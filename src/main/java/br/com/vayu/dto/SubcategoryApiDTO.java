package br.com.vayu.dto;

import br.com.vayu.models.Subcategory;

import java.util.List;

public record SubcategoryApiDTO(String name,
                                String code,
                                String studyGuide,
                                List<CourseApiDTO> courses){

    public SubcategoryApiDTO(Subcategory subcategory, List<CourseApiDTO> courses) {
        this(subcategory.getName(),
                subcategory.getCode(),
                subcategory.getStudyGuide(),
                courses);
    }

}
