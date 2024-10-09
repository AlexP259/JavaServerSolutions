package com.doctor.servlet;

import com.dao.DoctorDao;
import com.db.DBConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/doctorChangePassword")
public class DoctorPasswordChange extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = Integer.parseInt(req.getParameter("uid"));
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        DoctorDao doctorDao = new DoctorDao(DBConnect.getConnection());
        HttpSession session = req.getSession();

        if(doctorDao.checkOldPassword(uid, oldPassword)){
            if(doctorDao.changePassword(uid, newPassword)){
                session.setAttribute("succMsg", "Пароль изменен успешно");
                resp.sendRedirect("doctor/edit_profile.jsp");
            } else {
                session.setAttribute("errorMsg", "Ошибка сервера");
                resp.sendRedirect("doctor/edit_profile.jsp");
            }
        } else {
            session.setAttribute("errorMsg", "Пароль неверный");
            resp.sendRedirect("doctor/edit_profile.jsp");
        }
    }
}
