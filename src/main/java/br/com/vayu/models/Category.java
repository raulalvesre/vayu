package br.com.vayu.models;

import javax.persistence.*;
import java.util.Objects;

import static br.com.vayu.services.ValidationService.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "study_guide")
    private String studyGuide;

    private boolean active;

    @Column(columnDefinition = "TINYINT")
    private int order;

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "color_code")
    private String colorCode;

    public Category(int id,
                    String code,
                    String name,
                    String description,
                    String studyGuide,
                    boolean active,
                    int order,
                    String iconPath,
                    String colorCode) {
        this(code, name, description, studyGuide, active, order, iconPath, colorCode);

        this.id = id;
    }

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

    public Category(String code, String name) {
        validateIfItIsValidCode(code);
        validateIfIsBlankString("name", name);

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
