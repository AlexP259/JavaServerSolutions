package com.dao;

import com.entity.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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


    public List<Doctor> getAllDoctors(){
        List<Doctor> doctors = new ArrayList<Doctor>();

        Doctor doctor = null;

        try{
            String sql = "SELECT * FROM doctor ORDER BY id DESC";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                doctor = new Doctor();
                doctor.setId(resultSet.getInt(1));
                doctor.setFullName(resultSet.getString(2));
                doctor.setDob(resultSet.getString(3));
                doctor.setQualification(resultSet.getString(4));
                doctor.setSpecialist(resultSet.getString(5));
                doctor.setEmail(resultSet.getString(6));
                doctor.setMobNo(resultSet.getString(7));
                doctor.setPassword(resultSet.getString(8));
                doctors.add(doctor);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return doctors;
    }


    public Doctor getDoctorById(int id){
        Doctor doctor = null;

        try{
            String sql = "SELECT * FROM doctor WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                doctor = new Doctor();
                doctor.setId(resultSet.getInt(1));
                doctor.setFullName(resultSet.getString(2));
                doctor.setDob(resultSet.getString(3));
                doctor.setQualification(resultSet.getString(4));
                doctor.setSpecialist(resultSet.getString(5));
                doctor.setEmail(resultSet.getString(6));
                doctor.setMobNo(resultSet.getString(7));
                doctor.setPassword(resultSet.getString(8));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return doctor;
    }


    public boolean updateDoctor(Doctor doctor){
        boolean flag = false;
        
        try{
            String sql = "UPDATE doctor SET full_name=?, dob=?, qualification=?, specialist=?, email=?, mobno=?, password=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, doctor.getFullName());
            preparedStatement.setString(2, doctor.getDob());
            preparedStatement.setString(3, doctor.getQualification());
            preparedStatement.setString(4, doctor.getSpecialist());
            preparedStatement.setString(5, doctor.getEmail());
            preparedStatement.setString(6, doctor.getMobNo());
            preparedStatement.setString(7, doctor.getPassword());
            preparedStatement.setInt(8, doctor.getId());

            int row = preparedStatement.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }


    public boolean deleteDoctor(int id){
        boolean flag = false;

        try{
            String sql = "DELETE FROM doctor WHERE id = ?";
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


    public Doctor login(String email, String password){
        Doctor doctor = null;

        try{
            String sql = "SELECT * FROM doctor WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                doctor = new Doctor();
                doctor.setId(resultSet.getInt(1));
                doctor.setFullName(resultSet.getString(2));
                doctor.setDob(resultSet.getString(3));
                doctor.setQualification(resultSet.getString(4));
                doctor.setSpecialist(resultSet.getString(5));
                doctor.setEmail(resultSet.getString(6));
                doctor.setMobNo(resultSet.getString(7));
                doctor.setPassword(resultSet.getString(8));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return doctor;
    }

}























