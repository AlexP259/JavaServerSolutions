package org.spring_boot.gamestore.repository;

import org.spring_boot.gamestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    List<User> findAllByRoleContainsIgnoreCase(String role);

    List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String ch, String ch2);

    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password = :password WHERE u.id = :id")
    int updateUser(@Param("id") int id, @Param("name") String name, @Param("email") String email, @Param("password") String password);

}
