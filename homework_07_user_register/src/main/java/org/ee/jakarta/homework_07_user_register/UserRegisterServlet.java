package org.ee.jakarta.homework_07_user_register;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(value = "/register")
public class UserRegisterServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql:///users_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Qwerty123!";

    private static final String sql = "INSERT INTO users_table (email, login, password) VALUES (?, ?, ?)";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean emailExists = checkEmailExists(email);

        if(!emailExists){
            try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, login);
                preparedStatement.setString(3, password);
                int row = preparedStatement.executeUpdate();

                out.println("<html><head><link rel='stylesheet' href='css/style.css'></head><body><div class='list'>");
                if(row > 0){
                    out.println("<h2>Пользователь зарегистрирован</h2>");
                } else {
                    out.println("<h2>Регистрация пользователя не удалась</h2>");
                }
            } catch (SQLException e){
                e.printStackTrace();
                out.println("<h2>" + e.getMessage() + "</h2>");
            }
            out.println("<br>");
            out.println("</div></body></html>");
        } else {
            out.println("<html><head><link rel='stylesheet' href='css/style.css'></head><body><div class='list'>");
            out.println("<h2>Регистрация пользователя не удалась</h2>");
            out.println("<h2>Пользователь с таким email уже зарегистрирован</h2>");
            out.println("<br><a href='main.html'>Главная</a>");
            out.println("</div></body></html>");
        }

    }

    private boolean checkEmailExists(String email){
        String query = "SELECT * FROM users_table WHERE email = ?";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
























