<%--
  Created by IntelliJ IDEA.
  User: Aliaksei Trushkou
  Date: 23.10.2026
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Load book</title>
</head>
<body>

<h1 style="text-align: center;">Electronic library</h1>

<form action="${pageContext.request.contextPath}/load-book"
      method="post"
      enctype="multipart/form-data">

    <input type="file" id="book" name="book" required>
    <button type="submit">Load book</button>

</form>

<p>
    <a href="${pageContext.request.contextPath}/book">
        Back to books
    </a>
</p>

</body>
</html>
