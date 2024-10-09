package com.teacher.servlet;

import com.dao.TeacherDao;
import com.db.DBConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/teacherChangePassword")
public class TeacherPasswordChange extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("sid"));
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        TeacherDao teacherDao = new TeacherDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        if(teacherDao.checkOldPassword(sid, oldPassword)){
            if(teacherDao.changePassword(sid, newPassword)){
                session.setAttribute("succMsg", "Пароль изменен успешно");
                resp.sendRedirect("teacher/edit_profile.jsp");
            } else {
                session.setAttribute("errorMsg", "Ошибка сервера");
                resp.sendRedirect("teacher/edit_profile.jsp");
            }
        } else {
            session.setAttribute("errorMsg", "Пароль неверный");
            resp.sendRedirect("teacher/edit_profile.jsp");
        }
    }
}
