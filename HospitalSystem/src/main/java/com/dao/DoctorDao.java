package com.dao;

import com.entity.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DoctorDao {
    private Connection conn;

    public DoctorDao(Connection conn) {
        this.conn = conn;
    }

    public boolean registerDoctor(Doctor doctor){
        boolean flag = false;

        try{
            String sql = "INSERT INTO doctor (full_name, dob, qualification, specialist, email, mobno, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, doctor.getFullName());
            preparedStatement.setString(2, doctor.getDob());
            preparedStatement.setString(3, doctor.getQualification());
            preparedStatement.setString(4, doctor.getSpecialist());
            preparedStatement.setString(5, doctor.getEmail());
            preparedStatement.setString(6, doctor.getMobNo());
            preparedStatement.setString(7, doctor.getPassword());

            int row = preparedStatement.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}
