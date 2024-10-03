<%@ page import="com.dao.DoctorDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page import="com.entity.Doctor" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="component/allcss.jsp"/>
</head>
<body>
<jsp:include page="component/navbar.jsp"/>

<div class="appointment">
    <div class="wrap">
        <h2>Панель назначения</h2>

        <c:if test="${not empty errorMsg}">
            <p class="center text-danger fs-3">${errorMsg}</p>
            <c:remove var="errorMsg" scope="session" />
        </c:if>

        <c:if test="${not empty succMsg}">
            <p class="center text-success fs-3">${succMsg}</p>
            <c:remove var="succMsg" scope="session" />
        </c:if>

        <form action="addAppointment" method="post" class="row g-3">

            <input type="hidden" name="userid" value="${userObj.id}">

            <div class="col-md-6">
                <label for="fio" class="form-label">ФИО:</label>
                <input type="text" name="fullName" class="form-control" id="fio" required>
            </div>

            <div class="col-md-6">
                <label for="gender" class="form-label">Пол:</label>
                <select name="gender" class="form-control" id="gender" required>
                    <option value="male">Мужской</option>
                    <option value="female">Женский</option>
                </select>
            </div>

            <div class="col-md-6">
                <label for="age" class="form-label">Возраст:</label>
                <input type="number" name="age" class="form-control" id="age" required>
            </div>

            <div class="col-md-6">
                <label for="date" class="form-label">Дата назначения:</label>
                <input type="date" name="appoint_date" class="form-control" id="date" required>
            </div>

            <div class="col-md-6">
                <label for="email" class="form-label">E-mail:</label>
                <input type="email" name="email" class="form-control" id="email" required>
            </div>

            <div class="col-md-6">
                <label for="tel" class="form-label">Телефон:</label>
                <input type="text" name="number" class="form-control" id="tel" required>
            </div>

            <div class="col-md-6">
                <label for="diseases" class="form-label">Диагноз:</label>
                <input type="text" name="diseases" class="form-control" id="diseases" required>
            </div>

            <div class="col-md-6">
                <label for="doctor" class="form-label">Врач:</label>
                <select name="doct" class="form-control" id="doctor" required>
                    <option value="" disabled selected hidden>-- Выбор --</option>
                    <%
                        DoctorDao dao = new DoctorDao(DBConnect.getConnection());
                        List<Doctor> list = dao.getAllDoctors();
                        for (Doctor doc : list) {
                    %>
                    <option value="<%= doc.getId() %>"><%= doc.getFullName() %> (<%= doc.getSpecialist() %> )</option>
                    <%
                        }
                    %>

                </select>
            </div>

            <div class="col-md-12">
                <label for="address" class="form-label">Адрес:</label>
                <textarea name="address" class="form-control" id="address" required rows="3"></textarea>
            </div>

            <c:if test="${empty userObj}">
                <a href="user_login.jsp" class="btn btn-info">Отправить</a>
            </c:if>

            <c:if test="${not empty userObj}">
                <button class="btn button">Отправить</button>
            </c:if>


        </form>
    </div>
</div>

</body>
</html>


















