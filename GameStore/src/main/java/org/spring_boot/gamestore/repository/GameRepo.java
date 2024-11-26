package org.spring_boot.gamestore.repository;

import org.spring_boot.gamestore.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepo extends JpaRepository<Game, Integer> {
    List<Game> findByGenre(String genre);

    List<Game> findByNameContainingIgnoreCaseOrGenreContainingIgnoreCase(String ch, String ch2);

    Page<Game> findByGenre(Pageable pageable, String genre);

    Page<Game> findAllBy(Pageable pageable);
}
