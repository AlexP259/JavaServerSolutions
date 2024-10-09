package com.teacher.servlet;

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

@WebServlet("/teacherUpdateProfile")
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

            Teacher teacher = new Teacher(id, fullName, dob, qualification, spec, email, phone, "");
            TeacherDao dao = new TeacherDao(DBConnect.getConn());
            HttpSession session = req.getSession();

            if(dao.editTeacherProfile(teacher)){
                Teacher updateTeacher = dao.getTeacherById(id);
                session.setAttribute("succMsg", "Преподаватель обновлен успешно");
                session.setAttribute("teacherObj", updateTeacher);
                resp.sendRedirect("teacher/edit_profile.jsp");
            } else {
                session.setAttribute("errorMsg", "Ошибка сервера");
                resp.sendRedirect("teacher/edit_profile.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
