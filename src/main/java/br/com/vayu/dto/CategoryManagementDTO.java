package br.com.vayu.dto;

import br.com.vayu.models.Category;

public record CategoryManagementDTO(int id,
                                    String name,
                                    String code,
                                    String description,
                                    String studyGuide,
                                    boolean active,
                                    int order,
                                    String iconPath,
                                    String colorCode) {

    public CategoryManagementDTO(Category category) {
        this(category.getId(),
                category.getName(),
                category.getCode(),
                category.getDescription(),
                category.getStudyGuide(),
                category.isActive(),
                category.getOrder(),
                category.getIconPath(),
                category.getColorCode());
    }

}
