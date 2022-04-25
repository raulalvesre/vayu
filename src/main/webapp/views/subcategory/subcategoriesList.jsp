<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subcategorias</title>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css"/>
</head>
<body>

<div class="container">
    <h4 id="categoryName">${categoryDto.name}</h4>
    <h1 style="margin-top: 10px">Subcategorias</h1>
    <a href="/admin/subcategories/new">
        <button type="button" class="btn btn-primary" id="btn-add">Nova subcategoria</button>
    </a>

    <div>

        <table class="table table-bordered" id="table">
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
            <c:forEach items="${subcategories}" var="subcategory">
                <tr>
                    <td class="name">${subcategory.name}</td>
                    <td class="code">${subcategory.code}</td>
                    <td class="activeTd">${subcategory.active ? "ATIVA" : "INATIVA"}</td>
                    <td class="child"><a href="/admin/courses/${categoryDto.code}/${subcategory.code}" class="underlined-text">Cursos</a></td>
                    <td class="edit">
                        <a href="/admin/subcategories/${categoryDto.code}/${subcategory.code}" class="btn btn-default">
                            Editar
                        </a>
                    </td>
                    <td class="deactivation-button-td">
                        <button class="btn btn-default deactivate-btn ${!subcategory.active ? 'hidden' : ''}" data-sb-id="${subcategory.id}">
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
    <script src="${pageContext.request.contextPath}/js/subcategoryList.js"></script>
</footer>
</body>
</html>
