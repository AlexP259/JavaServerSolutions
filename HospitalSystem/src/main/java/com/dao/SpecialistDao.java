package com.dao;

import com.entity.Specialist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpecialistDao {
    private Connection connection;

    public SpecialistDao(Connection connection) {
        this.connection = connection;
    }


    public boolean addSpecialist(String spec){
        boolean flag = false;

        try{
            String sql = "INSERT INTO specialist (spec_name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, spec);
            int row = preparedStatement.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }


    public List<Specialist> getAllSpecialist(){
        List<Specialist> list = new ArrayList<Specialist>();
        Specialist specialist = null;

        try{
            String sql = "SELECT * FROM specialist";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                specialist = new Specialist();
                specialist.setId(resultSet.getInt(1));
                specialist.setSpecialistName(resultSet.getString(2));
                list.add(specialist);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }


}











