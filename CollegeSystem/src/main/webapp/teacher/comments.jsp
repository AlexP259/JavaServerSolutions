<%@ page import="com.dao.HomeworkDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page import="com.entity.Homework" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../component/allcss.jsp"/>
</head>
<body>
<jsp:include page="navigation_menu.jsp"/>

<c:if test="${empty teacherObj}">
    <c:redirect url="../teacher_login.jsp"></c:redirect>
</c:if>

<div class="comment">
    <div class="wrap">
        <h2>Комментарий студенту</h2>

        <%
            int id = Integer.parseInt(request.getParameter("id"));
            HomeworkDao dao = new HomeworkDao(DBConnect.getConn());
            Homework hom = dao.getHomeworkById(id);
        %>

        <form action="../updateStatus" method="post" class="row">

            <div class="col-md-6">
                <label for="name">Имя студента</label>
                <input type="text" readonly class="form-control" id="name" value="<%= hom.getFullName() %>">
            </div>

            <div class="col-md-6">
                <label for="group">Группа</label>
                <input type="text" readonly class="form-control" id="group" value="<%= hom.getGroup() %>">
            </div>

            <div class="col-md-6">
                <label for="phone">Телефон</label>
                <input type="text" readonly class="form-control" id="phone" value="<%= hom.getPhone() %>">
            </div>

            <div class="col-md-6">
                <label for="homework">Домашнее задание</label>
                <input type="text" readonly class="form-control" id="homework" value="<%= hom.getHomeworkFile() %>">
            </div>

            <div class="col-md-12">
                <label for="comment">Комментарий</label>
                <textarea name="comm" required class="form-control" id="comment"></textarea>
            </div>

            <input type="hidden" name="id" value="<%= hom.getId() %>">
            <input type="hidden" name="did" value="<%= hom.getTeacherId() %>">

            <div>
                <button class="btn but">Отправить</button>
            </div>

        </form>

    </div>
</div>

</body>
</html>
