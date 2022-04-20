package br.com.vayu.dto;

import br.com.vayu.models.Subcategory;

import java.util.List;

public class SubcategoryCategoryPublicPageDTO {

    private int id;
    private String name;
    private String code;
    private List<CourseCategoryPublicPageDTO> courses;

    public SubcategoryCategoryPublicPageDTO(int id,
                                            String name,
                                            String code,
                                            List<CourseCategoryPublicPageDTO> courses) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.courses = courses;
    }

    public SubcategoryCategoryPublicPageDTO(Subcategory subcategory, List<CourseCategoryPublicPageDTO> courses) {
        this.id = subcategory.getId();
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.courses = courses;
    }

    @Deprecated
    public SubcategoryCategoryPublicPageDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<CourseCategoryPublicPageDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseCategoryPublicPageDTO> courses) {
        this.courses = courses;
    }

}
