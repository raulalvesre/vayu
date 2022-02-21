package br.com.vayu.models;

import java.util.Objects;

import static br.com.vayu.services.ValidationService.*;

public class Category {

    private final String code;
    private final String name;
    private String description;
    private String studyGuide;
    private boolean active;
    private int order;
    private String iconPath;
    private String colorCode;

    public Category(String code,
                    String name,
                    String description,
                    String studyGuide,
                    boolean active,
                    Integer order,
                    String iconPath,
                    String colorCode) {
        this(code, name);

        validateIfIsValidHexColorCode("color code", colorCode);

        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.order = order;
        this.iconPath = iconPath;
        this.colorCode = colorCode;
    }

    public Category(String code,
                    String name) {
        validateIfItIsValidCode(code);
        validateIfIsBlankString("name", name);

        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getOrder() {
        return order;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getColorCode() {
        return colorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(code, category.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Category{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", order=" + order +
                ", iconPath='" + iconPath + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }

}
