package org.ee.jakarta;

import java.util.Optional;

public class UserDao {
    public Optional<User> findByID(Long id){
        User user = new User();
        user.setName("Виктор");
        return Optional.of(user);
    }
}
