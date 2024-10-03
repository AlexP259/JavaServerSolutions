package com.student.servlet;

import com.dao.HomeworkDao;
import com.db.DBConnect;
import com.entity.Homework;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/addHomework")
public class HomeworkServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentid"));
        String fullName = req.getParameter("fullName");
        String group = req.getParameter("group");
        String homeworkDate = req.getParameter("homework_date");
        String email = req.getParameter("email");
        String phone = req.getParameter("number");
        String homeworkFile = req.getParameter("homework_file");
        int teacher = Integer.parseInt(req.getParameter("teacher"));

        Homework homework = new Homework(studentId, fullName, group, homeworkDate, email, phone, homeworkFile, teacher, "В ожидании");

        HomeworkDao dao = new HomeworkDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        if (dao.addHomework(homework)){
            session.setAttribute("succMsg", "Домашняя работа сдана");
            resp.sendRedirect("student_homework.jsp");
        } else {
            session.setAttribute("errorMsg", "Ошибка сервера");
            resp.sendRedirect("student_homework.jsp");
        }
    }
}













