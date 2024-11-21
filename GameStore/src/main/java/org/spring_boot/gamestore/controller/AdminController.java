package org.spring_boot.gamestore.controller;

import jakarta.servlet.http.HttpSession;
import org.spring_boot.gamestore.entity.Category;
import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.repository.UserRepo;
import org.spring_boot.gamestore.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        return "admin/profile";
    }

    @GetMapping("/")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/loadAddItem")
    public String loadAddItem(){
        return "admin/add_item";
    }

    @GetMapping("/category")
    public String category(Model m){
        m.addAttribute("categories", categoryService.getAllCategory());
        return "admin/category";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute Category category, HttpSession session){
        Boolean existCategory = categoryService.existCategory(category.getName());
        if(existCategory){
            session.setAttribute("errorMsg", "Категория с таким именем уже существует");
        } else {
            if(category.getName().trim().isEmpty()){
                session.setAttribute("errorMsg", "Пустое название категории");
            } else {
                Category saveCategory = categoryService.saveCategory(category);
                if(saveCategory != null){
                    session.setAttribute("succMsg", "Категория создана успешно");
                } else {
                    session.setAttribute("errorMsg", "Ошибка создания категории");
                }
            }
        }
        return "redirect:/admin/category";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id, HttpSession session){
        Boolean deleteCategory = categoryService.deleteCategory(id);
        if(deleteCategory){
            session.setAttribute("succMsg", "Категория удалена успешно");
        } else {
            session.setAttribute("errorMsg", "Ошибка удаления категории");
        }
        return "redirect:/admin/category";
    }

}











