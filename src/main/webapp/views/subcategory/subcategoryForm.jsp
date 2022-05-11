<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<templates:admin-template title='${formIsCreate ? "Novo" : "Editar"} subcategoria'>
    <div class="container">
        <h1 id="header">${formIsCreate ? "Nova" : "Editar"} subcategoria</h1>
            <%--@elvariable id="subcategoryFormDTO" type="br.com.vayu.dto.SubcategoryFormDTO"--%>
        <form:form modelAttribute="subcategoryFormDTO" action="${postURL}" method="POST" id="subcategoryForm">
            <input type="hidden" name="id" value="${subcategoryFormDTO.id}">

            <spring:bind path="name">
                <div class="form-group ${status.error ? "has-error has-feedback" : ""}">
                    <form:label path="name">Nome</form:label>
                    <form:input path="name" cssClass="form-control" placeholder="Digite aqui o nome da subcategoria"/>
                    <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                    <form:errors path="name" cssClass="help-block" element="div"/>
                </div>
            </spring:bind>


            <spring:bind path="code">
                <div class="form-group ${status.error ? "has-error has-feedback" : ""}">
                    <form:label path="code">Código</form:label>
                    <form:input path="code" cssClass="form-control" id="code"
                                placeholder="Por exemplo: java, java-oo (não use letras maiúsculas, acentos ou caracteres especiais)"/>
                    <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                    <form:errors path="code" cssClass="help-block" element="div"/>
                </div>
            </spring:bind>

            <div class="form-check" id="activeFormGroup">
                <form:checkbox path="active" cssClass="form-check-input"/>
                <form:label path="active" cssClass="form-check-label lb-md">Subcategoria ativa?</form:label>
            </div>

            <div class="form-group" id="orderFormGroup">
                <form:label path="order">Ordem</form:label>
                <form:input path="order" cssClass="form-control"
                            placeholder="Por exemplo: subcategoria de ordem 1 aparece antes da subcategoria de ordem 2"/>
                <form:errors path="order" cssClass="help-block" element="div"/>
            </div>

            <div class="form-group">
                <form:label path="studyGuide">Guia de estudos</form:label>
                <form:textarea path="studyGuide" cssClass="form-control" rows="2"
                               placeholder="Um texto apontando para formações para ajudar pessoas perdidas"/>
                <form:errors path="studyGuide" cssClass="help-block" element="div"/>
            </div>

            <div class="form-group">
                <form:label path="description">Descrição</form:label>
                <form:input path="description" cssClass="form-control"
                            placeholder="Por exemplo: iOS, Android, PhoneGap e mais..."/>
                <form:errors path="description" cssClass="help-block" element="div"/>
            </div>

            <div class="form-group">
                <form:label path="categoryCode" cssClass="control-label">Categoria</form:label>
                <form:select path="categoryCode" cssClass="form-control" id="categorySelect">
                    <c:forEach items="${categories}" var="category">
                        <form:option value="${category.code}">${category.name}</form:option>
                    </c:forEach>
                </form:select>
            </div>

            <button type="submit" class="btn btn-primary mb-2" id="submitButton">Enviar</button>
        </form:form>
    </div>
</templates:admin-template>
<templates:admin-footer>
</templates:admin-footer>
