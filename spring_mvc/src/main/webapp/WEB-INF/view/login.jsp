<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Страница авторизации</h1>

<%--<%--%>
<%--    String address = (String) request.getAttribute("address");--%>
<%--    int house = (Integer) request.getAttribute("house");--%>
<%--    List<String> nameList = (List<String>) request.getAttribute("nameList");--%>
<%--%>--%>

<%--<h4>Address: <%= address %></h4>--%>
<%--<h4>House: <%= house %></h4>--%>
<%--<h4>Name List: <%= nameList %></h4>--%>


<%--<ul>--%>
<%--    <%--%>
<%--        for(String s : nameList){--%>
<%--    %>--%>
<%--    <li><%= s %></li>--%>
<%--    <%--%>
<%--        }--%>
<%--    %>--%>
<%--</ul>--%>


<h4>Address: ${address} </h4>
<h4>House: ${house} </h4>
<h3>Name List: ${nameList} </h3>


</body>
</html>
