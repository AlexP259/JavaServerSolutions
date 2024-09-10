<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 11.09.2024
  Time: 0:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String firstName = request.getParameter("name1");
        out.println(firstName);
    %>

    <%
        String cname = String.valueOf(session.getAttribute("session_name"));
        out.println(cname);
    %>
</body>
</html>

