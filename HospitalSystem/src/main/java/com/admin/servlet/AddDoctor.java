package com.admin.servlet;

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

@WebServlet("/addDoctor")
public class AddDoctor extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fullName = req.getParameter("full_name");
            String dob = req.getParameter("dob");
            String qualification = req.getParameter("qualification");
            String spec = req.getParameter("spec");
            String email = req.getParameter("email");
            String mobno = req.getParameter("mobno");
            String password = req.getParameter("password");

            Doctor doctor = new Doctor(fullName, dob, qualification, spec, email, mobno, password);

            DoctorDao doctorDao = new DoctorDao(DBConnect.getConnection());
            HttpSession session = req.getSession();

            if(doctorDao.registerDoctor(doctor)){
                session.setAttribute("succMsg", "Врач добавлен успешно");
                resp.sendRedirect("admin/doctor.jsp");
            } else {
                session.setAttribute("errorMsg", "Ошибка сервера");
                resp.sendRedirect("admin/doctor.jsp");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}












