package org.spring.securityregisterlogin.controller;

import org.spring.securityregisterlogin.entity.User;
import org.spring.securityregisterlogin.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/profile")
    public String profile(Principal principal, Model model){
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        return "user/profile";
    }
}
