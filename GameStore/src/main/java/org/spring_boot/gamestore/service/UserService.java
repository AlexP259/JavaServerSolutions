package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole("ROLE_USER");
        User newUser = userRepo.save(user);
        return newUser;
    }

}
