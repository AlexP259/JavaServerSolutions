package com.student.servlet;

import com.dao.StudentDao;
import com.db.DBConnect;
import com.entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/studentLogin")
public class StudentLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        StudentDao studentDao = new StudentDao(DBConnect.getConn());
        Student student = studentDao.login(email, password);

        if(student != null){
            session.setAttribute("studentObj", student);
            resp.sendRedirect("index.jsp");
        } else {
            session.setAttribute("errorMsg", "Некорректный email или пароль");
            resp.sendRedirect("student_login.jsp");
        }
    }
}















