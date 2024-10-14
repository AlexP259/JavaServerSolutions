<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Книга</h2>
<hr>
<ul>
  <li>Название книги: ${book.bookTitle}</li>
  <li>Автор книги: ${book.bookAuthor}</li>
  <li>Год издания: ${book.bookYear}</li>
</ul>

<a href="main">На главную</a>
</body>
</html>
