package com.dao;

import com.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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


    public List<Teacher> getAllTeachers(){
        List<Teacher> teachers = new ArrayList<Teacher>();

        Teacher teacher = null;

        try{
            String sql = "SELECT * FROM teacher ORDER BY id DESC";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                teacher = new Teacher();
                teacher.setId(resultSet.getInt(1));
                teacher.setFullName(resultSet.getString(2));
                teacher.setDob(resultSet.getString(3));
                teacher.setQualification(resultSet.getString(4));
                teacher.setSpeciality(resultSet.getString(5));
                teacher.setEmail(resultSet.getString(6));
                teacher.setMobNo(resultSet.getString(7));
                teacher.setPassword(resultSet.getString(8));
                teachers.add(teacher);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return teachers;
    }


    public Teacher getTeacherById(int id){
        Teacher teacher = null;

        try{
            String sql = "SELECT * FROM teacher WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                teacher = new Teacher();
                teacher.setId(resultSet.getInt(1));
                teacher.setFullName(resultSet.getString(2));
                teacher.setDob(resultSet.getString(3));
                teacher.setQualification(resultSet.getString(4));
                teacher.setSpeciality(resultSet.getString(5));
                teacher.setEmail(resultSet.getString(6));
                teacher.setMobNo(resultSet.getString(7));
                teacher.setPassword(resultSet.getString(8));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return teacher;
    }


    public boolean updateTeacher(Teacher teacher){
        boolean flag = false;

        try{
            String sql = "UPDATE teacher SET full_name=?, dob=?, qualification=?, speciality=?, email=?, mobno=?, password=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getFullName());
            preparedStatement.setString(2, teacher.getDob());
            preparedStatement.setString(3, teacher.getQualification());
            preparedStatement.setString(4, teacher.getSpeciality());
            preparedStatement.setString(5, teacher.getEmail());
            preparedStatement.setString(6, teacher.getMobNo());
            preparedStatement.setString(7, teacher.getPassword());
            preparedStatement.setInt(8, teacher.getId());

            int row = preparedStatement.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }


    public boolean deleteTeacher(int id){
        boolean flag = false;

        try{
            String sql = "DELETE FROM teacher WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int row = preparedStatement.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }


    public Teacher login(String email, String password){
        Teacher teacher = null;

        try{
            String sql = "SELECT * FROM teacher WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                teacher = new Teacher();
                teacher.setId(resultSet.getInt(1));
                teacher.setFullName(resultSet.getString(2));
                teacher.setDob(resultSet.getString(3));
                teacher.setQualification(resultSet.getString(4));
                teacher.setSpeciality(resultSet.getString(5));
                teacher.setEmail(resultSet.getString(6));
                teacher.setMobNo(resultSet.getString(7));
                teacher.setPassword(resultSet.getString(8));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return teacher;
    }


    public int countTeachers(){
        int i = 0;

        try{
            String sql = "SELECT * FROM teacher";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }


    public int countHomeworks(){
        int i = 0;

        try{
            String sql = "SELECT * FROM homework";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }


    public int countStudents(){
        int i = 0;

        try{
            String sql = "SELECT * FROM students_dtls";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }


    public int countSpecialities(){
        int i = 0;

        try{
            String sql = "SELECT * FROM speciality";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }


    public int countHomeworksTeacherId(int tid){
        int i = 0;

        try{
            String sql = "SELECT * FROM homework WHERE teacher_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }


    public boolean checkOldPassword(int studentId, String oldPassword){
        boolean flag = false;

        try{
            String sql = "SELECT * FROM teacher WHERE id = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setString(2, oldPassword);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                flag = true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }


    public boolean changePassword(int studentId, String newPassword){
        boolean flag = false;

        try{
            String sql = "UPDATE teacher SET password = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setInt(2, studentId);
            int row = ps.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }


    public boolean editTeacherProfile(Teacher teacher){
        boolean flag = false;

        try{
            String sql = "UPDATE teacher SET full_name=?, dob=?, qualification=?, speciality=?, email=?, mobno=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getFullName());
            preparedStatement.setString(2, teacher.getDob());
            preparedStatement.setString(3, teacher.getQualification());
            preparedStatement.setString(4, teacher.getSpeciality());
            preparedStatement.setString(5, teacher.getEmail());
            preparedStatement.setString(6, teacher.getMobNo());
            preparedStatement.setInt(7, teacher.getId());

            int row = preparedStatement.executeUpdate();
            System.out.println("Hello " + row);
            if(row == 1){
                flag = true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }

}








