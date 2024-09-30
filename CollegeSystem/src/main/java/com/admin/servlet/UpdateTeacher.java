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

@WebServlet("/updateTeacher")
public class UpdateTeacher extends HttpServlet {
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

            int id = Integer.parseInt(req.getParameter("id"));

            Teacher teacher = new Teacher(id, fullName, dob, qualification, spec, email, mobno, password);
            TeacherDao dao = new TeacherDao(DBConnect.getConn());
            HttpSession session = req.getSession();

            if(dao.updateTeacher(teacher)){
                session.setAttribute("succMsg", "Преподаватель обновлен успешно");
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
