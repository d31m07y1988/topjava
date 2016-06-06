<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<c:if test="${not empty meals}">
    <ul>
    <c:forEach items="${meals}" var="meal">
        <c:if test="${meal.exceed}">
            <li style="color: blue">
        </c:if>
        <c:if test="${not meal.exceed}">
            <li style="color: red">
        </c:if>
            <c:out value="${meal}"/>
        </li>
    </c:forEach>
    </ul>
</c:if>
</body>
</html>
