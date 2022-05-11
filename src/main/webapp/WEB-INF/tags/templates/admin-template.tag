<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@attribute name="title" %>
<%@attribute name="cssPath" %>

<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link href="${pageContext.request.contextPath}/${cssPath}" rel="stylesheet"/>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
</head>
    <jsp:doBody/>