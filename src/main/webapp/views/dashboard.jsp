<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Dashboard</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>

<div class="container">

    <h1 class="title">Dashboard</h1>

    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Total de cursos</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${categories}" var="sb">
                <tr>
                    <td class="name">${sb.name}</td>
                    <td>${sb.totalCourses}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <h3>Instrutor com mais cursos</h3>
    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Total de cursos</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td class="name">${instructor.name}</td>
                <td>${instructor.totalCourses}</td>
            </tr>
            </tbody>
        </table>
    </div>

</div>

<footer>
</footer>
</body>
</html>
