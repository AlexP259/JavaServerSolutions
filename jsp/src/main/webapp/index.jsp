<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h3>Изучаем JSP</h3>

<%
    String title = "Hello world";
    int len = title.length();
    String header = "User list";
    String[] people = new String[]{"Tom", "Bob", "Sam"};
%>



<%!
    int a = 10;
    String name = "jsp";

    int square(int n){
        return n * n;
    }
%>

<p><%= square(6) %></p>
<ul>
    <%
        for (int i = 1; i < 5; i++) {
            out.println("<li>" + square(i) + "</li>");
        }
    %>
</ul>


<%
    out.println("a: " + a + "<br>");
    out.println("Name: " + name + "<br>");

    int b = 20;
    if(b < 100){
        out.println(b + " меньше 100");
    } else {
        out.println(b + " больше 100");
    }
%>




<p>Длина строки: <%= title %> = <%= len %> символов</p>
<p>Сегодня: <%= new java.util.Date() %></p>



<ul>
    <%
    for(String person: people) {
        out.println("<li>" + person + "</li>");
    }
    %>
</ul>

<p>2 + 2 = <%= 2 + 2 %></p>
<p>5 > 2 = <%= 5 > 2 %></p>
<p><%= new String("Hello").toUpperCase() %></p>
<p><%= LocalDate.now() %></p>


<%
    for (int i = 1; i < 5; i++) {
        out.println("<br>Hello " + i);
    }
%>
</body>
</html>









