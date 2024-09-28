package com.dao;

import com.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public Student login(String email, String password){
        Student student = null;

        try{
            String sql = "SELECT * FROM students_dtls WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                student = new Student();
                student.setId(resultSet.getInt(1));
                student.setName(resultSet.getString(2));
                student.setEmail(resultSet.getString(3));
                student.setPassword(resultSet.getString(4));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return student;
    }

}


















