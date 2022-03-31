package br.com.vayu.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column
    private String studyGuide;

    private boolean active;

    @Column(columnDefinition = "TINYINT")
    private int order;

    @Column
    private String iconPath;

    @Column
    private String colorCode;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Subcategory> subcategories = new ArrayList<>();

    public Category(String code,
                    String name,
                    String description,
                    String studyGuide,
                    boolean active,
                    Integer order,
                    String iconPath,
                    String colorCode) {
        this(code, name);
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.order = order;
        this.iconPath = iconPath;
        this.colorCode = colorCode;
    }

    public Category(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Deprecated
    public Category() {

    }

    public int getId() {
        return id;
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

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isActive() {
        return active;
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return active == category.active &&
                order == category.order &&
                code.equals(category.code) &&
                name.equals(category.name) &&
                Objects.equals(description, category.description) &&
                Objects.equals(studyGuide, category.studyGuide) &&
                Objects.equals(iconPath, category.iconPath) &&
                Objects.equals(colorCode, category.colorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, description, studyGuide, active, order, iconPath, colorCode);
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
