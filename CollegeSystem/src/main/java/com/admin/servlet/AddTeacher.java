package com.admin.servlet;

import com.dao.TeacherDao;
import com.db.DBConnect;
import com.entity.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/addTeacher")
public class AddTeacher extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String fullName = req.getParameter("full_name");
            String dob = req.getParameter("dob");
            String qualification = req.getParameter("qualification");
            String spec = req.getParameter("spec");
            String email = req.getParameter("email");
            String mobno = req.getParameter("mobno");
            String password = req.getParameter("password");

            Teacher teacher = new Teacher(fullName, dob, qualification, spec, email, mobno, password);
            TeacherDao dao = new TeacherDao(DBConnect.getConn());

            HttpSession session = req.getSession();

            if(dao.registerTeacher(teacher)){
                session.setAttribute("succMsg", "Преподаватель добавлен успешно");
                resp.sendRedirect("admin/teacher.jsp");
            } else {
                session.setAttribute("errorMsg", "Ошибка сервера");
                resp.sendRedirect("admin/teacher.jsp");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}















