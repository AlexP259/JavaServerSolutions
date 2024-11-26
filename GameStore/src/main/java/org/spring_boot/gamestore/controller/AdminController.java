package org.spring_boot.gamestore.controller;

import jakarta.servlet.http.HttpSession;
import org.spring_boot.gamestore.entity.Genre;
import org.spring_boot.gamestore.entity.Game;
import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.repository.UserRepo;
import org.spring_boot.gamestore.service.IGameService;
import org.spring_boot.gamestore.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private IGenreService genreService;

    @Autowired
    private IGameService gameService;


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
        return "admin/profile";
    }

    @GetMapping("/")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/loadAddGame")
    public String loadAddGame(Model m){
        List<Genre> genres = genreService.getAllGenres();
        m.addAttribute("genres", genres);
        return "admin/add_game";
    }

    @GetMapping("/genre")
    public String genres(Model m){
        m.addAttribute("genres", genreService.getAllGenres());
        return "admin/genre";
    }

    @PostMapping("/saveGenre")
    public String saveGenre(@ModelAttribute Genre genre, HttpSession session){
        Boolean existGenre = genreService.existGenre(genre.getName());
        if(existGenre){
            session.setAttribute("errorMsg", "Такой жанр уже существует");
        } else {
            if(genre.getName().trim().isEmpty()){
                session.setAttribute("errorMsg", "Пустое название жанра");
            } else {
                Genre saveGenre = genreService.saveGenre(genre);
                if(saveGenre != null){
                    session.setAttribute("succMsg", "Жанр сохранен успешно");
                } else {
                    session.setAttribute("errorMsg", "Ошибка сохранения жанра");
                }
            }
        }
        return "redirect:/admin/genre";
    }

    @GetMapping("/deleteGenre/{id}")
    public String deleteGenre(@PathVariable int id, HttpSession session){
        Boolean deleteGenre = genreService.deleteGenre(id);
        if(deleteGenre){
            session.setAttribute("succMsg", "Жанр удален успешно");
        } else {
            session.setAttribute("errorMsg", "Ошибка удаления жанра");
        }
        return "redirect:/admin/genre";
    }

    @GetMapping("/loadEditGenre/{id}")
    public String loadEditGenre(@PathVariable int id, Model m){
        m.addAttribute("genre", genreService.getGenreById(id));
        return "admin/edit_genre";
    }

    @PostMapping("/updateGenre")
    public String updateGenre(@ModelAttribute Genre genre, HttpSession session){
        Genre oldGenre = genreService.getGenreById(genre.getId());
        if(oldGenre != null){
            oldGenre.setName(genre.getName());
        }
        Genre updateGenre = genreService.saveGenre(oldGenre);
        if(updateGenre != null){
            session.setAttribute("succMsg", "Жанр обновлен успешно");
        } else {
            session.setAttribute("errorMsg", "Ошибка обновления жанра");
        }
        return "redirect:/admin/genre";
    }

    @PostMapping("/saveGame")
    public String saveGame(@ModelAttribute Game game, HttpSession session, @RequestParam("file")MultipartFile image) throws IOException {
        String imageName = image.isEmpty() ? "default.jpg" : image.getOriginalFilename();
        game.setImage(imageName);

        Game saveGame = gameService.saveGame(game);
        if(saveGame != null){
            String saveFile = new File("src/main/resources/static/img/").getAbsolutePath();
            System.out.println(saveFile);
            if(!image.isEmpty()){
                Path path = Paths.get(saveFile + File.separator + "game_img" + File.separator + image.getOriginalFilename());
                System.out.println(path);

                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            session.setAttribute("succMsg", "Игра сохранена успешно");
        } else {
            session.setAttribute("errorMsg", "Ошибка сохранения игры");
        }
        return "redirect:/admin/loadAddGame";
    }

    @GetMapping("/games")
    public String loadViewGame(Model m, @RequestParam(defaultValue = "") String ch){
        List<Game> games = null;
        if(ch != null && ch.length() > 0){
            games = gameService.searchGame(ch);
        } else {
            games =  gameService.getAllGames();
        }
        m.addAttribute("games", games);
        return "admin/games";
    }

    @GetMapping("/deleteGame/{id}")
    public String deleteGame(@PathVariable int id, HttpSession session){
        Boolean deleteGame = gameService.deleteGame(id);
        if(deleteGame){
            session.setAttribute("succMsg", "Игра удалена успешно");
        } else {
            session.setAttribute("errorMsg", "Ошибка удаления игры");
        }
        return "redirect:/admin/games";
    }

    @GetMapping("/editGame/{id}")
    public String editGame(@PathVariable int id, Model m){
        m.addAttribute("game", gameService.getGameById(id));
        m.addAttribute("genres", genreService.getAllGenres());
        return "admin/edit_game";
    }

}











