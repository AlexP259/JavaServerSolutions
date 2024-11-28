package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.entity.UsersEWallet;
import org.spring_boot.gamestore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


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
        user.seteWallet(new UsersEWallet());
        User newUser = userRepo.save(user);
        return newUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<User> searchUsersByNameAndEmail(String ch) {
        return userRepo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(ch, ch);
    }

    //    это для баланса у юзера было
//    @Override
//    public void upBalance(String email, BigDecimal money) {
//        User user = userRepo.findByEmail(email);
//        BigDecimal balance = user.getBalance();
//        BigDecimal result = balance.add(money);
//        user.setBalance(result);
//        userRepo.save(user);
//    }
}
