package com.dao;

import com.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TeacherDao {
    private Connection conn;

    public TeacherDao(Connection conn) {
        this.conn = conn;
    }

    public boolean registerTeacher(Teacher teacher){
        boolean flag = false;

        try{
            String sql = "INSERT INTO teacher (full_name, dob, qualification, speciality, email, mobno, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getFullName());
            preparedStatement.setString(2, teacher.getDob());
            preparedStatement.setString(3, teacher.getQualification());
            preparedStatement.setString(4, teacher.getSpeciality());
            preparedStatement.setString(5, teacher.getEmail());
            preparedStatement.setString(6, teacher.getMobNo());
            preparedStatement.setString(7, teacher.getPassword());

            int row = preparedStatement.executeUpdate();
            if (row == 1){
                flag = true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }

}
