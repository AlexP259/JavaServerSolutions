package com.dao;

import com.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDao {
    private Connection conn;

    public StudentDao(Connection conn) {
        this.conn = conn;
    }

    public boolean registerStudent(Student student){
        boolean flag = false;

        try{
            String sql = "INSERT INTO students_dtls(full_name, email, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            int row = preparedStatement.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}


















