package com.admin.servlet;

import com.dao.TeacherDao;
import com.db.DBConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/deleteTeacher")
public class DeleteTeacher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        TeacherDao dao = new TeacherDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        if(dao.deleteDoctor(id)){
            session.setAttribute("succMsg", "Преподаватель удален успешно");
            resp.sendRedirect("admin/teacher.jsp");
        } else {
            session.setAttribute("errorMsg", "Ошибка сервера");
            resp.sendRedirect("admin/teacher.jsp");
        }
    }
}


















