<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/categorias" var="linkCategoryServlet"/>
<c:set var="formObjective" value="${category == null ? \"ADICIONAR\" : \"EDITAR\" }"/>
<html>
<head>
    <title>${formObjective} CATEGORIA</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/categoryForm.css" rel="stylesheet"/>
</head>
<body>
<h1 id="header">${formObjective} CATEGORIA</h1>

<hr>

<div id="formWrapper">
    <form action="/categorias" method="post">
        <input type="hidden" name="id" value="${category.id}">

        <div class="form-group">
            <label for="fcode">Código</label>
            <input type="text" class="form-control" id="fcode" name="code" value="${category.code}">
        </div>

        <div class="form-group">
            <label for="fname">Nome</label>
            <input type="text" class="form-control" id="fname" name="name" value="${category.name}">
        </div>

        <div class="form-group">
            <label for="fdescription">Descrição</label>
            <input type="text" class="form-control" id="fdescription" name="description"
                   value="${category.description}">
        </div>

        <div class="form-group">
            <label for="fstudyGuide">Guia de estudos</label>
            <textarea class="form-control" id="fstudyGuide" name="studyGuide" value="${category.studyGuide}"
                      rows="2"></textarea>
        </div>

        <div class="form-group">
            <label for="ficonPath">URL Ícone</label>
            <input type="text" class="form-control" id="ficonPath" name="iconPath" value="${category.iconPath}">
        </div>

        <div class="row" id="smallFormsWrapper">
            <div class="form-group col-2" id="colorFormGroup">
                <label for="fcolor">Cor: </label>
                <input type="color" class="form-control" id="fcolor" name="colorCode" value="${category.colorCode}">
            </div>

            <div class="form-check form-switch col-2" id="activeFormGroup">
                <label class="form-check-label" for="factive">ATIVA</label>
                <input class="form-check-input" type="checkbox" id="factive" name="active"
                       <c:if test="${category.active}">checked</c:if>>
            </div>

            <div class="form-group col-2" id="orderFormGroup">
                <label for="forder">Ordem: </label>
                <input type="number" class="form-control" id="forder" name="order" value="${category.order}">
            </div>
        </div>

        <button type="submit" class="btn btn-primary mb-2" id="submitButton">${formObjective}</button>
    </form>
</div>
</body>
</html>
