<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Регистрация пользователя</h1>

<p>${msg}</p>

<form action="createUser" method="post">
    <p>
        <label for="fio">ФИО: </label>
        <input type="text" name="fullName" id="fio">
    </p>
    <p>
        <label for="email">Email: </label>
        <input type="email" name="email" id="email">
    </p>
    <p>
        <label for="password">Пароль: </label>
        <input type="password" name="password" id="password">
    </p>
    <p>
        <label for="check">
            <input type="checkbox" name="check" id="check">
            Проверка
        </label>
    </p>
    <p>
        <button>Регистрация</button>
    </p>
</form>



</body>
</html>
