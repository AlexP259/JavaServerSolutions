package org.spring.securityregisterlogin.controller;

import jakarta.servlet.http.HttpSession;
import org.spring.securityregisterlogin.entity.User;

import org.spring.securityregisterlogin.repository.UserRepo;
import org.spring.securityregisterlogin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {

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
    public String index(){
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

    @GetMapping("/user/profile")
    public String profile(Principal principal, Model model){
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        return "profile";
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


}


















