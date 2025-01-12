package org.spring_boot.gamestore.controller;

import jakarta.servlet.http.HttpSession;
import org.spring_boot.gamestore.entity.Genre;
import org.spring_boot.gamestore.entity.Game;
import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.repository.UserRepo;
import org.spring_boot.gamestore.service.IGameService;
import org.spring_boot.gamestore.service.IGenreService;
import org.spring_boot.gamestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String profile() {
        return "admin/profile";
    }

    @GetMapping("/signout")
    public String logout(){
        return "auth/logout";
    }

    @GetMapping("/")
    public String index() {
        return "admin/index";
    }


    //    ************** Добавить игру **************
    @GetMapping("/loadAddGame")
    public String loadAddGame(Model m){
        List<Genre> genres = genreService.getAllGenres();
        m.addAttribute("genres", genres);
        return "admin/add_game";
    }

    @PostMapping("/saveGame")
    public String saveGame(@ModelAttribute Game game, HttpSession session, @RequestParam("files[]")MultipartFile[] images){
        Game updateGame = gameService.saveGame(game, images);
        if(!ObjectUtils.isEmpty(updateGame)){
            session.setAttribute("succMsg", "Игра сохранена успешно");
        } else {
            session.setAttribute("errorMsg", "Ошибка сохранения игры");
        }
        return "redirect:/admin/loadAddGame";
    }
    //    ****************************************

    //    ************** Добавить жанр **************
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
    //    ****************************************

    //    ************** Игры **************
    @GetMapping("/games")
    public String loadViewGame(Model m, @RequestParam(defaultValue = "") String ch){
        List<Game> games = null;
        if(ch != null && !ch.isEmpty()){
            games = gameService.searchGame(ch);
        } else {
            games =  gameService.getAllGames();
        }
        m.addAttribute("games", games);
        System.out.println(games);
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

    @PostMapping("/updateGame")
    public String updateGame(@ModelAttribute Game game, HttpSession session, @RequestParam("files[]")MultipartFile[] images){
        Game updateGame = gameService.updateGame(game, images);
        if(!ObjectUtils.isEmpty(updateGame)){
            session.setAttribute("succMsg", "Игра обновлена успешно");
        } else {
            session.setAttribute("errorMsg", "Ошибка обновления игры");
        }
        return "redirect:/admin/editGame/" + game.getId();
    }
    //    ****************************************

    //    ************** Пользователи **************
    @GetMapping("/users")
    public String viewUsersList(Model m, @RequestParam(defaultValue = "") String ch){
        List<User> users = null;
        if(ch != null && ch.length() > 0){
            users = userService.searchUsersByNameAndEmail(ch);
        } else {
            users = userService.getUsersByRole("ROLE_USER");
        }
        m.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable int id, Model m){
        m.addAttribute("editUser", userService.getUserById(id));
        return "admin/edit_user";
    }

//    @PostMapping("/updateUser")
//    public String updateUser(@ModelAttribute User editUser, HttpSession session){
//        User updateUser =  userService.updateUser(editUser);
//        if(!ObjectUtils.isEmpty(updateUser)){
//            session.setAttribute("succMsg", "Пользователь обновлен успешно");
//        } else {
//            session.setAttribute("errorMsg", "Ошибка обновления пользователя");
//        }
//        return "redirect:/admin/editUser/" + editUser.getId();
//    }

    @PostMapping("/updateUser")
    public String updateUser(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam boolean non_lock,
            HttpSession session){
        User updateUser =  userService.updateUser(id, name, email, password, non_lock);
        if(!ObjectUtils.isEmpty(updateUser)){
            session.setAttribute("succMsg", "Пользователь обновлен успешно");
        } else {
            session.setAttribute("errorMsg", "Ошибка обновления пользователя");
        }
        return "redirect:/admin/editUser/" + id;
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id, HttpSession session){
        Boolean deleteUser = userService.deleteUser(id);
        if(deleteUser){
            session.setAttribute("succMsg", "Пользователь удален успешно");
        } else {
            session.setAttribute("errorMsg", "Ошибка удаления пользователя");
        }
        return "redirect:/admin/users";
    }
    //    ****************************************

    //    ************** Добавить админа **************
    @GetMapping("/loadAddAdmin")
    public String loadAddAdmin(Model m, @RequestParam(defaultValue = "") String ch){
        List<User> users = null;
        if(ch != null && ch.length() > 0){
            users = userService.searchUsersByNameAndEmail(ch);
        } else {
            users = userService.getAllUsers();
        }
        m.addAttribute("users", users);
        return "admin/add_admin";
    }

    @PostMapping("/saveAdmin")
    public String saveAdmin(@ModelAttribute("admin") User admin, HttpSession session) {
        User existAdmin = userRepo.findByEmail(admin.getEmail());

        if (existAdmin != null) {
            session.setAttribute("errorMsg", "Пользователь с таким email уже существует");
        } else {
            User u = userService.saveAdmin(admin);

            if (u != null) {
                session.setAttribute("succMsg", "Администратор зарегистрирован успешно");
            } else {
                session.setAttribute("errorMsg", "Регистрация администратора не удалась");
            }
        }

        return "redirect:/admin/loadAddAdmin";
    }

    @GetMapping("/makeAdmin/{id}")
    public String makeAdmin(@PathVariable int id){
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setRole("ROLE_ADMIN");
            userRepo.save(user);
        }
        return "redirect:/admin/loadAddAdmin";
    }

    @GetMapping("/unmakeAdmin/{id}")
    public String unmakeAdmin(@PathVariable int id){
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setRole("ROLE_USER");
            userRepo.save(user);
        }
        return "redirect:/admin/loadAddAdmin";
    }
    //    ****************************************



}











