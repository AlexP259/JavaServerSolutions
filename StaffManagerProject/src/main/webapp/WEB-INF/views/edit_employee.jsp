<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <style>
    div{
      text-align: center;
    }
    table{
      width: 200px;
      margin: 0 auto;
    }
  </style>
</head>
<body>
<div>
  <h1>Редактировать сотрудника</h1>
  <form action="/updateEmployee" method="post">
    <input type="hidden" name="id" value="${editEmployee.id}">
    <table>
      <tr>
        <td>Name</td>
        <td><input type="text" name="name" value="${editEmployee.name}"></td>
      </tr>
      <tr>
        <td>Email</td>
        <td><input type="text" name="email" value="${editEmployee.email}"></td>
      </tr>
      <tr>
        <td>Address</td>
        <td><input type="text" name="address" value="${editEmployee.address}"></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" value="Update"></td>
      </tr>
    </table>
  </form>
</div>

</body>
</html>
