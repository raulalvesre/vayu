<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<c:url value="admin/categories" var="linkCategoryServlet"/>

<templates:admin-template title="Categorias" cssPath="css/list.css">
    <div class="container">
        <h1 style="margin-top: 10px">Categorias</h1>
        <a href="categories/new">
            <button type="button" class="btn btn-primary" id="btn-add">
                Nova categoria
            </button>
        </a>

    <templates:admin-table modelList="${categories}"
                           hasChildren="${true}"
                           childrenListUrlContext="subcategories"
                           childrenModelName="Subcategoria"
                           editFormUrlContext="categories"/>
    </div>
    <footer>
        <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/categoryList.js"></script>
    </footer>
</templates:admin-template>