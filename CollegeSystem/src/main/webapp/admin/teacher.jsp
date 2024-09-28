<%@ page import="com.dao.SpecialityDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page import="com.entity.Speciality" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../component/allcss.jsp"/>
</head>
<body>
<jsp:include page="navigation_menu.jsp"/>

<section class="teacher">
    <div class="wrap">
        <h2>Добавить преподавателя</h2>

        <c:if test="${not empty errorMsg}">
            <p class="center text-danger fs-3">${errorMsg}</p>
            <c:remove var="errorMsg"/>
        </c:if>

        <c:if test="${not empty succMsg}">
            <p class="center text-success fs-3">${succMsg}</p>
            <c:remove var="succMsg"/>
        </c:if>

        <form action="../addTeacher" method="post">
            <div class="mb-3">
                <label for="full">Полное имя</label>
                <input type="text" required name="full_name" id="full" class="form-control">
            </div>

            <div class="mb-3">
                <label for="dob">Дата рождения</label>
                <input type="date" required name="dob" id="dob" class="form-control">
            </div>

            <div class="mb-3">
                <label for="qualit">Квалификация</label>
                <input type="text" required name="qualification" id="qualit" class="form-control">
            </div>

            <div class="mb-3">
                <label for="special">Специальность</label>
                <select type="text" required name="spec" id="special" class="form-control">
                    <option value="">-- Выбор специальности --</option>
                    <%
                        SpecialityDao dao = new SpecialityDao(DBConnect.getConn());
                        List<Speciality> list = dao.getAllSpeciality();
                        for (Speciality s : list) {
                    %>
                    <option value=""><%= s.getSpecialityName() %>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="mb-3">
                <label for="email">Email</label>
                <input type="email" required name="email" id="email" class="form-control">
            </div>

            <div class="mb-3">
                <label for="tel">Телефон</label>
                <input type="tel" required name="mobno" id="tel" class="form-control">
            </div>

            <div class="mb-3">
                <label for="psw">Пароль</label>
                <input type="password" required name="password" id="psw" class="form-control">
            </div>

            <button class="btn btn-info">Отправить</button>

        </form>
    </div>
</section>

</body>
</html>
