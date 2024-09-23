<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Авторизация преподавателя</title>
    <jsp:include page="component/allcss.jsp" />
  </head>
  <body>
  <jsp:include page="component/navigation_menu.jsp" />

  <div class="height">
    <section class="form">
      <h2>Авторизация преподавателя</h2>
      <form action="teacherLogin" method="post">

        <div>
          <label for="email-address">Email:</label>
          <input type="email" name="email" class="form-control" id="email-address" required>
        </div>

        <div>
          <label for="psw">Пароль:</label>
          <input type="password" name="password" class="form-control" id="psw" required>
        </div>

        <button class="btn button">Авторизация</button>

      </form>
    </section>
  </div>

  <jsp:include page="component/footer.jsp" />
  </body>
</html>
