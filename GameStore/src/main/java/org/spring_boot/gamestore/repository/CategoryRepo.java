package org.spring_boot.gamestore.repository;

import org.spring_boot.gamestore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    public boolean existsByName(String name);
}
