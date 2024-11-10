package org.spring_boot.gamestore.controller;

import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    public UserService userService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/game-catalog")
    public String gameCatalog(){
        return "game_catalog";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "auth/logout";
    }

    @GetMapping("/invalid")
    public String invalid(){
        return "error";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user){
    userService.saveUser(user);
        return "register";
    }

}
