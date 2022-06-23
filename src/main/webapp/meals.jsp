<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="mealsTo" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
<jsp:useBean id="dateFormatter" scope="request" type="java.time.format.DateTimeFormatter"/>
<html>
<head>
    <title>Meals</title>
    <style>
        .green{
            color: green;
        }
        .red{
            color: red;
        }
        th,td{
            border: 1px solid grey;
            padding: 3px;
        }
        table{
            border-spacing: 0;
        }
    </style>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Meals</h2>
    <a href="${pageContext.request.contextPath}/create">Add Meal</a>
    <table>
        <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Calories</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:set value="${dateFormatter}" var="dateFormatter"/>
            <c:forEach items="${mealsTo}" var="mealTo">
                <c:set var="color" value="${mealTo.excess ? \"red\" : \"green\"}" />
                <tr class="${color}">
                    <td><c:out value="${mealTo.dateTime.format(dateFormatter)}"/></td>
                    <td><c:out value="${mealTo.description}"/></td>
                    <td><c:out value="${mealTo.calories}"/></td>
                    <td><a href="${pageContext.request.contextPath}/update?id=<c:out value="${mealTo.id}"/>">Update</a></td>
                    <td><a href="${pageContext.request.contextPath}/delete?id=<c:out value="${mealTo.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
