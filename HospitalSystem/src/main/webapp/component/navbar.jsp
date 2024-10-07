<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="main navk">
    <div class="wrap">
        <ul class="menu">
            <li class="logo"><a href="index.jsp">Главная</a></li>

<%-- Навигационное меню если пользователь НЕ АВТОРИЗИРОВАН--%>
            <c:if test="${empty userObj}">
                <li><a href="admin_login.jsp">Администратор</a></li>
                <li><a href="doctor_login.jsp">Врач</a></li>
                <li><a href="user_appointment.jsp">Назначение</a></li>
                <li><a href="user_login.jsp">Пациент</a></li>
            </c:if>

<%-- Навигационное меню если пользователь АВТОРИЗИРОВАН--%>
            <c:if test="${not empty userObj}">
                <li><a href="user_appointment.jsp">Назначение</a></li>
                <li><a href="view_appointment.jsp">Просмотр назначения</a></li>
                <li>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            ${userObj.name}
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="../change_password.jsp">Изменить пароль</a></li>
                            <li><a class="dropdown-item" href="../userLogout">Выход</a></li>
                        </ul>
                    </div>
                </li>
            </c:if>

        </ul>
    </div>
</div>