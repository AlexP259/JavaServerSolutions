<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 11.09.2024
  Time: 1:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="application.Calculate" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        for (int i = 2; i < 10; i++) {
            out.println("<p>" + i + "<sup>2</sup> = " + new Calculate().square(i) + "/p");
        }
    %>
</body>
</html>
