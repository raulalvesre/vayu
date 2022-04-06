<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="admin/categories" var="linkCategoryServlet"/>

<html>
<head>
    <title>Categorias</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="../css/categoryList.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/src/main/webapp/css/categoryList.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>

<div class="container">

    <h1 class="title">Categorias</h1>

    <a href="categories/new">
        <button type="button" class="btn btn-primary" id="btn-add">Nova categoria</button>
    </a>

    <div>
        <table class="table table-bordered" id="categoryTable">
            <thead>
            <tr>
                <th scope="col">Nome</th>
                <th scope="col">Código</th>
                <th scope="col">Status</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${categories}" var="ct">
                <tr>
                    <td class="name">${ct.name()}</td>
                    <td class="code">${ct.code()}</td>
                    <td class="categoryActive">${ct.active() ? "ATIVA" : "INATIVA"}</td>
                    <td class="subcategories"><a href="subcategories/${ct.code()}">Subcategorias</a></td>
                    <td class="editCategory">
                        <a href="categories/${ct.code()}">
                            <button type="button" class="btn btn-light">Editar</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

</div>

<footer>
    <script src="js/categoryList.js"></script>
</footer>
</body>
</html>
