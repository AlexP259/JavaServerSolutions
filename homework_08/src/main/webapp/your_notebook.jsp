<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <jsp:include page="components/header.jsp"/>

    <%
        int ssdSize = Integer.parseInt(request.getParameter("ssdSize"));
        request.setAttribute("ssdSize", ssdSize);   // чисто ради прикола засунем переменную в атрибут запроса
    %>

    <div class="wrap height">
        <h2>Ваш выбор комплектующих:</h2>
        <p>Название производителя: ${param.maker}</p>
        <p>Объем SSD: ${requestScope.ssdSize}</p>    <!--правильно искать атрибут надо через Request scope (атрибуты запроса),
Session scope (атрибуты сессии), Application scope (атрибуты приложения) или Parameters (параметры запроса)-->
        <p>Тип видеокарты: ${param.gpuType}</p>
        <p>Модель процессора: ${param.cpuModel}</p>
        <p>Операционная система: ${param.OS}</p>
        <p>Тип матрицы: ${param.matrixType}</p>
        <p>Разрешение экрана: ${param.screenResolution}</p>
        <p>Сенсорный экран: ${param.sensor}</p>
        <p>Подсветка клавиатуры: ${param.keyboardLight}</p>

    </div>

    <jsp:include page="components/footer.jsp"/>
</div>
</body>
</html>
