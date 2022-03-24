<%@ page import="br.com.vayu.models.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista Categorias</title>
</head>
<body>

    <h1>Categorias</h1>

    <%
        List<Category> categories = (List<Category>) request.getAttribute("categories");
        for (Category ct : categories) {
    %>

        <h2 style="background-color:<%= ct.getColorCode() %>; max-width: 200px"><%= ct.getName() %></h2>
        <img src="<%= ct.getIconPath() %>" style="max-height: 250px"><br/>
        <p><%= ct.getDescription() %></p>
        <p><%= ct.getStudyGuide() != null ? ct.getStudyGuide() : "" %></p>

    <%
        }
    %>

</body>
</html>
