package org.spring.mvc.controller;

import org.spring.mvc.entity.User;
import org.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("home")
    public String home(){
        return "home";
    }

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user, @RequestParam("fullName") String fn, Model m){
        System.out.println("ФИО: " + fn);
        System.out.println(user);
        userService.registerUser(user);
//        m.addAttribute("msg", "Пользователь зарегистрирован успешно");
//        return "register";
        m.addAttribute("user", user);
        return "success";
    }
}



