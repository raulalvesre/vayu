package br.com.vayu.builders;

import br.com.vayu.models.Category;

public class CategoryBuilder {

    private String code;
    private String name;
    private String description;
    private String studyGuide;
    private boolean active;
    private int order;
    private String iconPath;
    private String colorCode;

    public CategoryBuilder code(String code) {
        this.code = code;
        return this;
    }

    public CategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CategoryBuilder studyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
        return this;
    }

    public CategoryBuilder active(boolean active) {
        this.active = active;
        return this;
    }

    public CategoryBuilder order(int order) {
        this.order = order;
        return this;
    }

    public CategoryBuilder iconPath(String iconPath) {
        this.iconPath = iconPath;
        return this;
    }

    public CategoryBuilder colorCode(String colorCode) {
        this.colorCode = colorCode;
        return this;
    }

    public Category build() {
        return new Category(code,
                name,
                description,
                studyGuide,
                active,
                order,
                iconPath,
                colorCode);
    }

}