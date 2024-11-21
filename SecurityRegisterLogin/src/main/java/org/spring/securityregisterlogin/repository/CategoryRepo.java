package org.spring.securityregisterlogin.repository;


import org.spring.securityregisterlogin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    public boolean existsByName(String name);
}
