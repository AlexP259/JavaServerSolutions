package org.spring_boot.gamestore.repository;

import org.spring_boot.gamestore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Integer> {
    public boolean existsByName(String name);
}
