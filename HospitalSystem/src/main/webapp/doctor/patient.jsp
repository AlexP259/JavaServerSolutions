<%@ page import="com.entity.Doctor" %>
<%@ page import="com.dao.AppointmentDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page import="com.entity.Appointment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../component/allcss.jsp"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<c:if test="${empty doctorObj}">
    <c:redirect url="../doctor_login.jsp"></c:redirect>
</c:if>

<div class="doctor">
    <div class="wrap">
        <h2>Просмотр назначения</h2>

        <c:if test="${not empty succMsg}">
            <p class="center text-success fs-3">${succMsg}</p>
            <c:remove var="succMsg" scope="session" />
        </c:if>

        <c:if test="${not empty errorMsg}">
            <p class="center text-danger fs-3">${errorMsg}</p>
            <c:remove var="errorMsg" scope="session" />
        </c:if>

        <table class="table">
            <tr>
                <th scope="col">ФИО</th>
                <th scope="col">Пол</th>
                <th scope="col">Возраст</th>
                <th scope="col">Дата назначения</th>
                <th scope="col">Email</th>
                <th scope="col">Телефон</th>
                <th scope="col">Диагноз</th>
                <th scope="col">Статус</th>
                <th scope="col">Действие</th>
            </tr>

            <%
                Doctor doc = (Doctor) session.getAttribute("doctorObj");
                AppointmentDao dao = new AppointmentDao(DBConnect.getConnection());
                List<Appointment> list = dao.getAllAppointmentsByLoginDoctor(doc.getId());
                for (Appointment appointment : list) {
            %>

            <tr>
                <td><%= appointment.getFullName() %></td>
                <td><%= appointment.getGender() %></td>
                <td><%= appointment.getAge() %></td>
                <td><%= appointment.getAppointDate() %></td>
                <td><%= appointment.getEmail() %></td>
                <td><%= appointment.getPhone() %></td>
                <td><%= appointment.getDiseases() %></td>
                <td><%= appointment.getStatus() %></td>
                <td>
                    <a href="comment.jsp?id=<%= appointment.getId() %>" class="btn btn-sm btn-info">Комментарий</a>
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
