package com.dao;

import com.entity.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao {
    private Connection conn;

    public AppointmentDao(Connection conn) {
        this.conn = conn;
    }

    public boolean addAppointment(Appointment appointment) {
        boolean flag = false;

        try {
            String sql = "INSERT INTO appointment(user_id, fullname, gender, age, appoint_date, email, phno, diseases, doctor_id, address, status) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, appointment.getUserId());
            preparedStatement.setString(2, appointment.getFullName());
            preparedStatement.setString(3, appointment.getGender());
            preparedStatement.setString(4, appointment.getAge());
            preparedStatement.setString(5, appointment.getAppointDate());
            preparedStatement.setString(6, appointment.getEmail());
            preparedStatement.setString(7, appointment.getPhone());
            preparedStatement.setString(8, appointment.getDiseases());
            preparedStatement.setInt(9, appointment.getDoctorId());
            preparedStatement.setString(10, appointment.getAddress());
            preparedStatement.setString(11, appointment.getStatus());

            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }


    public List<Appointment> getAllAppointmentsByLoginUser(int userId) {
        List<Appointment> appointments = new ArrayList<>();
        Appointment app = null;

        try{
            String sql = "SELECT * FROM appointment WHERE user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                app = new Appointment();
                app.setId(resultSet.getInt(1));
                app.setUserId(resultSet.getInt(2));
                app.setFullName(resultSet.getString(3));
                app.setGender(resultSet.getString(4));
                app.setAge(resultSet.getString(5));
                app.setAppointDate(resultSet.getString(6));
                app.setEmail(resultSet.getString(7));
                app.setPhone(resultSet.getString(8));
                app.setDiseases(resultSet.getString(9));
                app.setDoctorId(Integer.parseInt(resultSet.getString(10)));
                app.setAddress(resultSet.getString(11));
                app.setStatus(resultSet.getString(12));
                appointments.add(app);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return appointments;
    }


    public List<Appointment> getAllAppointmentsByLoginDoctor(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        Appointment app = null;

        try{
            String sql = "SELECT * FROM appointment WHERE doctor_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                app = new Appointment();
                app.setId(resultSet.getInt(1));
                app.setUserId(resultSet.getInt(2));
                app.setFullName(resultSet.getString(3));
                app.setGender(resultSet.getString(4));
                app.setAge(resultSet.getString(5));
                app.setAppointDate(resultSet.getString(6));
                app.setEmail(resultSet.getString(7));
                app.setPhone(resultSet.getString(8));
                app.setDiseases(resultSet.getString(9));
                app.setDoctorId(Integer.parseInt(resultSet.getString(10)));
                app.setAddress(resultSet.getString(11));
                app.setStatus(resultSet.getString(12));
                appointments.add(app);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return appointments;
    }


    public Appointment getAppointmentById(int id) {
        Appointment app = null;

        try{
            String sql = "SELECT * FROM appointment WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                app = new Appointment();
                app.setId(resultSet.getInt(1));
                app.setUserId(resultSet.getInt(2));
                app.setFullName(resultSet.getString(3));
                app.setGender(resultSet.getString(4));
                app.setAge(resultSet.getString(5));
                app.setAppointDate(resultSet.getString(6));
                app.setEmail(resultSet.getString(7));
                app.setPhone(resultSet.getString(8));
                app.setDiseases(resultSet.getString(9));
                app.setDoctorId(Integer.parseInt(resultSet.getString(10)));
                app.setAddress(resultSet.getString(11));
                app.setStatus(resultSet.getString(12));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return app;
    }


    public boolean updateCommentStatus(int id, int doctorId, String comment){
        boolean flag = false;

        try{
            String sql = "UPDATE appointment SET status = ? WHERE id = ? AND doctor_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, comment);
            ps.setInt(2, id);
            ps.setInt(3, doctorId);
            int row = ps.executeUpdate();

            if(row == 1){
                flag = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return flag;
    }


    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        Appointment app = null;

        try{
            String sql = "SELECT * FROM appointment ORDER BY id DESC";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                app = new Appointment();
                app.setId(resultSet.getInt(1));
                app.setUserId(resultSet.getInt(2));
                app.setFullName(resultSet.getString(3));
                app.setGender(resultSet.getString(4));
                app.setAge(resultSet.getString(5));
                app.setAppointDate(resultSet.getString(6));
                app.setEmail(resultSet.getString(7));
                app.setPhone(resultSet.getString(8));
                app.setDiseases(resultSet.getString(9));
                app.setDoctorId(Integer.parseInt(resultSet.getString(10)));
                app.setAddress(resultSet.getString(11));
                app.setStatus(resultSet.getString(12));
                appointments.add(app);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return appointments;
    }

}













