<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<templates:admin-template title="${formIsCreate ? 'Criar categoria' : 'Editar Categoria'}"
                          cssPath="css/form.css"/>
<div class="container">
    <h1 id="header">${formIsCreate ? "Nova" : "Editar"} categoria</h1>
    <%--@elvariable id="categoryFormDTO" type="br.com.vayu.dto.CategoryFormDTO"--%>
    <form:form modelAttribute="categoryFormDTO" action="${postURL}" method="POST">
        <input type="hidden" name="id" value="${categoryFormDTO.id}">

        <spring:bind path="name">
            <div class="form-group ${status.error ? "has-error has-feedback" : ""}">
                <form:label path="name">Nome</form:label>
                <form:input path="name" cssClass="form-control" placeholder="Digite aqui o nome da categoria"/>
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <form:errors path="name" cssClass="help-block with-errors" element="div"/>
            </div>
        </spring:bind>

        <spring:bind path="code">
            <div class="form-group ${status.error ? "has-error has-feedback" : ""}">
                <form:label path="code">Código</form:label>
                <form:input path="code" cssClass="form-control"
                            placeholder="Por exemplo: desenvolvimento, mobile (não use letras maiúsculas, acentos ou caracteres especiais)"/>
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <form:errors path="code" cssClass="help-block" element="div"/>
            </div>
        </spring:bind>

        <div class="form-check" id="activeFormGroup">
            <form:checkbox path="active" cssClass="form-check-input"/>
            <form:label path="active" cssClass="form-check-label lb-md">Categoria ativa?</form:label>
            <span class="text-muted">Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formação, etc</span>
        </div>

        <div class="form-group" id="orderFormGroup">
            <form:label path="order">Ordem</form:label>
            <form:input path="order" cssClass="form-control"
                        placeholder="Por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2"/>
            <form:errors path="order" cssClass="help-block" element="div"/>
        </div>

        <div class="form-group">
            <form:label path="studyGuide">Guia de estudos</form:label>
            <form:textarea path="studyGuide" cssClass="form-control" rows="2"
                           placeholder="Um texto apontando para formações para ajudar pessoas perdidas"/>
            <form:errors path="studyGuide" cssClass="help-block" element="div"/>
        </div>

        <div class="form-group">
            <form:label path="iconPath">Caminho do ícone</form:label>
            <form:input path="iconPath" cssClass="form-control"
                        placeholder="Por exemplo: /images/categorias/programacao.svg"/>
            <form:errors path="iconPath" ccssClass="help-block" element="div"/>
        </div>

        <spring:bind path="colorCode">
            <div class="form-group ${status.error ? "has-error has-feedback" : ""}">
                <form:label path="colorCode">Cor</form:label>
                <form:input path="colorCode" cssClass="form-control" placeholder="Por exemplo: #fcc14a"/>
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <form:errors path="colorCode" cssClass="help-block" element="div"/>
            </div>
        </spring:bind>

        <div class="form-group">
            <form:label path="description">Descrição</form:label>
            <form:input path="description" cssClass="form-control"
                        placeholder="Por exemplo: iOS, Android, PhoneGap e mais..."/>
            <form:errors path="description" cssClass="help-block" element="div"/>
        </div>

        <button type="submit" class="btn btn-primary mb-2" id="submitButton">Enviar</button>
    </form:form>
</div>
<templates:admin-footer />
