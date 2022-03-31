package br.com.vayu.models;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.Objects;

@Entity
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
    private int order;

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
        this.code = code;
        this.name = name;
        this.category = category;
    }

    @Deprecated
    public Subcategory() {

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

    public Category getCategory() {
        return category;
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
