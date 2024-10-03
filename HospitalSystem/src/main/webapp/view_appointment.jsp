<%@ page import="com.entity.User" %>
<%@ page import="com.dao.AppointmentDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page import="com.dao.DoctorDao" %>
<%@ page import="com.entity.Appointment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.entity.Doctor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="component/allcss.jsp"/>
</head>
<body>
<jsp:include page="component/navbar.jsp"/>

<div class="appointment">
    <div class="wrap">
        <h2>Просмотр назначения</h2>

        <table class="table">
            <tr>
                <th scope="col">ФИО</th>
                <th scope="col">Пол</th>
                <th scope="col">Возраст</th>
                <th scope="col">Дата назначения</th>
                <th scope="col">Диагноз</th>
                <th scope="col">Имя врача</th>
                <th scope="col">Статус</th>
            </tr>

            <%
                User user = (User) session.getAttribute("userObj");
                AppointmentDao dao = new AppointmentDao(DBConnect.getConnection());
                DoctorDao dao2 = new DoctorDao(DBConnect.getConnection());
                List<Appointment> list = dao.getAllAppointmentsByLoginUser(user.getId());
                for (Appointment appointment : list) {
                    Doctor d = dao2.getDoctorById(appointment.getDoctorId());
            %>
            <tr>
                <td><%= appointment.getFullName() %></td>
                <td><%= appointment.getGender() %></td>
                <td><%= appointment.getAge() %></td>
                <td><%= appointment.getAppointDate() %></td>
                <td><%= appointment.getDiseases() %></td>
                <td><%= d.getFullName() %></td>
                <td>
                    <% if ("В ожидании".equals(appointment.getStatus()))
                        {
                    %>
                    <a href="#" class="btn btn-info">В ожидании</a>
                    <%
                        } else { %>
                    <%= appointment.getStatus() %>
                    <% } %>
                </td>
            </tr>
            <%
                }
            %>

        </table>
    </div>
</div>

</body>
</html>














