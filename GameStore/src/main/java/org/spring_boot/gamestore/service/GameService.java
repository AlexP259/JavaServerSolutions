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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepo gameRepo;


    @Override
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @Override
    public Boolean deleteGame(int id) {
        Game game = gameRepo.findById(id).orElse(null);
        if (game != null) {

            deleteOldPathsAndImages(game.getImages(), null);

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
        if (ObjectUtils.isEmpty(genre)) {
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
    public Page<Game> getAllGamePagination(Integer pageNo, Integer pageSize, String ch, String genre, String platform, boolean pg18) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Game> pageGames = null;

        if (pg18) {
            // Если pg18 == true, игнорируем жанр и платформу
            if (ch != null && !ch.isEmpty()) {
                // Поисковой запрос задан
                if (genre != null && !genre.isEmpty()) {
                    if (platform != null && !platform.isEmpty()) {
                        // Поиск по названию, жанру, платформе
                        pageGames = gameRepo.findByNameContainingIgnoreCaseAndGenreAndPlatform(pageable, ch, genre, platform);
                    } else {
                        // Поиск по названию и жанру
                        pageGames = gameRepo.findByNameContainingIgnoreCaseAndGenre(pageable, ch, genre);
                    }
                } else {
                    // Жанр не задан
                    if (platform != null && !platform.isEmpty()) {
                        // Поиск по названию и платформе
                        pageGames = gameRepo.findByNameContainingIgnoreCaseAndPlatform(pageable, ch, platform);
                    } else {
                        // Поиск только по названию
                        pageGames = gameRepo.findByNameContainingIgnoreCase(pageable, ch);
                    }
                }
            } else {
                // Поисковой запрос не задан
                if (genre != null && !genre.isEmpty()) {
                    if (platform != null && !platform.isEmpty()) {
                        // Поиск по жанру и платформе
                        pageGames = gameRepo.findByGenreAndPlatform(pageable, genre, platform);
                    } else {
                        // Поиск только по жанру
                        pageGames = gameRepo.findByGenre(pageable, genre);
                    }
                } else {
                    // Жанр не задан
                    if (platform != null && !platform.isEmpty()) {
                        // Поиск только по платформе
                        pageGames = gameRepo.findByPlatform(pageable, platform);
                    } else {
                        // Нет активных фильтров, ищем все
                        pageGames = gameRepo.findAll(pageable);
                    }
                }
            }
        } else {
            // Если pg18 == false, ищем игры без pg18
            if (ch != null && !ch.isEmpty()) {
                // Поисковой запрос задан
                if (genre != null && !genre.isEmpty()) {
                    if (platform != null && !platform.isEmpty()) {
                        // Поиск по названию, жанру и платформе
                        pageGames = gameRepo.findByNameContainingIgnoreCaseAndGenreAndPlatformAndPg18False(pageable, ch, genre, platform);
                    } else {
                        // Поиск по названию и жанру
                        pageGames = gameRepo.findByNameContainingIgnoreCaseAndGenreAndPg18False(pageable, ch, genre);
                    }
                } else {
                    // Жанр не задан
                    if (platform != null && !platform.isEmpty()) {
                        // Поиск по названию и платформе
                        pageGames = gameRepo.findByNameContainingIgnoreCaseAndPlatformAndPg18False(pageable, ch, platform);
                    } else {
                        // Поиск только по названию
                        pageGames = gameRepo.findByNameContainingIgnoreCaseAndPg18False(pageable, ch);
                    }
                }
            } else {
                // Поисковой запрос не задан
                if (genre != null && !genre.isEmpty()) {
                    if (platform != null && !platform.isEmpty()) {
                        // Поиск по жанру и платформе
                        pageGames = gameRepo.findByGenreAndPlatformAndPg18False(pageable, genre, platform);
                    } else {
                        // Поиск только по жанру
                        pageGames = gameRepo.findByGenreAndPg18False(pageable, genre);
                    }
                } else {
                    // Жанр не задан
                    if (platform != null && !platform.isEmpty()) {
                        // Поиск только по платформе
                        pageGames = gameRepo.findByPlatformAndPg18False(pageable, platform);
                    } else {
                        // Поиск только игр без pg18
                        pageGames = gameRepo.findByPg18False(pageable);
                    }
                }
            }
        }

        return pageGames;
    }

    @Override
    public Game saveGame(Game game, MultipartFile[] images) {
//        записываем пути файлов image для игры
        List<String> gameImagePaths = createListPathsImages(images, game.getPlatform(), game.getName(), null);
        game.setImages(gameImagePaths);

//        сохраним игру
        Game saveGame = gameRepo.save(game);

//        теперь переходим к созданию директорий и сохранению картинок в файловой системе
        createDirForImages(images, game.getPlatform(), game.getName(), null);

        return saveGame;
    }

    @Override
    public Game updateGame(Game game, MultipartFile[] images) {
//        Находим в БД игру под таким id
        Game dbGame = getGameById(game.getId());

//        нам нужны пути картинок.
        List<String> gameImagePaths = createListPathsImages(images, game.getPlatform(), game.getName(), dbGame.getImages());

//        перед тем, как назначать новые пути к картинкам объекту dbGame, сохраним старые пути в переменную. Они нам понадобятся когда будем приводить в порядок каталоги
        List<String> pathsOldImages = dbGame.getImages();

//        пропишем изменения через сеттеры в dbGame
        dbGame.setName(game.getName());
        dbGame.setDescription(game.getDescription());
        dbGame.setPlatform(game.getPlatform());
        dbGame.setGenre(game.getGenre());
        dbGame.setSystemRequirements(game.getSystemRequirements());
        dbGame.setPg18(game.isPg18());
        dbGame.setImages(gameImagePaths);
        dbGame.setPrice(game.getPrice());

//        сохраним dbGame
        Game updateGame = gameRepo.save(dbGame);

//        теперь переходим к созданию директорий и сохранению картинок в файловой системе
        createDirForImages(images, dbGame.getPlatform(), dbGame.getName(), pathsOldImages);

//        необходимо удалить старые директории
        deleteOldPathsAndImages(pathsOldImages, dbGame.getImages());

        return updateGame;
    }

//    Метод createListPathsImages формирует записи путей, которые будут хранится у игры в свойстве images[]. Метод используется и для формирований путей
//    новой игры, и для формирования путей редактируемой игры, поэтому принимает параметры platform, gameName и список путей.
    @Override
    public List<String> createListPathsImages(MultipartFile[] images, String platform, String gameName, List<String> pathsOldImages) {
        List<String> gameImagePaths = new ArrayList<>();

//        если есть старые пути, то формируем пути с новыми платформой и названием игры, но со старыми названиями файлов. Для этого
//        проходимся по массиву pathsOldImages и, если путь не "default.jpg", то из строки типа "Windows/BloodBorne/1.jpg" делаем файл, а уже из него извлекаем конкретное
//        имя файла и используем его для формирования нового пути. Иначе, если путь "default.jpg" И новых картинок нет, то default и сохраняем.
//        Этот блок кода про редактирование игры
        if (pathsOldImages != null) {
            for (String pathOldImage : pathsOldImages) {
                if (!pathOldImage.contains("default.jpg")) {
                    File oldFile = new File("src/main/resources/static/img/game_img/" + pathOldImage);
                    gameImagePaths.add(platform + "/" + gameName + "/" + oldFile.getName());
                }
                if (pathOldImage.contains("default.jpg") && images[0].isEmpty()) {
                    gameImagePaths.add("default.jpg");
                }
            }
        }

//        Этот блок кода может быть и про редактирование и про создание. Конкретнее, он про прикрепление новых картинок.
//        Теперь проверим что там по новым прикрепленным картинкам
        for (MultipartFile image : images) {
//            если мы прикрепили картинки, то в массив добавятся пути типа платформа/название_игры/имя_файла
            if (image != null && !image.isEmpty()) {
                gameImagePaths.add(platform + "/" + gameName + "/" + image.getOriginalFilename());
            } else if (pathsOldImages == null) {
//                если картинки не прикрепляли, то если старых путей нет (игра создается, а не редактируется) в массив сохранится 1 запись - default.jpg
                gameImagePaths.add("default.jpg");
            }
        }

        return gameImagePaths;
    }

    @Override
    public void createDirForImages(MultipartFile[] images, String platform, String gameName, List<String> pathsOldImages) {
//        Сначала создадим актуальный путь
//        Абсолютный путь до папки img
        String absolutePath = new File("src/main/resources/static/img/").getAbsolutePath();

        // Проверяем, есть ли каталоги для изображений
        boolean hasNewImages = false;

        // Проверяем старые пути
        if (pathsOldImages != null) {
            System.out.println("pathsOldImages != null");
            for (String oldPath : pathsOldImages) {
                if (!oldPath.contains("default.jpg")) {
                    hasNewImages = true;
                    break; // Найдена старая картинка, которая не default.jpg
                }
            }
        }

        if (hasNewImages || !images[0].isEmpty()) {
            //        проверим есть ли каталог game_img. Если нет, то создадим
            File mainDir = new File(absolutePath, "game_img");
            if (!mainDir.exists()) {
                mainDir.mkdirs(); // Создаем каталог, если он не существует
            }
//        проверим есть ли каталог с названием платформы данной игры, если нет, то создадим
            File platformDir = new File(mainDir, platform);
            if (!platformDir.exists()) {
                platformDir.mkdirs(); // Создаем каталог, если он не существует
            }
//        Создаем подкаталог для игры
            File gameDir = new File(platformDir, gameName);
            if (!gameDir.exists()) {
                gameDir.mkdirs(); // Создаем подкаталог для игры, если он не существует
            }

//        Копируем старые файлы
            if (pathsOldImages != null) {
                for (String oldPath : pathsOldImages) {
                    if (!oldPath.contains("default.jpg")) {
//                    из старого пути делаем файл
                        File oldFile = new File(oldPath);
//                это  старый путь
                        Path from = Paths.get(mainDir + File.separator + oldPath);
//                это новый путь (из файла мы взяли чисто имя)
                        Path into = Paths.get(gameDir + File.separator + oldFile.getName());
                        try {
                            Files.copy(from, into, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    try {
                        Path path = Paths.get(gameDir + File.separator + image.getOriginalFilename());
                        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void deleteOldPathsAndImages(List<String> pathsOldImages, List<String> newPaths) {
        String absolutePath = new File("src/main/resources/static/img/").getAbsolutePath();

        File oldPlatformDir = null;
        File oldGameNameDir = null;

        if (newPaths == null) {
            // Удаляем все старые файлы
            for (String oldPath : pathsOldImages) {
                if (!oldPath.contains("default.jpg")) {
                    File oldFileForDelete = new File(absolutePath + File.separator + "game_img" + File.separator + oldPath);
                    if (oldFileForDelete.delete()) {
                        oldGameNameDir = oldFileForDelete.getParentFile();
                        oldPlatformDir = oldGameNameDir.getParentFile();
                    }
                }
            }
        } else {
            // Логика удаления только тех файлов, которые не находятся в newPaths
            for (String oldPath : pathsOldImages) {
                if (!oldPath.contains("default.jpg")) {
                    boolean isInNewPaths = newPaths.stream().anyMatch(newPath -> newPath.equals(oldPath));

                    if (!isInNewPaths) {
                        File oldFileForDelete = new File(absolutePath + File.separator + "game_img" + File.separator + oldPath);
                        if (oldFileForDelete.delete()) {
                            oldGameNameDir = oldFileForDelete.getParentFile();
                            oldPlatformDir = oldGameNameDir.getParentFile();
                        }
                    }
                }
            }
        }

        deleteEmptyDirectory(oldGameNameDir);
        deleteEmptyDirectory(oldPlatformDir);
    }

    @Override
    public void deleteEmptyDirectory(File dir) {
        if (dir != null && dir.isDirectory() && dir.list().length == 0) {
            dir.delete();
        }
    }

}









