package com.doctor.servlet;

import com.dao.DoctorDao;
import com.db.DBConnect;
import com.entity.Doctor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/doctorUpdateProfile")
public class EditProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String fullName = req.getParameter("full_name");
            String dob = req.getParameter("dob");
            String qualification = req.getParameter("qualification");
            String spec = req.getParameter("spec");
            String email = req.getParameter("email");
            String phone = req.getParameter("mobno");

            int id = Integer.parseInt(req.getParameter("id"));

            Doctor doctor = new Doctor(id, fullName, dob, qualification, spec, email, phone, "");
            DoctorDao dao = new DoctorDao(DBConnect.getConnection());
            HttpSession session = req.getSession();

            if(dao.editDoctorProfile(doctor)){
                Doctor updateDoctor = dao.getDoctorById(id);
                session.setAttribute("succMsg", "Врач обновлен успешно");
                session.setAttribute("doctorObj", updateDoctor);
                resp.sendRedirect("doctor/edit_profile.jsp");
            } else {
                session.setAttribute("errorMsg", "Ошибка сервера");
                resp.sendRedirect("doctor/edit_profile.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
