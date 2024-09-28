package com.admin.servlet;

import com.dao.SpecialityDao;
import com.db.DBConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/addSpeciality")
public class AddSpeciality extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String specName = req.getParameter("specName");

        SpecialityDao dao = new SpecialityDao(DBConnect.getConn());
        boolean f = dao.addSpeciality(specName);

        HttpSession session = req.getSession();

        if(f){
            session.setAttribute("succMsg", "Специальность добавлена");
            resp.sendRedirect("admin/index.jsp");
        } else {
            session.setAttribute("errorMsg", "Ошибка сервера");
            resp.sendRedirect("admin/index.jsp");
        }
    }
}













