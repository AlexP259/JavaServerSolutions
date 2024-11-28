package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.Game;
import org.spring_boot.gamestore.repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class GameService implements IGameService{

    @Autowired
    private GameRepo gameRepo;

    @Override
    public Game saveGame(Game game) {
        return gameRepo.save(game);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @Override
    public Boolean deleteGame(int id) {
        Game game = gameRepo.findById(id).orElse(null);
        if(game != null){
            gameRepo.delete(game);
            return true;
        }
        return false;
    }

    @Override
    public Game getGameById(int id) {
        Game game = gameRepo.findById(id).orElse(null);
        return game;
    }

    @Override
    public List<Game> getAllSelectGames(String genre) {
        List<Game> games = null;
        if(ObjectUtils.isEmpty(genre)){
            games = gameRepo.findAll();
        } else {
            games = gameRepo.findByGenre(genre);
        }
        return games;
    }

    @Override
    public List<Game> searchGame(String ch) {
        return gameRepo.findByNameContainingIgnoreCaseOrGenreContainingIgnoreCase(ch, ch);
    }

    @Override
    public Page<Game> getAllGamePagination(Integer pageNo, Integer pageSize, String genre) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Game> pageGames = null;
        if(ObjectUtils.isEmpty(genre)){
            pageGames = gameRepo.findAllBy(pageable);
        } else {
            pageGames = gameRepo.findByGenre(pageable, genre);
        }
        return pageGames;
    }

    @Override
    public Game updateGame(Game game, MultipartFile image) {
        Game dbGame = getGameById(game.getId());
        String imageName = image.isEmpty() ? dbGame.getImage() : image.getOriginalFilename();

        dbGame.setName(game.getName());
        dbGame.setDescription(game.getDescription());
        dbGame.setGenre(game.getGenre());
        dbGame.setImage(imageName);
        dbGame.setPrice(game.getPrice());

        Game updateGame = gameRepo.save(dbGame);

        if(updateGame != null){
            if(!image.isEmpty()){
                try{
                    String saveFile = new File("src/main/resources/static/img").getAbsolutePath();
                    Path path = Paths.get(saveFile + File.separator + "game_img" + File.separator + image.getOriginalFilename());
                    Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(game);
            return game;
        }
        return null;
    }
}










