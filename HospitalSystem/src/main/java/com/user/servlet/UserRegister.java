package com.user.servlet;

import com.dao.UserDao;
import com.db.DBConnect;
import com.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user_register")
public class UserRegister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String fullName = req.getParameter("fullName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            User user = new User(fullName, email, password);
            UserDao userDao = new UserDao(DBConnect.getConnection());

            HttpSession session = req.getSession();
            boolean f = userDao.register(user);

            if(f){
                session.setAttribute("sucMsg", "Пользователь зарегистрирован успешно");
                resp.sendRedirect("sign_up.jsp");
            } else {
                session.setAttribute("errorMsg", "Ошибка регистрации");
                resp.sendRedirect("sign_up.jsp");
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
