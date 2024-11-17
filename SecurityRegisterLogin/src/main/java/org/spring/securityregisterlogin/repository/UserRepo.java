package org.spring.securityregisterlogin.repository;

import org.spring.securityregisterlogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}
