package org.spring_boot.gamestore.controller;

import jakarta.servlet.http.HttpSession;
import org.spring_boot.gamestore.entity.MethodOfReplenishment;
import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.entity.UsersEWallet;
import org.spring_boot.gamestore.enums.PaymentSystem;
import org.spring_boot.gamestore.repository.MethodOfReplenishmentRepo;
import org.spring_boot.gamestore.repository.UserRepo;
import org.spring_boot.gamestore.service.ICardService;
import org.spring_boot.gamestore.service.IUsersEWalletService;
import org.spring_boot.gamestore.service.MethodOfReplenishmentService;
import org.spring_boot.gamestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
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
    @Autowired
    private IUsersEWalletService usersEWalletService;
    @Autowired
    private ICardService cardService;
    @Autowired
    private MethodOfReplenishmentService methodOfReplenishmentService;
    @Autowired
    private MethodOfReplenishmentRepo methodOfReplenishmentRepo;


    @ModelAttribute
    public void commonUser(Principal principal, Model model){
        if(principal != null){
            String email = principal.getName();
            User user = userRepo.findByEmail(email);
            model.addAttribute("user", user);
        }
    }

    @GetMapping("/profile")
    public String profile() {
        return "user/profile";
    }

    @GetMapping("/signout")
    public String logout(){
        return "auth/logout";
    }

    @GetMapping("/chooseMethodsOfReplenishment")
    public String chooseMethodsOfReplenishment(Model m){
        m.addAttribute("paymentSystems", PaymentSystem.values());
        return "user/methods_of_replenishment";
    }

    @PostMapping(value = "/addMethodOfReplenishment")
    public String addMethodOfReplenishment(
            @ModelAttribute MethodOfReplenishment methodOfReplenishment,
            @RequestParam("usersEWalletId") int eWalletId,
            HttpSession session) {

        boolean isValidCard = cardService.validateCard(
                methodOfReplenishment.getPaymentSystem(),
                methodOfReplenishment.getCardNumber(),
                methodOfReplenishment.getValidityPeriod(),
                methodOfReplenishment.getCvv(),
                methodOfReplenishment.getFirstName(),
                methodOfReplenishment.getLastName(),
                methodOfReplenishment.getCity()
        );

        if(!isValidCard){
            session.setAttribute("errorMsg", "Карта не найдена");
            return "redirect:/user/chooseMethodsOfReplenishment";
        }

        UsersEWallet eWallet = usersEWalletService.addMethodOfReplenishmentForThisWallet(methodOfReplenishment, eWalletId);

        if(!ObjectUtils.isEmpty(eWallet)){
            session.setAttribute("succMsg", "Метод пополнения добавлен успешно");
        } else {
            session.setAttribute("errorMsg", "Ошибка добавления метода пополнения");
        }

        return "redirect:/user/chooseMethodsOfReplenishment";
    }

    @GetMapping("/deleteMethodOfReplenishment/{id}")
    public String deleteMethodOfReplenishment(@PathVariable int id, HttpSession session){
        boolean res = methodOfReplenishmentService.deleteMethodOfReplenishment(id);
        if(res){
            session.setAttribute("succMsg2", "Метод пополнения удален успешно");
        } else {
            session.setAttribute("errorMsg2", "Ошибка добавления метода пополнения");
        }
        return "redirect:/user/chooseMethodsOfReplenishment";
    }

    @GetMapping("/pageBalance")
    public String pageBalance() {
        return "user/balance";
    }

    @PostMapping("/upBalance")
    public String upBalance(
            @RequestParam("methodOfReplenishmentId") int methodOfReplenishmentId,
            @RequestParam("usersEWalletId") int eWalletId,
            @RequestParam BigDecimal sum,
            HttpSession session) {

        MethodOfReplenishment methodOfReplenishment = methodOfReplenishmentRepo.findById(methodOfReplenishmentId).orElse(null);

        boolean upBalance = cardService.upBalance(
                methodOfReplenishment.getPaymentSystem(),
                methodOfReplenishment.getCardNumber(),
                methodOfReplenishment.getValidityPeriod(),
                methodOfReplenishment.getCvv(),
                methodOfReplenishment.getFirstName(),
                methodOfReplenishment.getLastName(),
                methodOfReplenishment.getCity(),
                eWalletId,
                sum
        );

        if(!upBalance){
            session.setAttribute("errorMsg", "Списание не удалось");
            return "redirect:/user/pageBalance";
        }

        UsersEWallet eWallet = usersEWalletService.upBalanceOnWallet(eWalletId, sum);
        session.setAttribute("succMsg", "Пополнение кошелька прошло успешно");
        session.setAttribute("errorMsg", "Ошибка пополнения кошелька");


        return "redirect:/user/pageBalance";
    }

    @GetMapping("/buyTheGame/{userId}/{gameId}")
    public String buyTheGame(@PathVariable int userId, @PathVariable int gameId, HttpSession session){
        Boolean success = userService.buyGame(userId, gameId);

        if(success){
            session.setAttribute("succMsg", "Игра куплена успешно");
        } else {
            session.setAttribute("errorMsg", "Покупка игры не удалась");
        }

        return "redirect:/game/" + gameId;
    }

}

