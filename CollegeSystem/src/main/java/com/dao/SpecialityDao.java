package com.dao;

import com.entity.Speciality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpecialityDao {
    private Connection conn;

    public SpecialityDao(Connection conn) {
        this.conn = conn;
    }

    public boolean addSpeciality(String spec){
        boolean flag = false;

        try{
            String sql = "INSERT INTO speciality (spec_name) VALUES (?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, spec);
            int row = preparedStatement.executeUpdate();
            if (row == 1){
                flag = true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }


    public List<Speciality> getAllSpeciality(){
        List<Speciality> list = new ArrayList<Speciality>();
        Speciality speciality = null;

        try{
            String sql = "SELECT * FROM speciality";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                speciality = new Speciality();
                speciality.setId(resultSet.getInt(1));
                speciality.setSpecialityName(resultSet.getString(2));
                list.add(speciality);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}








