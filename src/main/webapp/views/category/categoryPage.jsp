<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${category.name} | Cursos online de tecnologia</title>
    <link rel="stylesheet" type="text/css" href="../css/categoryPage.css"/>
</head>
<body>
<section class="category-banner__wrapper">
    <div class="container category-banner">
            <span class="category-banner__icon">
                <img src="${category.iconPath}">
            </span>
        <h1 class="category-banner__title">${category.name}</h1>
    </div>
</section>
<main class="container">
    <div class="subcategories">
        <p class="subcategories__label">O que você quer aprender?</p>
        <ul class="subcategories__list">
            <c:forEach items="${category.subcategories}" var="sb">
                <li class="subcategories__item">
                    <a href="#${sb.code}" class="subcategories__link">
                        <span class="subcategories__name">${sb.name}</span>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <c:forEach items="${category.subcategories}" var="sb">
        <div class="subcategory">
            <h2 id="${sb.code}" class="subcategory__name">${sb.name}</h2>
            <ul class="courses__list">
                <c:forEach items="${sb.courses}" var="crs">
                    <li class="course-card">
                        <h3 class="course-card__name">${crs.name}</h3>
                        <p class="course-card__hours">${crs.estimatedHoursToFinish}h</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
</main>
</body>
</html>