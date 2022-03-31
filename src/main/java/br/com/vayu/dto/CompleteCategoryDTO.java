package br.com.vayu.dto;

import br.com.vayu.models.Category;

import java.util.List;

public record CompleteCategoryDTO(String name,
                                  String code,
                                  int order,
                                  String colorCode,
                                  String studyGuide,
                                  int totalOfCourses,
                                  List<SubcategoryDTO> subcategories,
                                  List<CourseDTO> courses) {

    public CompleteCategoryDTO(Category category,
                               List<SubcategoryDTO> subcategories,
                               List<CourseDTO> courses) {
        this(category.getName(),
                category.getCode(),
                category.getOrder(),
                category.getColorCode(),
                category.getStudyGuide(),
                courses.size(),
                subcategories,
                courses);
    }

}