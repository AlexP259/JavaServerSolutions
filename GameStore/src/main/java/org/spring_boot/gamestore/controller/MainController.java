package org.spring_boot.gamestore.controller;

import jakarta.servlet.http.HttpSession;
import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.repository.UserRepo;
import org.spring_boot.gamestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    public IUserService userService;

    @Autowired
    public UserRepo userRepo;

    @ModelAttribute
    public void commonUser(Principal principal, Model model){
        if(principal != null){
            String email = principal.getName();
            User user = userRepo.findByEmail(email);
            model.addAttribute("user", user);
        }
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/user/game-catalog")
    public String gameCatalog() {
        return "game_catalog";
    }

    @GetMapping("/user/user")
    public String user(Principal principal, Model model) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @GetMapping("/signin")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/user/signout")
    public String logout() {
        return "auth/logout";
    }

    @GetMapping("/invalid")
    public String invalid() {
        return "error";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, HttpSession session) {
        User u = userService.saveUser(user);

        if (u != null) {
            session.setAttribute("msg", "Пользователь зарегистрирован успешно");
        } else {
            session.setAttribute("msg", "Регистрация пользователя не удалась");
        }

        return "redirect:/register";
    }

}













