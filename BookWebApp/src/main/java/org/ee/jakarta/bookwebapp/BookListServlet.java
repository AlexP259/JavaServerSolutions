package org.ee.jakarta.bookwebapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    private static final String query = "SELECT * FROM book_data";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        out.println("<html><head><link rel='stylesheet' href='css/style.css'></head>");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql:///book", "root", "Qwerty123!");
             PreparedStatement preparedStatement = connection.prepareStatement(query)){
            out.println("<body><div class='list'>");
            out.println("<h1>Book List</h1>");
            out.println("<table><tr>");
            out.println("<th>Идентификатор книги</th>");
            out.println("<th>Название книги</th>");
            out.println("<th>Книжное издание</th>");
            out.println("<th>Цена книги</th>");
            out.println("<th>Редактирование</th>");
            out.println("<th>Удаление</th>");
            out.println("</tr>");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                out.println("<tr>");
                out.println("<td>" + resultSet.getInt(1) + "</td>");
                out.println("<td>" + resultSet.getString(2) + "</td>");
                out.println("<td>" + resultSet.getString(3) + "</td>");
                out.println("<td>" + resultSet.getFloat(4) + "</td>");
                out.println("<td><a href='editScreen?id=" + resultSet.getInt(1) + "'>Редактировать</a></td>");
                out.println("<td><a href='deleteUrl?id=" + resultSet.getInt(1) + "'>Удалить</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>" + e.getMessage() + "</h2>");
        }
        out.println("<br><a href='home.html'>Главная</a>");
        out.println("</div></body></html>");
    }
}
