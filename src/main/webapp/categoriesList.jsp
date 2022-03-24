<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Lista Categorias</title>
</head>
<body>
    <h1>Categorias</h1>

    <c:forEach items="${categories}" var="ct">
        <h2 style="background-color:${ct.colorCode}; max-width: 200px">${ct.name}</h2>
        <img src="${ct.iconPath}" style="max-height: 250px"><br/>
        <p>${ct.description}</p>
        <p>${ct.studyGuide}</p>
    </c:forEach>

</body>
</html>
