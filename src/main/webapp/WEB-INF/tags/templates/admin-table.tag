<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@attribute name="modelList" type="java.util.List" %>
<%@attribute name="hasChildren" %>
<%@attribute name="childrenListUrlContext" %>
<%@attribute name="childrenModelName" %>
<%@attribute name="editFormUrlContext" %>

<div>
    <table class="table table-bordered" id="table">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Código</th>
            <th>Status</th>
            <c:forEach begin="1" end="${hasChildren ? 3 : 2}">
                <th></th>
            </c:forEach>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${modelList}" var="model">
            <tr>
                <td>${model.name}</td>
                <td>${model.code}</td>
                <td>${model.active ? "ATIVA" : "INATIVA"}</td>
                <c:if test="${hasChildren}">
                    <td class="child">
                        <a href="${childrenListUrlContext}/${model.code}" class="underlined-text">
                                ${childrenModelName}s
                        </a>
                    </td>
                </c:if>
                <td class="edit">
                    <a href="${editFormUrlContext}/${model.code}" class="btn btn-default">
                        Editar
                    </a>
                </td>
                <td class="deactivation-button-td">
                    <button class="btn btn-default deactivate-btn ${!model.active ? 'hidden' : ''}"
                            data-model-id="${model.id}">
                        Desativar
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>