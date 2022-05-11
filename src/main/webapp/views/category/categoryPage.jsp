<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template title="${category.name} | Cursos online de tecnologia"
                          cssPath="css/categoryPage.css"/>
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
            <c:forEach items="${category.subcategories}" var="subcategory">
                <li class="subcategories__item">
                    <a href="#${subcategory.code}" class="subcategories__link">
                        <span class="subcategories__name">${subcategory.name}</span>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <c:forEach items="${category.subcategories}" var="subcategory">
        <div class="subcategory">
            <h2 id="${subcategory.code}" class="subcategory__name">${subcategory.name}</h2>
            <ul class="courses__list">
                <c:forEach items="${subcategory.courses}" var="crs">
                    <li class="course-card">
                        <h3 class="course-card__name">${crs.name}</h3>
                        <p class="course-card__hours">${crs.estimatedHoursToFinish}h</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
</main>
<templates:admin-footer/>
