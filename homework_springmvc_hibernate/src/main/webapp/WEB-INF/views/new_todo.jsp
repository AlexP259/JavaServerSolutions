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
    <h1>Новое дело</h1>

    <form action="save" method="post">
        <table>
            <tr>
                <td>To-do</td>
                <td><input type="text" name="todo"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
