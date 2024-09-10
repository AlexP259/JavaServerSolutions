package org.ee.jakarta.homework_07_user_register;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(value="/usersList")
public class UsersListServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql:///users_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Qwerty123!";

    private static final String query = "SELECT * FROM users_table";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        out.println("<html><head><link rel='stylesheet' href='css/style.css'></head><body><div class='list'>");
        out.println("<h1>Список пользователей</h1>");
        out.println("<table><tr>");
        out.println("<th>Идентификатор пользователя</th>");
        out.println("<th>Email</th>");
        out.println("<th>Логин</th>");
        out.println("<th>Пароль</th>");
        out.println("<th>Редактирование</th>");
        out.println("<th>Удаление</th>");
        out.println("</tr>");

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                out.println("<tr>");
                out.println("<td>" + resultSet.getInt(1) + "</td>");
                out.println("<td>" + resultSet.getString(2) + "</td>");
                out.println("<td>" + resultSet.getString(3) + "</td>");
                out.println("<td>" + resultSet.getString(4) + "</td>");
                out.println("<td><a href='editScreen?id=" + resultSet.getInt(1) + "'>Редактировать</a></td>");
                out.println("<td><a href='deleteUrl?id=" + resultSet.getInt(1) + "'>Удалить</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (SQLException e){
            e.printStackTrace();
            out.println("<h2>" + e.getMessage() + "</h2>");
        }
        out.println("<br><a href='main.html'>Главная</a>");
        out.println("</div></body></html>");
    }
}
