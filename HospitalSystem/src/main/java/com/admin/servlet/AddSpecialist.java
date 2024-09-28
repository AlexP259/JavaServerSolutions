package com.admin.servlet;

import com.dao.SpecialistDao;
import com.db.DBConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/addSpecialist")
public class AddSpecialist extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String specName = req.getParameter("specName");

        SpecialistDao specialistDao = new SpecialistDao(DBConnect.getConnection());
        boolean f = specialistDao.addSpecialist(specName);

        HttpSession session = req.getSession();

        if(f){
            session.setAttribute("succMsg", "Специалист добавлен");
            resp.sendRedirect("admin/index.jsp");
        } else {
            session.setAttribute("errorMsg", "Ошибка сервера");
            resp.sendRedirect("admin/index.jsp");
        }
    }
}
