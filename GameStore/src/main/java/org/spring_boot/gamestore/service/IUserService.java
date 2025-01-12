package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.Game;
import org.spring_boot.gamestore.entity.User;

import java.math.BigDecimal;
import java.util.List;


public interface IUserService {

    public User saveUser(User user);

    public User saveAdmin(User user);

    List<User> getUsersByRole(String role);

    List<User> getAllUsers();

    User getUserById(int id);

    List<User> searchUsersByNameAndEmail(String ch);

//    User updateUser(User user);

    User updateUser(int id, String name, String email, String password, boolean non_lock);

    public Boolean deleteUser(int id);

//    это когда было поле баланс типа BigDecimal у юзера. Лучше зафигачу
//    кошелек как прям отдельную сущность
//    public void upBalance(String email, BigDecimal money);

    public  Boolean buyGame(int userId, int gameId);

    public void addPurchasedGame(User user, Game game);

}
