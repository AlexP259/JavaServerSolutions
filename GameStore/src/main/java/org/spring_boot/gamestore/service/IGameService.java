package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface IGameService {

    public List<Game> getAllGames();

    public Boolean deleteGame(int id);

    public Game getGameById(int id);

    public List<Game> getAllSelectGames(String genre);

    public List<Game> searchGame(String ch);

    public Page<Game> getAllGamePagination(Integer pageNo, Integer pageSize, String ch, String genre, String platform, boolean pg18);

    Game saveGame(Game game, MultipartFile[] images);

    Game updateGame(Game game, MultipartFile[] images);

    List<String> createListPathsImages(MultipartFile[] images, String platform, String gameName, List<String> pathsOldImages);

    void createDirForImages(MultipartFile[] images, String platform, String gameName, List<String> pathsOldImages);

    void deleteOldPathsAndImages(List<String> pathsOldImages, List<String> newPaths);

    void deleteEmptyDirectory(File dir);

}
