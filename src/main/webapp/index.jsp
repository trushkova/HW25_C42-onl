<%--
  Created by IntelliJ IDEA.
  User: Aliaksei Trushkou
  Date: 23.10.2026
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Electronic library</title>
</head>
<body>

<h1 style="text-align: center;">Electronic library</h1>

<a href="${pageContext.request.contextPath}/book">
    Books
</a>
<br><br>

<a href="${pageContext.request.contextPath}/load-book">
    Load book
</a>
</body>
</html>
