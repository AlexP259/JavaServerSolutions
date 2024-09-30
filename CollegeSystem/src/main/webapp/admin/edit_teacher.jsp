<%@ page import="com.dao.SpecialityDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page import="com.entity.Speciality" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dao.TeacherDao" %>
<%@ page import="com.entity.Teacher" %>
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


        <%--        <button class="btn btn-info btn-teacher" type="button" data-bs-toggle="offcanvas"--%>
        <%--                data-bs-target="#staticBackdrop" aria-controls="staticBackdrop">--%>
        <%--            Добавить преподавателя--%>
        <%--        </button>--%>

        <div class="offcanvas offcanvas-start" data-bs-backdrop="static" tabindex="-1" id="staticBackdrop"
             aria-labelledby="staticBackdropLabel">
            <div class="offcanvas-header">
                <h3 class="offcanvas-title" id="staticBackdropLabel">Редактировать преподавателя</h3>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <div>

                    <c:if test="${not empty errorMsg}">
                        <p class="center text-danger fs-3">${errorMsg}</p>
                        <c:remove var="errorMsg"/>
                    </c:if>

                    <c:if test="${not empty succMsg}">
                        <p class="center text-success fs-3">${succMsg}</p>
                        <c:remove var="succMsg"/>
                    </c:if>

                    <%
                        int id = Integer.parseInt(request.getParameter("id"));
                        TeacherDao daoEdit = new TeacherDao(DBConnect.getConn());
                        Teacher teach = daoEdit.getTeacherById(id);
                    %>

                    <form action="../updateTeacher" method="post">
                        <div class="mb-3">
                            <label for="full">Полное имя</label>
                            <input type="text" required name="full_name" id="full" class="form-control"
                                   value="<%= teach.getFullName() %>">
                        </div>

                        <div class="mb-3">
                            <label for="dob">Дата рождения</label>
                            <input type="date" required name="dob" id="dob" class="form-control"
                                   value="<%= teach.getDob() %>">
                        </div>

                        <div class="mb-3">
                            <label for="qualit">Квалификация</label>
                            <input type="text" required name="qualification" id="qualit" class="form-control"
                                   value="<%= teach.getQualification() %>">
                        </div>

                        <div class="mb-3">
                            <label for="special">Специальность</label>
                            <select required name="spec" id="special" class="form-control">
                                <option><%= teach.getSpeciality() %>
                                </option>
                                <%
                                    SpecialityDao dao = new SpecialityDao(DBConnect.getConn());
                                    List<Speciality> list = dao.getAllSpeciality();
                                    for (Speciality s : list) {
                                %>
                                <option value="<%= s.getSpecialityName() %>"><%= s.getSpecialityName() %>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="email">Email</label>
                            <input type="email" required name="email" id="email" class="form-control"
                                   value="<%= teach.getEmail() %>">
                        </div>

                        <div class="mb-3">
                            <label for="tel">Телефон</label>
                            <input type="tel" required name="mobno" id="tel" class="form-control"
                                   value="<%= teach.getMobNo() %>">
                        </div>

                        <div class="mb-3">
                            <label for="psw">Пароль</label>
                            <input type="password" required name="password" id="psw" class="form-control"
                                   value="<%= teach.getPassword() %>">
                        </div>

                        <input type="hidden" name="id" value="<%= teach.getId() %>">

                        <button class="btn btn-info">Обновить</button>

                    </form>
                </div>
            </div>
        </div>

        <h2>Данные преподавателей</h2>
        <table class="table">
            <tr>
                <th scope="col">Имя</th>
                <th scope="col">Дата рождения</th>
                <th scope="col">Квалификация</th>
                <th scope="col">Специализация</th>
                <th scope="col">Email</th>
                <th scope="col">Телефон</th>
                <th scope="col">Действие</th>
            </tr>
            <%
                TeacherDao dao2 = new TeacherDao(DBConnect.getConn());
                List<Teacher> list2 = dao2.getAllTeachers();
                for (Teacher t : list2) {
            %>
            <tr>
                <td><%= t.getFullName() %>
                </td>
                <td><%= t.getDob() %>
                </td>
                <td><%= t.getQualification() %>
                </td>
                <td><%= t.getSpeciality() %>
                </td>
                <td><%= t.getEmail() %>
                </td>
                <td><%= t.getMobNo() %>
                </td>
                <td>
                    <a href="edit_teacher.jsp?id=<%= t.getId() %>" class="btn btn-sm btn-info">Изменить</a>
                    <a href="../deleteTeacher?id=<%= t.getId() %>" class="btn btn-sm btn-danger">Удалить</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>

    </div>
</section>

</body>
</html>
