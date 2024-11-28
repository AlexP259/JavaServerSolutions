package org.spring_boot.gamestore.controller;

import jakarta.servlet.http.HttpSession;
import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.repository.UserRepo;
import org.spring_boot.gamestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;


    @ModelAttribute
    public void commonUser(Principal principal, Model model){
        if(principal != null){
            String email = principal.getName();
            User user = userRepo.findByEmail(email);
            model.addAttribute("user", user);
        }
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/topUpBalance")
    public String topUpBalance() {
        return "user/balance";
    }


//    тут шло пополнение баланса до внедрения отдельного кошелька юзера
//    @PostMapping("/saveBalance")
//    public String saveBalance(Principal principal, @RequestParam(value = "money") BigDecimal money, HttpSession session) {
//        String email = userRepo.findByEmail(principal.getName()).getEmail();
//        userService.upBalance(email, money);
//
//        session.setAttribute("succMsg", "Баланс пополнен успешно");
//
//        return "redirect:/user/profile";
//    }


}
