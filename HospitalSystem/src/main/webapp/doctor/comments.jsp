<%@ page import="com.dao.AppointmentDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page import="com.entity.Appointment" %>
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

<div class="comment">
    <div class="wrap">
        <h2>Комментарий пациенту</h2>

        <%
            int id = Integer.parseInt(request.getParameter("id"));
            AppointmentDao dao = new AppointmentDao(DBConnect.getConnection());
            Appointment app = dao.getAppointmentById(id);
        %>

        <form action="../updateStatus" method="post" class="row">

            <div class="col-md-6">
                <label for="name">Имя пациента</label>
                <input type="text" readonly class="form-control" id="name" value="<%= app.getFullName() %>">
            </div>

            <div class="col-md-6">
                <label for="age">Возраст</label>
                <input type="text" readonly class="form-control" id="age" value="<%= app.getAge() %>">
            </div>

            <div class="col-md-6">
                <label for="phone">Телефон</label>
                <input type="text" readonly class="form-control" id="phone" value="<%= app.getPhone() %>">
            </div>

            <div class="col-md-6">
                <label for="diseases">Назначение</label>
                <input type="text" readonly class="form-control" id="diseases" value="<%= app.getDiseases() %>">
            </div>

            <div class="col-md-12">
                <label for="comment">Комментарий</label>
                <textarea name="comm" required class="form-control" id="comment"></textarea>
            </div>

            <input type="hidden" name="id" value="<%= app.getId() %>">
            <input type="hidden" name="did" value="<%= app.getDoctorId() %>">

            <div>
                <button class="btn but">Отправить</button>
            </div>

        </form>

    </div>
</div>
</body>
</html>
