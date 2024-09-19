<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <title>Title</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
<div class="container">
    <jsp:include page="header.jsp"/>
    <section>
        <form action="user.jsp" method="post">
            <p>Имя: <input type="text" name="username"></p>
            <p>Пол: <br>
                <label for="female">
                    <input type="radio" name="gender" value="Ж" id="female">Ж
                </label>
                <br>
                <label for="male">
                    <input type="radio" name="gender" value="М" id="male">М
                </label>
            </p>
            <p>Страна:
                <select name="country" id="">
                    <option value="ru">Россия</option>
                    <option>Беларусь</option>
                    <option>Турция</option>
                </select>
            </p>
            <p>Курс обучения: <br>
                <label for="java">
                    <input type="checkbox" id="java" name="courses" value="Java"> Java <br>
                </label>
                <label for="html">
                    <input type="checkbox" id="html" name="courses" value="HTML"> HTML <br>
                </label>
                <label for="js">
                    <input type="checkbox" id="js" name="courses" value="JavaScript"> JS <br>
                </label>
            </p>

            <input type="submit" value="Подтвердить">
        </form>
    </section>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>