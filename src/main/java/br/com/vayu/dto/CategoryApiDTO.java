package br.com.vayu.dto;

import br.com.vayu.models.Category;

import java.util.List;
import java.util.Objects;

public final class CategoryApiDTO {

    private String name;
    private String code;
    private int order;
    private String colorCode;
    private String studyGuide;
    private int totalOfCourses;
    private List<SubcategoryApiDTO> subcategories;

    public CategoryApiDTO(Category category, List<SubcategoryApiDTO> subcategories) {
        this.name = category.getName();
        this.code = category.getCode();
        this.order = category.getOrder();
        this.colorCode = category.getColorCode();
        this.studyGuide = category.getStudyGuide();
        this.subcategories = subcategories;

        this.totalOfCourses = subcategories.stream()
                .map(SubcategoryApiDTO::courses)
                .mapToInt(List::size)
                .sum();
    }

    @Deprecated
    public CategoryApiDTO() {

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

    public List<SubcategoryApiDTO> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryApiDTO> subcategories) {
        this.subcategories = subcategories;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CategoryApiDTO) obj;
        return Objects.equals(this.name, that.name) &&
               Objects.equals(this.code, that.code) &&
               this.order == that.order &&
               Objects.equals(this.colorCode, that.colorCode) &&
               Objects.equals(this.studyGuide, that.studyGuide) &&
               this.totalOfCourses == that.totalOfCourses &&
               Objects.equals(this.subcategories, that.subcategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, order, colorCode, studyGuide, totalOfCourses, subcategories);
    }

}