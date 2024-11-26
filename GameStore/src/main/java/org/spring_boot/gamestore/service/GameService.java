package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.Game;
import org.spring_boot.gamestore.repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
}
