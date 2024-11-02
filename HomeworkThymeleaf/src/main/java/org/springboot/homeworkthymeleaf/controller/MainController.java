package org.springboot.homeworkthymeleaf.controller;

import org.springboot.homeworkthymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String hello(){
        return "hello";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("login/check")
    public String checkLogin(@ModelAttribute("user") User user, Model model){
        if(user.getEmail().equals("admin@mail.ru") && user.getPassword().equals("123")){
            model.addAttribute("status", "admin");
        } else if(user.getEmail().equals("user@mail.ru") && user.getPassword().equals("123")){
            model.addAttribute("status", "user");
        }
        return "hello";
    }
}
