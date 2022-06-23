<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<html>
<head>
    <title>Meals</title>
    <style>
        input {
            margin: 5px;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Update/Create meal</h2>
<a href="${pageContext.request.contextPath}/meals">Back</a>
    <form method="post" action="${pageContext.request.contextPath}/update">
        <input name="id" type="number" value="<c:out value="${meal.id}"/>" hidden>
        <label>
            Date time:
            <input required type="datetime-local" name="local_date_time" value="<c:out value="${meal.dateTime}"/>">
        </label><br>
        <label>
            Description:
            <input required type="text" name="description" value="<c:out value="${meal.description}"/>">
        </label><br>
        <label>
            Calories:
            <input required type="number" name="calories" value="<c:out value="${meal.calories}"/>">
        </label><br>
        <input type="submit"><br>
    </form>
</body>
</html>
