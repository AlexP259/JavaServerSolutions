package org.ee.jakarta.servletapplication;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebServlet(value="/second")
public class SecondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html"); // говорим клиенту, что ответ будет в формате html
        String parameter1 = req.getParameter("param1"); // Здесь мы получаем значение параметра запроса с именем "param1"
        var writer = resp.getWriter(); // Этот вызов получает объект PrintWriter, который используется для отправки текстового содержимого в ответ. Var позволяет компилятору автоматически определить тип переменной writer
        writer.println("<h1>" + parameter1 + "</h1>");

        Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.entrySet().forEach((e) -> writer.println("<h3>" + e.getKey() + " = " + Arrays.toString(e.getValue()) + "</h3>"));
    }
}
