package org.spring_boot.gamestore.controller;

import jakarta.servlet.http.HttpSession;
import org.spring_boot.gamestore.entity.Game;
import org.spring_boot.gamestore.entity.Genre;
import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.repository.UserRepo;
import org.spring_boot.gamestore.service.IGameService;
import org.spring_boot.gamestore.service.IGenreService;
import org.spring_boot.gamestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private IGenreService genreService;

    @Autowired
    private IGameService gameService;

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
    public String index(Model m,
                        @RequestParam(name = "genre", defaultValue = "") String genre,
                        @RequestParam(name = "ch", required = false, defaultValue = "") String ch,
                        @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                        @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize,
                        @RequestParam(name = "platform", required = false, defaultValue = "") String platform,
                        @RequestParam(name = "pg18", required = false, defaultValue = "false") boolean pg18
    ){

//        передаем жанры для меню слева
        List<Genre> genres = genreService.getAllGenres();
        m.addAttribute("paramValue", genre);
        m.addAttribute("genres", genres);
        m.addAttribute("platformValue", platform);
        m.addAttribute("pg18Value", pg18);
        m.addAttribute("searchValue", ch);

//        вывод игр справа
        Page<Game> page = gameService.getAllGamePagination(pageNo, pageSize, ch, genre, platform, pg18);
        List<Game> games = page.getContent();
        m.addAttribute("games", games);
        m.addAttribute("gamesSize", games.size());
        m.addAttribute("pageNo", page.getNumber());
        m.addAttribute("pageSize", pageSize);
        m.addAttribute("totalElements", page.getTotalElements());
        m.addAttribute("totalPages", page.getTotalPages());
        m.addAttribute("isLast", page.isLast());
        m.addAttribute("isFirst", page.isFirst());

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @GetMapping("/signin")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, HttpSession session) {
        User existUser = userRepo.findByEmail(user.getEmail());

        if (existUser != null) {
            session.setAttribute("errorMsg", "Пользователь с таким email уже существует");
        } else {
            User u = userService.saveUser(user);

            if (u != null) {
                session.setAttribute("succMsg", "Пользователь зарегистрирован успешно");
            } else {
                session.setAttribute("errorMsg", "Регистрация пользователя не удалась");
            }
        }

        return "redirect:/register";
    }

    @GetMapping("/game/{id}")
    public String item(@PathVariable int id, Model m){
        Game game = gameService.getGameById(id);
        m.addAttribute("game", game);
        return "view_game";
    }

}













