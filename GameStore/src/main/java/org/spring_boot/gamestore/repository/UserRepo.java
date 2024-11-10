package org.spring_boot.gamestore.repository;

import org.spring_boot.gamestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
}
