<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../component/allcss.jsp" />
</head>
<body>
<jsp:include page="navbar.jsp" />
<section class="admin">
    <div class="wrap">
        <h2>Панель администратора</h2>
        <div class="admin__block">
            <div class="admin__element">
                <img src="../img/admin_1.png" alt="">
                <h3>Врач</h3>
                <p>5</p>
            </div>
            <div class="admin__element">
                <img src="../img/admin_2.png" alt="">
                <h3>Пользователи</h3>
                <p>43</p>
            </div>
            <div class="admin__element">
                <img src="../img/admin_3.png" alt="">
                <h3>Общее</h3>
                <p>456</p>
            </div>
            <div class="admin__element">
                <img src="../img/admin_4.png" alt="">
                <h3>Специалист</h3>
                <p>34</p>
            </div>
        </div>
    </div>
</section>
</body>
</html>
