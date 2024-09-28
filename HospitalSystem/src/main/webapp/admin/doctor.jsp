<%@ page import="com.dao.SpecialistDao, com.db.DBConnect" %>
<%@ page import="com.entity.Specialist" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../component/allcss.jsp" />
</head>
<body>
<jsp:include page="navbar.jsp" />

<section class="doctor">
    <div class="wrap">
        <h2>Добавить врача</h2>

        <c:if test="${not empty errorMsg}">
            <p class="center text-danger fs-3">${errorMsg}</p>
            <c:remove var="errorMsg" scope="session" />
        </c:if>

        <c:if test="${not empty succMsg}">
            <p class="center text-success fs-3">${succMsg}</p>
            <c:remove var="succMsg" scope="session" />
        </c:if>

        <form action="../addDoctor" method="post">
            <div class="mb-3">
                <label for="full">Полное имя</label>
                <input type="text" required name="full_name" class="form-control" id="full">
            </div>

            <div class="mb-3">
                <label for="dob">Дата рождения</label>
                <input type="date" required name="dob" class="form-control" id="dob">
            </div>

            <div class="mb-3">
                <label for="qualit">Квалификация</label>
                <input type="text" required name="qualification" class="form-control" id="qualit">
            </div>

            <div class="mb-3">
                <label for="special">Специалист</label>
                <select name="spec" id="special" required class="form-control">
                    <option value="">-- Выбор специалиста --</option>
                    <%
                        SpecialistDao dao = new SpecialistDao(DBConnect.getConnection());
                        List<Specialist> list = dao.getAllSpecialist();
                        for (Specialist s : list){
                    %>
                    <option value=""><%= s.getSpecialistName() %></option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="mb-3">
                <label for="email">Email</label>
                <input type="email" required name="email" class="form-control" id="email">
            </div>

            <div class="mb-3">
                <label for="tel">Телефон</label>
                <input type="tel" required name="mobno" class="form-control" id="tel">
            </div>

            <div class="mb-3">
                <label for="psw">Пароль</label>
                <input type="password" required name="password" class="form-control" id="psw">
            </div>

            <button class="btn btn-info">Отправить</button>
        </form>

    </div>
</section>

</body>
</html>
