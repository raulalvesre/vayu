package br.com.vayu.dto;

import br.com.vayu.models.Category;

public record CategoryManagementDTO(String name,
                                    String code,
                                    boolean active) {

    public CategoryManagementDTO(Category category) {
        this(category.getName(),
                category.getCode(),
                category.isActive());
    }

}
