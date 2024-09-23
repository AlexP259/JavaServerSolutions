<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация студента</title>
    <jsp:include page="component/allcss.jsp" />
</head>
<body>
<jsp:include page="component/navigation_menu.jsp" />

<div class="height">
    <section class="form">
        <h2>Регистрация студента</h2>

<%--        <%--%>
<%--            if(session.getAttribute("sucMsg") != null){--%>
<%--                out.println("<p class='center text-success fs-3'>" + session.getAttribute("sucMsg") + "</p>");--%>
<%--            }--%>
<%--        %>--%>

        <c:if test="${not empty sucMsg}">
            <p class="center text-success fs-3">${sucMsg}</p>
            <c:remove var="sucMsg"/>
        </c:if>

        <c:if test="${not empty errorMsg}">
            <p class="center text-danger fs-3">${errorMsg}</p>
            <c:remove var="errorMsg"/>
        </c:if>

        <form action="studentRegister" method="post">

            <div>
                <label for="fio">ФИО:</label>
                <input type="text" name="fullName" class="form-control" id="fio" required>
            </div>

            <div>
                <label for="email-address">Email:</label>
                <input type="email" name="email" class="form-control" id="email-address" required>
            </div>

            <div>
                <label for="psw">Пароль:</label>
                <input type="password" name="password" class="form-control" id="psw" required>
            </div>

            <button class="btn button">Регистрация</button>

        </form>
    </section>
</div>

<jsp:include page="component/footer.jsp" />
</body>
</html>
