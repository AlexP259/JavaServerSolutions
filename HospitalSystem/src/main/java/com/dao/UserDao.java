package com.dao;

import com.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public boolean register(User user){
        boolean flag = false;

        try{
            String sql = "INSERT INTO user_dtls (full_name, email, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            int row = preparedStatement.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}
