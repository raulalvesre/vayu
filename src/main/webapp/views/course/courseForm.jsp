<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>${formIsCreate ? "Nova" : "Editar"} curso</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet"/>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
</head>
<body>
<div class="container">
    <h1 id="header">${formIsCreate ? "Nova" : "Editar"} curso</h1>

    <%--@elvariable id="courseFormDTO" type="br.com.vayu.dto.CourseFormDTO"--%>
    <form:form modelAttribute="courseFormDTO" action="${postURL}" method="POST" id="courseForm">
        <input type="hidden" name="id" value="${courseFormDTO.id}">

        <spring:bind path="name">
            <div class="form-group ${status.error ? "has-error has-feedback" : ""}">
                <form:label path="name">Nome</form:label>
                <form:input path="name" cssClass="form-control" placeholder="Digite aqui o nome do curso"/>
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <form:errors path="name" cssClass="help-block" element="div"/>
            </div>
        </spring:bind>

        <spring:bind path="code">
            <div class="form-group ${status.error ? "has-error has-feedback" : ""}">
                <form:label path="code">Código</form:label>
                <form:input path="code" cssClass="form-control" id="code"
                            placeholder="Por exemplo: spring-boot, docker (não use letras maiúsculas, acentos ou caracteres especiais)"/>
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <form:errors path="code" cssClass="help-block" element="div"/>
            </div>
        </spring:bind>

        <div class="form-check" id="activeFormGroup">
            <form:checkbox path="visible" cssClass="form-check-input"/>
            <form:label path="visible" cssClass="form-check-label lb-md">Curso público?</form:label>
        </div>

        <spring:bind path="instructorName">
            <div class="form-group ${status.error ? "has-error has-feedback" : ""}">
                <form:label path="instructorName">Nome do instrutor</form:label>
                <form:input path="instructorName" cssClass="form-control"
                            placeholder="O nome do instrutor (devia ser um entidade, né)"/>
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <form:errors path="instructorName" cssClass="help-block" element="div"/>
            </div>
        </spring:bind>

        <spring:bind path="estimatedHoursToFinish">
            <div class="form-group ${status.error ? "has-error has-feedback" : ""}">
                <form:label path="estimatedHoursToFinish">Carga horária</form:label>
                <form:input path="estimatedHoursToFinish" cssClass="form-control"
                            placeholder="Qual a carga horária? (entre 1 e 20)"/>
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <form:errors path="estimatedHoursToFinish" cssClass="help-block" element="div"/>
            </div>
        </spring:bind>

        <div class="form-group">
            <form:label path="targetAudience">Público alvo</form:label>
            <form:input path="targetAudience" cssClass="form-control"
                        placeholder="Qual o público alvo para esse curso?"/>
            <form:errors path="targetAudience" cssClass="help-block" element="div"/>
        </div>

        <div class="form-group">
            <form:label path="syllabus">Ementa</form:label>
            <form:textarea path="syllabus" cssClass="form-control" rows="3"
                           placeholder="Um texto apontando o que será aprendido no curso"/>
            <form:errors path="syllabus" cssClass="help-block" element="div"/>
        </div>

        <div class="form-group">
            <form:label path="developedAbilities">Habilidades desenvolvidas</form:label>
            <form:textarea path="developedAbilities" cssClass="form-control" rows="2"
                        placeholder="Quais habilidades serão desenvolvidas?"/>
            <form:errors path="developedAbilities" cssClass="help-block" element="div"/>
        </div>

        <div class="form-group">
            <form:label path="subcategoryCode" cssClass="control-label">Subcategoria</form:label>
            <form:select path="subcategoryCode" cssClass="form-control" id="categorySelect">
                <c:forEach items="${subcategories}" var="subcategory">
                    <form:option value="${subcategory.code}">${subcategory.name}</form:option>
                </c:forEach>
            </form:select>
        </div>

        <button type="submit" class="btn btn-primary mb-2" id="submitButton">Enviar</button>
    </form:form>

</div>
</body>
</html>
