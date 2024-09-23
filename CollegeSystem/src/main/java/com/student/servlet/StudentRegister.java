package com.student.servlet;

import com.db.DBConnect;
import com.entity.Student;
import com.dao.StudentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/studentRegister")
public class StudentRegister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String fullName = req.getParameter("fullName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            Student student = new Student(fullName, email, password);
            StudentDao studentDao = new StudentDao(DBConnect.getConn());

            HttpSession session = req.getSession();
            boolean f = studentDao.registerStudent(student);
            if(f){
                session.setAttribute("sucMsg", "Студент зарегистрирован успешно");
                resp.sendRedirect("signup.jsp");
            } else {
                session.setAttribute("errorMsg", "Регистрация студента не удалась");
                resp.sendRedirect("signup.jsp");
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
