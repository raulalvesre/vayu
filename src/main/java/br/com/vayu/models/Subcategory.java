package br.com.vayu.models;

import br.com.vayu.dto.SubcategoryFormDTO;
import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
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
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(referencedColumnName = "id", name = "category_id", nullable = false)
    private Category category;

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

    @OneToMany(
            mappedBy = "subcategory",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Course> courses = new ArrayList<>();

    public Subcategory(SubcategoryFormDTO subcategoryFormDTO, Category category) {
        this.id = subcategoryFormDTO.getId();
        this.code = subcategoryFormDTO.getCode();
        this.name = subcategoryFormDTO.getName();
        this.description = subcategoryFormDTO.getDescription();
        this.studyGuide = subcategoryFormDTO.getStudyGuide();
        this.active = subcategoryFormDTO.isActive();
        this.order = subcategoryFormDTO.getOrder();
        this.category = category;
    }

    public void merge(SubcategoryFormDTO form, Category category) {
        this.code = form.getCode();
        this.name = form.getName();
        this.description = form.getDescription();
        this.studyGuide = form.getStudyGuide();
        this.active = form.isActive();
        this.order = form.getOrder();
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subcategory that = (Subcategory) o;
        return active == that.active &&
               order == that.order &&
               code.equals(that.code) &&
               name.equals(that.name) &&
               Objects.equals(description, that.description) &&
               Objects.equals(studyGuide, that.studyGuide) &&
               category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, description, studyGuide, active, order, category);
    }

}
