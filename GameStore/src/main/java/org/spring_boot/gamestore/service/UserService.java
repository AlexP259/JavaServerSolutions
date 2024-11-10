package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        User newUser = userRepo.save(user);
        return newUser;
    }
}
