<%--
  Created by IntelliJ IDEA.
  User: Aliaksei Trushkou
  Date: 23.10.2026
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Books</title>
</head>
<body>

<h1 style="text-align: center;">Electronic library</h1>

<h3>Available books:</h3>
<c:choose>
    <c:when test="${not empty books}">
        <ul>
            <c:forEach var="book" items="${books}">
                <li>
                    <c:out value="${book}"/>
                    <c:url var="downloadUrl" value="/book">
                        <c:param name="name" value="${book}"/>
                    </c:url>
                    <a href="${downloadUrl}">Download</a>
                </li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <p>No books available.</p>
    </c:otherwise>
</c:choose>
<p>
    <a href="${pageContext.request.contextPath}/load-book">
        Load book
    </a>
</p>
</body>
</html>
