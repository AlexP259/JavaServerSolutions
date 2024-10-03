<%@ page import="com.entity.Student" %>
<%@ page import="com.dao.HomeworkDao" %>
<%@ page import="com.db.DBConnect" %>
<%@ page import="com.dao.TeacherDao" %>
<%@ page import="com.entity.Homework" %>
<%@ page import="java.util.List" %>
<%@ page import="com.entity.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="component/allcss.jsp"/>
</head>
<body>
<jsp:include page="component/navigation_menu.jsp"/>

<div class="appointment">
    <div class="wrap">
        <h2>Просмотр домашней работы</h2>

        <table class="table">
            <tr>
                <th scope="col">ФИО</th>
                <th scope="col">Группа</th>
                <th scope="col">Дата ДЗ</th>
                <th scope="col">Ссылка на ДЗ</th>
                <th scope="col">Имя преподавателя</th>
                <th scope="col">Статус</th>
            </tr>

            <%
                Student student = (Student) session.getAttribute("studentObj");
                HomeworkDao dao = new HomeworkDao(DBConnect.getConn());
                TeacherDao dao2 = new TeacherDao(DBConnect.getConn());
                List<Homework> list = dao.getAllHomeworksByLoginStudent(student.getId());
                for (Homework homework : list) {
                    Teacher t = dao2.getTeacherById(homework.getTeacherId());
            %>
            <tr>
                <td><%= homework.getFullName() %></td>
                <td><%= homework.getGroup() %></td>
                <td><%= homework.getHomeworkDate() %></td>
                <td><%= homework.getHomeworkFile() %></td>
                <td><%= t.getFullName() %></td>
                <td>
                    <% if ("В ожидании".equals(homework.getStatus()))
                    {
                    %>
                    <a href="#" class="btn btn-info">В ожидании</a>
                    <%
                    } else { %>
                    <%= homework.getStatus() %>
                    <% } %>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</div>

</body>
</html>
