<%@ page import="com.entity.Teacher" %>
<%@ page import="com.dao.TeacherDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../component/allcss.jsp" />
</head>
<body>

<jsp:include page="navigation_menu.jsp" />

<c:if test="${empty teacherObj}">
    <c:redirect url="../teacher_login.jsp"></c:redirect>
</c:if>

<section class="teacher">
    <div class="wrap">
        <h2>Панель преподавателя</h2>

        <%
            Teacher t = (Teacher) session.getAttribute("teacherObj");
            TeacherDao dao = new TeacherDao(DBConnect.getConn());
        %>

        <div class="teacher__block">
            <div class="teacher__element">
                <h3>Преподаватель</h3>
                <h4><%= dao.countTeachers() %></h4>
            </div>
            <div class="teacher__element">
                <h3>Домашняя работа</h3>
                <h4><%= dao.countHomeworksTeacherId(t.getId()) %></h4>
            </div>
        </div>

    </div>
</section>
</body>
</html>
