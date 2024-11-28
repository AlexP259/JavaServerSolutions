package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IGameService {
    public Game saveGame(Game game);

    public List<Game> getAllGames();

    public Boolean deleteGame(int id);

    public Game getGameById(int id);

    public List<Game> getAllSelectGames(String genre);

    public List<Game> searchGame(String ch);

    public Page<Game> getAllGamePagination(Integer pageNo, Integer pageSize, String genre);

    public Game updateGame(Game game, MultipartFile image);
}
