package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.Game;
import org.spring_boot.gamestore.entity.User;
import org.spring_boot.gamestore.entity.UsersEWallet;
import org.spring_boot.gamestore.repository.GameRepo;
import org.spring_boot.gamestore.repository.UserRepo;
import org.spring_boot.gamestore.repository.UsersEWalletRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UsersEWalletRepo usersEWalletRepo;
    @Autowired
    private GameRepo gameRepo;


    @Override
    public User saveUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole("ROLE_USER");
        user.setAccountNonLocked(true);
        user.seteWallet(new UsersEWallet());
        User newUser = userRepo.save(user);
        return newUser;
    }

    @Override
    public User saveAdmin(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole("ROLE_ADMIN");
        user.setAccountNonLocked(true);
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

    @Override
    public User getUserById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userRepo.findAllByRoleContainsIgnoreCase(role);
    }

//    @Override
//    public User updateUser(User editUser) {
//        User dbUser = userRepo.findById(editUser.getId()).orElse(null);
//        if(dbUser != null){
//            if(!editUser.getPassword().trim().isEmpty()){
//                String password = passwordEncoder.encode(editUser.getPassword());
//                dbUser.setPassword(password);
//            }
//            if(editUser.getName().trim().isEmpty() || editUser.getEmail().trim().isEmpty()){
//                return null;
//            }
//            dbUser.setName(editUser.getName());
//            dbUser.setEmail(editUser.getEmail());
//            System.out.println(dbUser);
////            int res = userRepo.updateUser(dbUser.getId(), dbUser.getName(), dbUser.getEmail(), dbUser.getPassword());
//            User res = userRepo.save(dbUser);
//            return res;
//        }
//        return null;
//    }

    @Override
    public User updateUser(int id, String name, String email, String password, boolean non_lock) {
        User dbUser = userRepo.findById(id).orElse(null);
        if(dbUser != null){
            if(!password.trim().isEmpty()){
                String passwordEncode = passwordEncoder.encode(password);
                dbUser.setPassword(passwordEncode);
            }
            if(name.trim().isEmpty() || email.trim().isEmpty()){
                return null;
            }
            dbUser.setName(name);
            dbUser.setEmail(email);
            dbUser.setAccountNonLocked(non_lock);
            System.out.println(dbUser);
//            int res = userRepo.updateUser(dbUser.getId(), dbUser.getName(), dbUser.getEmail(), dbUser.getPassword());
            User res = userRepo.save(dbUser);
            return res;
        }
        return null;
    }

    @Override
    public Boolean deleteUser(int id) {
        User user = userRepo.findById(id).orElse(null);
        if(user != null){
            userRepo.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean buyGame(int userId, int gameId) {
        User user = userRepo.findById(userId).orElse(null);
        UsersEWallet usersEWallet = user.geteWallet();
        Game game = gameRepo.findById(gameId).orElse(null);


        if(usersEWallet != null && game.getPrice().compareTo(usersEWallet.getBalance()) <= 0){
            usersEWallet.setBalance(usersEWallet.getBalance().subtract(game.getPrice()));
            usersEWalletRepo.save(usersEWallet);

            addPurchasedGame(user, game);
            return true;
        }

        return false;
    }

    @Override
    public void addPurchasedGame(User user, Game game) {

        if (user.getPurchasedGames() == null) {
            user.setPurchasedGames(new ArrayList<>());
        }
        user.getPurchasedGames().add(game);
        userRepo.save(user);
    }
}
