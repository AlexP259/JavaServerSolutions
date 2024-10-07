<%@ page import="com.dao.HomeworkDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page import="com.dao.TeacherDao" %>
<%@ page import="com.entity.Homework" %>
<%@ page import="java.util.List" %>
<%@ page import="com.entity.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../component/allcss.jsp"/>
</head>
<body>
<jsp:include page="navigation_menu.jsp"/>

<%--<c:if test="${empty teacherObj}">--%>
<%--    <c:redirect url="../teacher_login.jsp"></c:redirect>--%>
<%--</c:if>--%>

<div class="student">
    <div class="wrap">
        <h2>Просмотр домашней работы</h2>

<%--        <c:if test="${not empty succMsg}">--%>
<%--        <p class="center text-success fs-3">${succMsg}</p>--%>
<%--            <c:remove var="succMsg" scope="session"/>--%>
<%--        </c:if>--%>

<%--        <c:if test="${not empty errorMsg}">--%>
<%--        <p class="center text-danger fs-3">${errorMsg}</p>--%>
<%--            <c:remove var="errorMsg" scope="session"/>--%>
<%--        </c:if>--%>

        <table class="table">
            <tr>
                <th scope="col">ФИО</th>
                <th scope="col">Группа</th>
                <th scope="col">Email</th>
                <th scope="col">Телефон</th>
                <th scope="col">Дата ДЗ</th>
                <th scope="col">Ссылка на ДЗ</th>
                <th scope="col">Имя преподавателя</th>
                <th scope="col">Статус</th>
            </tr>
            <%
                HomeworkDao dao = new HomeworkDao(DBConnect.getConn());
                TeacherDao dao2 = new TeacherDao(DBConnect.getConn());
                List<Homework> list = dao.getAllHomeworks();
                for (Homework homework : list) {
                    Teacher t = dao2.getTeacherById(homework.getTeacherId());
            %>
            <tr>
                <td><%= homework.getFullName() %>></td>
                <td><%= homework.getGroup() %>></td>
                <td><%= homework.getEmail() %>></td>
                <td><%= homework.getPhone() %>></td>
                <td><%= homework.getHomeworkDate() %>></td>
                <td><%= homework.getHomeworkFile() %>></td>
                <td><%= t.getFullName() %>></td>
                <td><%= homework.getStatus() %>></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</div>
</body>
</html>















