package org.spring.securityregisterlogin.controller;

import jakarta.servlet.http.HttpSession;
import org.spring.securityregisterlogin.entity.Category;
import org.spring.securityregisterlogin.entity.Post;
import org.spring.securityregisterlogin.entity.User;

import org.spring.securityregisterlogin.repository.UserRepo;
import org.spring.securityregisterlogin.service.ICategoryService;
import org.spring.securityregisterlogin.service.IPostService;
import org.spring.securityregisterlogin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepo userRepo;


    @ModelAttribute
    public void commonUser(Principal principal, Model model){
        if(principal != null){
            String email = principal.getName();
            User user = userRepo.findByEmail(email);
            model.addAttribute("user", user);
        }
    }

    @GetMapping("/")
    public String index(Model m, @RequestParam(value = "category", defaultValue = "") String category, @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize){
        List<Category> categories = categoryService.getAllCategory();
        m.addAttribute("paramValue", category);
        m.addAttribute("categories", categories);

//        List<Post> posts = postService.getAllSelectPosts(category);   вывод постов через поиск
//        m.addAttribute("posts", posts);

        Page<Post> page = postService.getAllPostPagination(pageNo, pageSize, category);
        List<Post> posts = page.getContent();
        m.addAttribute("posts", posts);
        m.addAttribute("postsSize", posts.size());
        m.addAttribute("postsNo", page.getNumber());
        m.addAttribute("postsSize", pageSize);
        m.addAttribute("totalElements", page.getTotalElements());
        m.addAttribute("totalPages", page.getTotalPages());

        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/signin")
    public String login(){
        return "login";
    }

    @GetMapping("/user/home")
    public String home(){
        return "home";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session){
//        System.out.println(user);
        User u = userService.saveUser(user);

        if(u != null){
//            System.out.println("Save user success");
            session.setAttribute("msg", "Register successfully");
        } else {
//            System.out.println("Error save user");
            session.setAttribute("msg", "Register failed");
        }
        return "redirect:/register";
    }

    @GetMapping("/item/{id}")
    public String item(@PathVariable int id, Model m){
        Post post = postService.getPostById(id);
        m.addAttribute("post", post);
        return "view_item";
    }

    @GetMapping("/search")
    public String searchItem(@RequestParam String ch, Model m){
        List<Post> searchPost = postService.searchPost(ch);
        m.addAttribute("posts", searchPost);

        List<Category> categories = categoryService.getAllCategory();
        m.addAttribute("categories", categories);
        return "index";
    }




}


















