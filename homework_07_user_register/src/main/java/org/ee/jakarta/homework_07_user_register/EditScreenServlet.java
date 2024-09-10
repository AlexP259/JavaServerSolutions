package org.ee.jakarta.homework_07_user_register;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql:///users_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Qwerty123!";

    private static final String query = "SELECT email, login, password FROM users_table WHERE id = ?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        out.println("<html><head><link rel='stylesheet' href='css/style.css'></head>");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)){
            out.println("<body><div class='list'>");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            out.println("<form action='editUrl?id=" + id + "' method='post'>");
            out.println("<table>");

            out.println("<tr>");
            out.println("<td>E-mail пользователя</td>");
            out.println("<td><input type='text' name='email' value='" + resultSet.getString(1) + "'></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Логин</td>");
            out.println("<td><input type='text' name='login' value='" + resultSet.getString(2) + "'></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Пароль</td>");
            out.println("<td><input type='text' name='password' value='" + resultSet.getString(3) + "'></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td><input type='submit' value='изменить' class='submit'></td>");
            out.println("<td><input type='reset' value='отменить' class='reset'></td>");
            out.println("</tr>");

            out.println("</table>");
            out.println("</form>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>" + e.getMessage() + "</h2>");
        }
        out.println("<br><a href='main.html'>Главная</a>");
        out.println("<br><a href='usersList'>Список пользователей</a>");
        out.println("</div></body></html>");
    }
}
