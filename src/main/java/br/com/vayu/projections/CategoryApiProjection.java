package br.com.vayu.projections;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface CategoryApiProjection {

    String getName();
    String getCode();
    int getOrder();
    String getColorCode();
    String getStudyGuide();
    int getTotalOfCourses();

    @Value("#{target.getSubcategories()}")
    List<SubcategoryApiProjection> getSubcategories();

    @Value("#{target.getSubcategories().stream().map(T(br.com.vayu.models.Subcategory).getCourses()).toList()}")
    List<CourseApiProjection> getCourses();

}
