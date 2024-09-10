<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 11.09.2024
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String name = "hello";
        out.println(name);
    %>


    <%
        session.setAttribute("session_name", "Start Programming");
    %>


    <form action="output.jsp" method="get">
        <input type="text" name="name1" placeholder="Введите имя">
        <input type="submit" value="Кнопка">
    </form>
</body>
</html>
