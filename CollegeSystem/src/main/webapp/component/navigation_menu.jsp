<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="main">
    <div class="wrap">
        <ul class="menu">
            <li class="logo"><a href="../index.jsp">Главная</a></li>

            <c:if test="${empty studentObj}">
                <li><a href="admin_login.jsp">Администратор</a></li>
                <li><a href="teacher_login.jsp">Преподаватель</a></li>
                <li><a href="student_homework.jsp">Домашнее задание</a></li>
                <li><a href="student_login.jsp">Студент</a></li>
            </c:if>

            <c:if test="${not empty studentObj}">
                <li><a href="student_homework.jsp">Домашнее задание</a></li>
                <li><a href="view_homework.jsp">Просмотр ДЗ</a></li>
                <li>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            ${studentObj.name}
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Изменить пароль</a></li>
                            <li><a class="dropdown-item" href="../studentLogout">Выход</a></li>
                        </ul>
                    </div>
                </li>
            </c:if>

        </ul>
    </div>
</div>
