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

@WebServlet("/doctorLogin")
public class DoctorLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        DoctorDao doctorDao = new DoctorDao(DBConnect.getConnection());
        Doctor doctor = doctorDao.login(email, password);

        if(doctor != null){
            session.setAttribute("doctorObj", doctor);
            resp.sendRedirect("doctor/index.jsp");
        } else {
            session.setAttribute("errorMsg", "Некорректный email или пароль");
            resp.sendRedirect("doctor_login.jsp");
        }
    }
}
