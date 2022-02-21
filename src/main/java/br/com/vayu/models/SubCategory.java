package br.com.vayu.models;

import static br.com.vayu.services.ValidationService.*;

public class SubCategory {

    private final String code;
    private final String name;
    private String description;
    private String studyGuide;
    private boolean active;
    private Integer order;
    private final Category category;

    public SubCategory(String code,
                       String name,
                       String description,
                       String studyGuide,
                       boolean active,
                       Integer order,
                       Category category) {
        this(code, name, category);

        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.order = order;
    }

    public SubCategory(String code,
                       String name,
                       Category category) {
        validateIfItIsValidCode(code);
        validateIfIsBlankString("name", name);
        validateIfItIsNull("category", category);

        this.code = code;
        this.name = name;
        this.category = category;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", order=" + order +
                ", category=" + category.getName() +
                '}';
    }

}
