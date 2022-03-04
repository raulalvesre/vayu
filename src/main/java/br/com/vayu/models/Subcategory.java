package br.com.vayu.models;

import br.com.vayu.services.ValidationService;

import java.util.Objects;

public class Subcategory {

    private final String code;
    private final String name;
    private String description;
    private String studyGuide;
    private boolean active;
    private int order;
    private final Category category;

    public Subcategory(String code,
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

    public Subcategory(String code,
                       String name,
                       Category category) {
        ValidationService.validateIfItIsValidCode(code);
        ValidationService.validateIfIsBlankString("name", name);
        ValidationService.validateIfItIsNull("category", category);

        this.code = code;
        this.name = name;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public int getOrder() {
        return order;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subcategory that = (Subcategory) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
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
