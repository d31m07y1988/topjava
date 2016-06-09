<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<c:if test="${!empty meals}">
    <ul>
    <c:forEach items="${meals}" var="meal">
        <li style="color:${meal.exceed ? 'red' : 'blue'}">

            <%--<c:set var="cleanedDateTime" value="${fn:replace(meal.dateTime, 'T', ' ')}" />
            <fmt:parseDate value="${ cleanedDateTime }" pattern="yyyy-MM-dd HH:mm" var="parsedDateTime" type="both" />--%>
            <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />

            - <c:out value="${meal.description}"/>
            - <c:out value="${meal.calories}"/>
        </li>
    </c:forEach>
    </ul>
</c:if>
</body>
</html>
