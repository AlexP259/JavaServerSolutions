<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>User Login Page</title>
    <jsp:include page="component/allcss.jsp"/>
</head>
<body>
<jsp:include page="component/navbar.jsp"/>

<div class="height">
    <section class="form">
        <h2>Авторизация пациента</h2>

        <c:if test="${not empty succMsg}">
            <p class="center text-success fs-3">${succMsg}</p>
            <c:remove var="succMsg" scope="session" />
        </c:if>

        <c:if test="${not empty errorMsg}">
            <p class="center text-danger fs-3">${errorMsg}</p>
            <c:remove var="errorMsg" scope="session" />
        </c:if>

        <form action="userLogin" method="post">
            <div>
                <label for="email-address">Email:</label>
                <input type="email" name="email" class="form-control" id="email-address" required>
            </div>
            <div>
                <label for="psw">Пароль:</label>
                <input type="password" name="password" class="form-control" id="psw" required>
            </div>

            <button class="btn button">Авторизация</button>
        </form>
        <p class="center">У вас нет аккаунта? <a href="sign_up.jsp" class="text-decoration-none">Создать аккаунт</a></p>
    </section>
</div>

<jsp:include page="component/footer.jsp"/>

</body>
</html>
