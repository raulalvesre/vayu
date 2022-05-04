<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<templates:admin-template title="Cursos" cssPath="css/list.css">
    <div class="container">
        <h4>${subcategoryDto.name}</h4>
        <h1 style="margin-top: 10px">Cursos</h1>
        <a href="/admin/courses/new">
            <button type="button" class="btn btn-primary" id="btn-add">
                Novo curso
            </button>
        </a>

        <templates:admin-table modelList="${coursePage.content}"
                               hasChildren="${false}"
                               editFormUrlContext="/admin/courses/${categoryCode}/${subcategoryDto.code}"/>

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
</templates:admin-template>