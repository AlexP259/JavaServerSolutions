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

<c:if test="${empty teacherObj}">
    <c:redirect url="../teacher_login.jsp"></c:redirect>
</c:if>

<section class="wrap">
    <h2>Редактирование профиля</h2>

    <c:if test="${not empty errorMsg}">
        <p class="center text-danger fs-3">${errorMsg}</p>
        <c:remove var="errorMsg"/>
    </c:if>

    <c:if test="${not empty succMsg}">
        <p class="center text-success fs-3">${succMsg}</p>
        <c:remove var="succMsg"/>
    </c:if>

    <div class="row">
        <div class="col-md-4">
            <div class="card point-card">
                <div class="card-body">
                    <form action="teacherChangePassword" method="post">
                        <div>
                            <label for="old">Введите старый пароль:</label>
                            <input type="password" name="oldPassword" class="form-control" id="old" required>
                        </div>
                        <div>
                            <label for="new">Введите новый пароль:</label>
                            <input type="password" name="newPassword" class="form-control" id="new" required>
                        </div>
                        <input type="hidden" name="sid" value="${teacherObj.getId()}">
                        <button class="btn button">Изменить пароль</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-8">
        <div class="card point-card">
            <div class="card-body">
                <form action="../teacherUpdateProfile" method="post">
                    <div class="mb-3">
                        <label for="full">Полное имя</label>
                        <input type="text" required name="full_name" id="full" class="form-control" value="${teacherObj.fullName}">
                    </div>

                    <div class="mb-3">
                        <label for="dob">Дата рождения</label>
                        <input type="date" required name="dob" id="dob" class="form-control" value="${teacherObj.dob}">
                    </div>

                    <div class="mb-3">
                        <label for="qualit">Квалификация</label>
                        <input type="text" required name="qualification" id="qualit" class="form-control" value="${teacherObj.qualification}">
                    </div>

                    <div class="mb-3">
                        <label for="special">Специальность</label>
                        <select required name="spec" id="special" class="form-control">
                            <option hidden>${teacherObj.speciality}</option>
                            <%
                                SpecialityDao dao = new SpecialityDao(DBConnect.getConn());
                                List<Speciality> list = dao.getAllSpeciality();
                                for (Speciality s : list) {
                            %>
                            <option><%= s.getSpecialityName() %></option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="email">Email</label>
                        <input type="email" readonly name="email" id="email" class="form-control" value="${teacherObj.email}">
                    </div>

                    <div class="mb-3">
                        <label for="tel">Телефон</label>
                        <input type="tel" required name="mobno" id="tel" class="form-control" value="${teacherObj.mobNo}">
                    </div>

                    <input type="hidden" name="id" value="${teacherObj.id}">

                    <button class="btn btn-info">Обновить</button>

                </form>
            </div>
        </div>
    </div>
    </div>



</section>

</body>
</html>
