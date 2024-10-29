package org.springboot.learn_spring_boot.controller;

import org.springboot.learn_spring_boot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("variable-expression")
    public String variableExpression(Model model){
        User user = new User("Виктор", "vic24@yandex.ru", "ADMIN", "Male");
        model.addAttribute("user", user);
        return "variable-expression";
    }

    @GetMapping("selection-expression")
    public String selectionExpression(Model model){
        User user = new User("Виктор", "vic24@yandex.ru", "ADMIN", "Male");
        model.addAttribute("user", user);
        return "selection-expression";
    }

    @GetMapping("message-expression")
    public String messageExpression(){
        return "message-expression";
    }

    @GetMapping("link-expression")
    public String linkExpression(){
        return "link-expression";
    }

    @GetMapping("fragment-expression")
    public String fragmentExpression(){
        return "fragment-expression";
    }

    @GetMapping("users")
    public String users(Model model){
        User admin = new User("Igor", "igor@gmail.com", "ADMIN", "Male");
        User user1 = new User("Misha", "misha@gmail.com", "USER", "Male");
        User user2 = new User("Marina", "marina@gmail.com", "USER", "Female");
        List<User> users = new ArrayList<>();
        users.add(admin);
        users.add(user1);
        users.add(user2);
        model.addAttribute("users", users);
        return "users";
    }


    @GetMapping("if-unless")
    public String ifUnless(Model model){
        User admin = new User("Igor", "igor@gmail.com", "ADMIN", "Male");
        User user1 = new User("Misha", "misha@gmail.com", "USER", "Male");
        User user2 = new User("Marina", "marina@gmail.com", "USER", "Female");
        List<User> users = new ArrayList<>();
        users.add(admin);
        users.add(user1);
        users.add(user2);
        model.addAttribute("users", users);
        return "if-unless";
    }

}












