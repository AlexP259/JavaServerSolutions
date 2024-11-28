package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.User;

import java.util.List;


public interface IUserService {

    public User saveUser(User user);

    List<User> getAllUsers();

    List<User> searchUsersByNameAndEmail(String ch);

//    это когда было поле баланс типа BigDecimal у юзера. Лучше зафигачу
//    кошелек как прям отдельную сущность
//    public void upBalance(String email, BigDecimal money);

}
