package com.user.servlet;

import com.dao.AppointmentDao;
import com.db.DBConnect;
import com.entity.Appointment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/addAppointment")
public class AppointmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userid"));
        String fullName = req.getParameter("fullName");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        String appointDate = req.getParameter("appoint_date");
        String email = req.getParameter("email");
        String phone = req.getParameter("number");
        String diseases = req.getParameter("diseases");
        int doctorId = Integer.parseInt(req.getParameter("doct"));
        String address = req.getParameter("address");

        Appointment appointment = new Appointment(userId, fullName, gender, age, appointDate, email, phone, diseases, doctorId, address, "В ожидании");

        AppointmentDao dao = new AppointmentDao(DBConnect.getConnection());
        HttpSession session = req.getSession();

        if (dao.addAppointment(appointment)){
            session.setAttribute("succMsg", "Назначение создано успешно");
            resp.sendRedirect("user_appointment.jsp");
        } else {
            session.setAttribute("errorMsg", "Ошибка сервера");
            resp.sendRedirect("user_appointment.jsp");
        }
    }
}
