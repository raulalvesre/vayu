<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="admin/categories" var="linkCategoryServlet"/>

<html>
<head>
    <title>Categorias</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>

<div class="container">

    <h1 class="title">Categorias</h1>

    <a href="categories/new">
        <button type="button" class="btn btn-primary" id="btn-add">Nova categoria</button>
    </a>

    <div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Código</th>
                <th>Status</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${categories}" var="sb">
                <tr>
                    <td class="name">${sb.name}</td>
                    <td class="code">${sb.code}</td>
                    <td class="activeTd">${sb.active ? "ATIVA" : "INATIVA"}</td>
                    <td class="child"><a href="subcategories/${sb.code}" class="underlined-text">Subcategorias</a></td>
                    <td class="edit">
                        <a href="categories/${sb.code}" class="btn btn-default">
                            Editar
                        </a>
                    </td>
                    <td class="deactivation-button-td">
                        <button class="btn btn-default deactivate-btn ${!sb.active ? 'hidden' : ''}" data-ct-id="${sb.id}">
                            Desativar
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

</div>

<footer>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/categoryList.js"></script>
</footer>
</body>
</html>
