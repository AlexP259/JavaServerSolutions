package org.spring_boot.gamestore.repository;

import org.spring_boot.gamestore.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepo extends JpaRepository<Game, Integer> {
    List<Game> findByGenre(String genre);

//    это для админского поиска по пользователям
    List<Game> findByNameContainingIgnoreCaseOrGenreContainingIgnoreCase(String ch, String genre);


    // Поиск по названию, жанру, платформе включая игры с рейтингом 18+
    Page<Game> findByNameContainingIgnoreCaseAndGenreAndPlatform(Pageable pageable, String ch, String genre, String platform);

    // Поиск по названию и жанру включая игры с рейтингом 18+
    Page<Game> findByNameContainingIgnoreCaseAndGenre(Pageable pageable, String ch, String genre);

    // Поиск по названию и платформе включая игры с рейтингом 18+
    Page<Game> findByNameContainingIgnoreCaseAndPlatform(Pageable pageable, String ch, String platform);

    // Поиск только по названию включая игры с рейтингом 18+
    Page<Game> findByNameContainingIgnoreCase(Pageable pageable, String ch);

    // Поиск по жанру и платформе включая игры с рейтингом 18+
    Page<Game> findByGenreAndPlatform(Pageable pageable, String genre, String platform);

    // Поиск только по жанру включая игры с рейтингом 18+
    Page<Game> findByGenre(Pageable pageable, String genre);

    // Поиск только по платформе включая игры с рейтингом 18+
    Page<Game> findByPlatform(Pageable pageable, String platform);



    // Поиск по названию, жанру и платформе не включая игры с рейтингом 18+
    Page<Game> findByNameContainingIgnoreCaseAndGenreAndPlatformAndPg18False(Pageable pageable, String ch, String genre, String platform);

    // Поиск по названию и жанру не включая игры с рейтингом 18+
    Page<Game> findByNameContainingIgnoreCaseAndGenreAndPg18False(Pageable pageable, String ch, String genre);

    // Поиск по названию и платформе не включая игры с рейтингом 18+
    Page<Game> findByNameContainingIgnoreCaseAndPlatformAndPg18False(Pageable pageable, String ch, String platform);

    // Поиск только по названию не включая игры с рейтингом 18+
    Page<Game> findByNameContainingIgnoreCaseAndPg18False(Pageable pageable, String ch);

    // Поиск по жанру и платформе не включая игры с рейтингом 18+
    Page<Game> findByGenreAndPlatformAndPg18False(Pageable pageable, String genre, String platform);

    // Поиск только по жанру не включая игры с рейтингом 18+
    Page<Game> findByGenreAndPg18False(Pageable pageable, String genre);

    // Поиск только по платформе не включая игры с рейтингом 18+
    Page<Game> findByPlatformAndPg18False(Pageable pageable, String platform);

    // Поиск не включая игры с рейтингом 18+
    Page<Game> findByPg18False(Pageable pageable);





    Page<Game> findAllBy(Pageable pageable);
}

























