package org.ee.jakarta.bookwebapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    private static final String query = "INSERT INTO book_data (book_title, book_edition, book_price) VALUES (?, ?, ?)";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }

        String bookTitle = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

         // за счет загрузки драйвера через Class.forName(), путь к БД пишем сокращая localhost:3306
        try (Connection connection = DriverManager.getConnection("jdbc:mysql:///book", "root", "Qwerty123!");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bookTitle);
            preparedStatement.setString(2, bookEdition);
            preparedStatement.setFloat(3, bookPrice);
            int row = preparedStatement.executeUpdate();

            out.println("<html><head><link rel='stylesheet' href='css/style.css'></head>");
            out.println("<body><div class='list'>");

            if(row > 0){
                out.println("<h2>Книга зарегистрирована</h2>");
            } else {
                out.println("<h2>Регистрация книги не удалась</h2>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>" + e.getMessage() + "</h2>");
        }
        out.println("<br><a href='home.html'>Главная</a>");
        out.println("<br><a href='bookList'>Список книг</a>");
        out.println("</div></body></html>");
    }
}
