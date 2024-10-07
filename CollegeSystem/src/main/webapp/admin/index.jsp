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

<c:if test="${empty adminObj}">
    <c:redirect url="../admin_login.jsp"></c:redirect>
</c:if>

<section class="admin">
    <div class="wrap">
        <h2>Панель администратора</h2>

        <c:if test="${not empty errorMsg}">
            <p class="center text-danger fs-3">${errorMsg}</p>
            <c:remove var="errorMsg"/>
        </c:if>

        <c:if test="${not empty succMsg}">
            <p class="center text-success fs-3">${succMsg}</p>
            <c:remove var="succMsg"/>
        </c:if>

        <%
            TeacherDao dao = new TeacherDao(DBConnect.getConn());
        %>

        <div class="admin__block">
            <div class="admin__element">
                <img src="../img/prepod.jpg" alt="">
                <h3>Преподаватель</h3>
                <p><%= dao.countTeachers() %></p>
            </div>
            <div class="admin__element">
                <img src="../img/homework.jpg.jpg" alt="">
                <h3>Домашние задания</h3>
                <p><%= dao.countHomeworks() %></p>
            </div>
            <div class="admin__element">
                <img src="../img/student.jpg" alt="">
                <h3>Студент</h3>
                <p><%= dao.countStudents() %></p>
            </div>
            <div class="admin__element">
                <img src="../img/laborant.jpg" alt="" data-bs-toggle="modal" data-bs-target="#exampleModal">
                <h3>Специальность</h3>
                <p><%= dao.countSpecialities() %></p>
            </div>
        </div>
    </div>

    <!-- Button trigger modal -->
<%--    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">--%>
<%--        Launch demo modal--%>
<%--    </button>--%>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="../addSpeciality">
                        <div class="form-group">
                            <label for="spec">Введите имя специалиста</label>
                            <input type="text" name="specName" id="spec" class="form-control">
                        </div>
                        <div class="text-center mt-3">
                            <button class="btn btn-info">Добавить</button>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</section>


</body>
</html>
