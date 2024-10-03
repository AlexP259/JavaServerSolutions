<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../component/allcss.jsp"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<c:if test="${empty doctorObj}">
    <c:redirect url="../doctor_login.jsp"></c:redirect>
</c:if>

<section class="doctor">
    <div class="wrap">
        <h2>Панель врача</h2>

        <div class="doctor__block">
            <div class="doctor__element">
                <h3>Врач</h3>
                <h4>5</h4>
            </div>
            <div class="doctor__element">
                <h3>Назначение</h3>
                <h4>12</h4>
            </div>
        </div>

    </div>
</section>
</body>
</html>
