<%@ page import="com.dao.TeacherDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page import="com.entity.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="component/allcss.jsp"/>
</head>
<body>
<jsp:include page="component/navigation_menu.jsp"/>

<div class="homework">
    <div class="wrap">
        <h2>Панель домашней работы</h2>

        <c:if test="${not empty errorMsg}">
            <p class="center text-danger fs-3">${errorMsg}</p>
            <c:remove var="errorMsg" scope="session" />
        </c:if>

        <c:if test="${not empty succMsg}">
            <p class="center text-success fs-3">${succMsg}</p>
            <c:remove var="succMsg" scope="session" />
        </c:if>

        <form action="addHomework" method="post" class="row g-3">

            <input type="hidden" name="studentid" value="${studentObj.id}">

            <div class="col-md-6">
                <label for="fio" class="form-label">ФИО:</label>
                <input type="text" name="fullName" class="form-control" id="fio" required>
            </div>

            <div class="col-md-6">
                <label for="group" class="form-label">Группа:</label>
                <input type="text" name="group" class="form-control" id="group" required>
            </div>

            <div class="col-md-6">
                <label for="date" class="form-label">Дата сдачи:</label>
                <input type="date" name="homework_date" class="form-control" id="date" required>
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
                <label for="homework_file" class="form-label">Ссылка на файл с ДЗ:</label>
                <input type="text" name="homework_file" class="form-control" id="homework_file" required>
            </div>

            <div class="col-md-6">
                <label for="teacher" class="form-label">Преподаватель:</label>
                <select name="teacher" class="form-control" id="teacher" required>
                    <option value="" disabled selected hidden>-- Выбор --</option>
                    <%
                        TeacherDao dao = new TeacherDao(DBConnect.getConn());
                        List<Teacher> list = dao.getAllTeachers();
                        for (Teacher teacher : list) {
                    %>
                    <option value="<%= teacher.getId() %>"><%= teacher.getFullName() %> (<%= teacher.getSpeciality() %>
                        )
                    </option>
                    <%
                        }
                    %>

                </select>
            </div>
            

            <c:if test="${empty studentObj}">
                <a href="student_login.jsp" class="btn btn-info">Отправить</a>
            </c:if>

            <c:if test="${not empty studentObj}">
                <button class="btn button">Отправить</button>
            </c:if>


        </form>
    </div>
</div>
</body>
</html>
