package com.dao;

import com.entity.Homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HomeworkDao {
    private Connection conn;

    public HomeworkDao(Connection conn) {
        this.conn = conn;
    }

    public boolean addHomework(Homework homework){
        boolean flag = false;

        try{
            String sql = "INSERT INTO homework(student_id, fullname, `group`, homework_date, email, phno, homework_file, teacher_id, status) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, homework.getStudentId());
            preparedStatement.setString(2, homework.getFullName());
            preparedStatement.setString(3, homework.getGroup());
            preparedStatement.setString(4, homework.getHomeworkDate());
            preparedStatement.setString(5, homework.getEmail());
            preparedStatement.setString(6, homework.getPhone());
            preparedStatement.setString(7, homework.getHomeworkFile());
            preparedStatement.setInt(8, homework.getTeacherId());
            preparedStatement.setString(9, homework.getStatus());


            int row = preparedStatement.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }


    public List<Homework> getAllHomeworksByLoginStudent(int studentId){
        List<Homework> homeworks = new ArrayList<>();
        Homework hw = null;

        try{
            String sql = "SELECT * FROM homework WHERE student_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                hw = new Homework();
                hw.setId(resultSet.getInt(1));
                hw.setStudentId(resultSet.getInt(2));
                hw.setFullName(resultSet.getString(3));
                hw.setGroup(resultSet.getString(4));
                hw.setHomeworkDate(resultSet.getString(5));
                hw.setEmail(resultSet.getString(6));
                hw.setPhone(resultSet.getString(7));
                hw.setHomeworkFile(resultSet.getString(8));
                hw.setTeacherId(resultSet.getInt(9));
                hw.setStatus(resultSet.getString(10));
                homeworks.add(hw);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return homeworks;
    }


    public List<Homework> getAllHomeworksByLoginTeacher(int teacherId){
        List<Homework> homeworks = new ArrayList<>();
        Homework hw = null;

        try{
            String sql = "SELECT * FROM homework WHERE teacher_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                hw = new Homework();
                hw.setId(resultSet.getInt(1));
                hw.setStudentId(resultSet.getInt(2));
                hw.setFullName(resultSet.getString(3));
                hw.setGroup(resultSet.getString(4));
                hw.setHomeworkDate(resultSet.getString(5));
                hw.setEmail(resultSet.getString(6));
                hw.setPhone(resultSet.getString(7));
                hw.setHomeworkFile(resultSet.getString(8));
                hw.setTeacherId(resultSet.getInt(9));
                hw.setStatus(resultSet.getString(10));
                homeworks.add(hw);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return homeworks;
    }

}













