<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../component/allcss.jsp" />
</head>
<body>
<jsp:include page="navbar.jsp" />

<c:if test="${empty adminObj}">
    <c:redirect url="../admin_login.jsp"></c:redirect>
</c:if>

<section class="admin">
    <div class="wrap">
        <h2>Панель администратора</h2>

        <c:if test="${not empty succMsg}">
            <p class="center text-success fs-3">${succMsg}</p>
            <c:remove var="succMsg" scope="session" />
        </c:if>

        <c:if test="${not empty errorMsg}">
            <p class="center text-danger fs-3">${errorMsg}</p>
            <c:remove var="errorMsg" scope="session" />
        </c:if>

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
            <div class="admin__element" data-bs-toggle="modal" data-bs-target="#exampleModal">
                <img src="../img/admin_4.png" alt="">
                <h3>Специалист</h3>
                <p>34</p>
            </div>
        </div>
    </div>

    <!-- Button trigger modal
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Launch demo modal
    </button> -->

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <form action="../addSpecialist" method="post">
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
