package org.spring.securityregisterlogin.service;

import org.spring.securityregisterlogin.entity.User;
import org.spring.securityregisterlogin.repository.UserRepo;
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
