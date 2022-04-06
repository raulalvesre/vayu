package br.com.vayu.dto;

import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;

import java.util.List;
import java.util.Objects;

public final class CategoryDTO {

    private String name;
    private String code;
    private int order;
    private String colorCode;
    private String studyGuide;
    private int totalOfCourses;
    private List<SubcategoryDTO> subcategories;
    private List<CourseDTO> courses;

    @Deprecated
    public CategoryDTO() {

    }

    public CategoryDTO(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.order = category.getOrder();
        this.colorCode = category.getColorCode();
        this.studyGuide = category.getStudyGuide();

        var subcategories = category.getSubcategories();

        this.courses = subcategories.stream()
                .map(Subcategory::getCourses)
                .flatMap(List::stream)
                .filter(Course::isVisible)
                .map(CourseDTO::new)
                .toList();

        this.totalOfCourses = courses.size();

        this.subcategories = subcategories.stream()
                .filter(Subcategory::isActive)
                .map(SubcategoryDTO::new)
                .toList();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public int getTotalOfCourses() {
        return totalOfCourses;
    }

    public void setTotalOfCourses(int totalOfCourses) {
        this.totalOfCourses = totalOfCourses;
    }

    public List<SubcategoryDTO> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryDTO> subcategories) {
        this.subcategories = subcategories;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CategoryDTO) obj;
        return Objects.equals(this.name, that.name) &&
               Objects.equals(this.code, that.code) &&
               this.order == that.order &&
               Objects.equals(this.colorCode, that.colorCode) &&
               Objects.equals(this.studyGuide, that.studyGuide) &&
               this.totalOfCourses == that.totalOfCourses &&
               Objects.equals(this.subcategories, that.subcategories) &&
               Objects.equals(this.courses, that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, order, colorCode, studyGuide, totalOfCourses, subcategories, courses);
    }

    @Override
    public String toString() {
        return "CategoryDTO[" +
               "name=" + name + ", " +
               "code=" + code + ", " +
               "order=" + order + ", " +
               "colorCode=" + colorCode + ", " +
               "studyGuide=" + studyGuide + ", " +
               "totalOfCourses=" + totalOfCourses + ", " +
               "subcategories=" + subcategories + ", " +
               "courses=" + courses + ']';
    }

}