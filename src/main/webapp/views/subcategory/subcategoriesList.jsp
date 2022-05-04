<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<templates:admin-template title="Subcategorias" cssPath="css/list.css">
    <div class="container">
        <h4>${categoryDto.name}</h4>
        <h1 style="margin-top: 10px">Subcategorias</h1>
        <a href="/admin/subcategories/new">
            <button type="button" class="btn btn-primary" id="btn-add">
                Nova subcategoria
            </button>
        </a>

        <templates:admin-table modelList="${subcategories}"
                               hasChildren="${true}"
                               childrenListUrlContext="/admin/courses/${categoryDto.code}"
                               childrenModelName="Curso"
                               editFormUrlContext="/admin/subcategories/${categoryDto.code}"/>

    </div>
    <footer>
        <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/subcategoryList.js"></script>
    </footer>
</templates:admin-template>