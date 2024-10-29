<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        div{
            text-align: center;
        }
        table{
            width: 220px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div>
    <h1>Редактировать дело</h1>
    <form action="/updateTodo" method="post">
        <input type="hidden" name="id" value="${editTodo.id}">
        <table>
            <tr>
                <td>To-do</td>
                <td><input type="text" name="todo" value="${editTodo.todo}"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Update"></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
