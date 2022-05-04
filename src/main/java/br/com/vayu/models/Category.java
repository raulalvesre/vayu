package br.com.vayu.models;

import br.com.vayu.dto.CategoryFormDTO;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    private String studyGuide;

    private boolean active;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.IntegerType")
    private int order;

    private String iconPath;

    private String colorCode;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Subcategory> subcategories = new ArrayList<>();


    public Category(CategoryFormDTO categoryDTO) {
        this.id = categoryDTO.getId();
        this.code = categoryDTO.getCode();
        this.name = categoryDTO.getName();
        this.description = categoryDTO.getDescription();
        this.studyGuide = categoryDTO.getStudyGuide();
        this.active = categoryDTO.isActive();
        this.order = categoryDTO.getOrder();
        this.iconPath = categoryDTO.getIconPath();
        this.colorCode = categoryDTO.getColorCode();
    }

    public void merge(CategoryFormDTO form) {
        this.code = form.getCode();
        this.name = form.getName();
        this.description = form.getDescription();
        this.studyGuide = form.getStudyGuide();
        this.active = form.isActive();
        this.order = form.getOrder();
        this.iconPath = form.getIconPath();
        this.colorCode = form.getColorCode();
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

}
