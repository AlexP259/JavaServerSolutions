package org.spring.mvc.service;

import org.spring.mvc.dao.UserDao;
import org.spring.mvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserDao userDao;

    @Override
    public int registerUser(User user) {
        int i = userDao.saveUser(user);
        return i;
    }
}
