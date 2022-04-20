<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cursos</title>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css"/>
</head>
<body>
<div class="container">
    <h4 id="categoryName">${subcategoryDto.name}</h4>
    <h1 style="margin-top: 10px">Cursos</h1>
    <a href="/admin/courses/new">

        <button type="button" class="btn btn-primary" id="btn-add">Novo curso</button>
    </a>

    <div>

        <table class="table table-bordered" id="table">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Código</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>

            <tbody>
                <c:forEach items="${coursePage.content}" var="crs">
                    <tr>
                        <td>${crs.name}</td>
                        <td>${crs.code}</td>
                        <td>${crs.visible ? "ATIVA" : "INATIVA"}</td>
                        <td>
                            <a href="/admin/courses/${categoryCode}/${subcategoryDto.code}/${crs.code}" class="btn btn-default">
                                Editar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
    <nav class="text-center">
        <ul class="pagination">
            <li class="${coursePage.first ? 'disabled' : ''}">
                <a href="/admin/courses/programacao/java?page=1">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach begin="1" end="${coursePage.totalPages}" step="1" varStatus="loop">
                <li class="${loop.count == (coursePage.number + 1) ? 'active' : ''}">
                    <a href="/admin/courses/programacao/java?page=${loop.count}">${loop.count}</a>
                </li>
            </c:forEach>
            <li class="${coursePage.last ? 'disabled' : ''}">
                <a href="/admin/courses/programacao/java?page=${coursePage.totalPages}">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>

</div>

</body>
</html>
