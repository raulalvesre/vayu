package br.com.vayu.dto;

import br.com.vayu.models.Subcategory;

public record SubcategoryDTO(String name,
                             String code,
                             String studyGuide){

    public SubcategoryDTO(Subcategory subcategory) {
        this(subcategory.getName(),
                subcategory.getCode(),
                subcategory.getStudyGuide());
    }

}
