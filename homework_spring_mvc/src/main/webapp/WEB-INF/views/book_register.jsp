<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Регистрация книги</h1>

<p>${msg}</p>

<form action="createBook" method="post">
    <p>
        <label for="title">Название книги</label>
        <input type="text" name="bookTitle" id="title">
    </p>
    <p>
        <label for="author">Автор книги</label>
        <input type="text" name="bookAuthor" id="author">
    </p>
    <p>
        <label for="year">Год издания книги</label>
        <input type="number" name="bookYear" id="year">
    </p>
    <p>
        <input type="submit" value="Отправить">
        <input type="reset" value="Очистить">
    </p>

</form>
</body>
</html>
