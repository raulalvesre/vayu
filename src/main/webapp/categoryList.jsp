<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/categorias" var="linkCategoryServlet"/>

<html>
<head>
    <title>Categorias</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/categoryList.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>

<h1 class="title">CATEGORIAS</h1><br>
<div id="table-wrapper">
    <table class="table" id="table">
        <thead>
        <tr>
            <th scope="col">CODIGO</th>
            <th scope="col">NOME</th>
            <th scope="col">ICONE</th>
            <th scope="col">COR</th>
            <th scope="col">DESCRIÇÃO</th>
            <th scope="col">STATUS</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${categories}" var="ct">
            <tr id="category${ct.id}">
                <td class="code">${ct.code}</td>
                <td class="name">${ct.name}</td>
                <td><img src="${ct.iconPath}" class="image"/></td>
                <td><span style="background-color: ${ct.colorCode}">⠀⠀⠀⠀</span></td>
                <td class="description">${ct.description}</td>
                <td class="active" id="active${ct.id}">${ct.active ? "ATIVA" : "INATIVA"}</td>
                <td><a href="${linkCategoryServlet}/formulario/${ct.id}"><i
                        class="material-icons delete-button">edit</i></a></td>
                <td>
                    <a href="#" <c:if test="${!ct.active}">hidden</c:if>>
                        <i class="material-icons delete-icn" id="deleteIcon${ct.id}" onclick="deactivateCategory(event ,this)">
                            delete
                        </i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="${linkCategoryServlet}/formulario">
        <button type="button" class="btn btn-primary" id="btn-add">ADICIONAR</button>
    </a>
</div>

<footer>
    <script src="js/categoryList.js"></script>
</footer>
</body>
</html>
