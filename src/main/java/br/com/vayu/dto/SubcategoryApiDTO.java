package br.com.vayu.dto;

import br.com.vayu.models.Subcategory;

public record SubcategoryApiDTO(String name,
                                String code,
                                String studyGuide){

    public SubcategoryApiDTO(Subcategory subcategory) {
        this(subcategory.getName(),
                subcategory.getCode(),
                subcategory.getStudyGuide());
    }

}
