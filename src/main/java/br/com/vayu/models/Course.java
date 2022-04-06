package br.com.vayu.models;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(referencedColumnName = "id", name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int estimatedHoursToFinish;

    private boolean visible;

    private String targetAudience;

    private  String instructorName;

    @Type(type="text")
    private String syllabus;

    private String developedAbilities;

    public Course(String code,
                  String name,
                  int estimatedHoursToFinish,
                  boolean visible,
                  String targetAudience,
                  String instructorName,
                  String syllabus,
                  String developedAbilities,
                  Subcategory subcategory) {
        this(code, name, estimatedHoursToFinish, instructorName, subcategory);

        this.visible = visible;
        this.targetAudience = targetAudience;
        this.syllabus = syllabus;
        this.developedAbilities = developedAbilities;
    }

    public Course(String code,
                  String name,
                  int estimatedHoursToFinish,
                  String instructorName,
                  Subcategory subcategory) {
        this.code = code;
        this.name = name;
        this.estimatedHoursToFinish = estimatedHoursToFinish;
        this.instructorName = instructorName;
        this.subcategory = subcategory;
    }

    @Deprecated
    public Course() {

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

    public int getEstimatedHoursToFinish() {
        return estimatedHoursToFinish;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public String getDevelopedAbilities() {
        return developedAbilities;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return estimatedHoursToFinish == course.estimatedHoursToFinish &&
                visible == course.visible && code.equals(course.code) &&
               name.equals(course.name) &&
               Objects.equals(targetAudience, course.targetAudience) &&
               instructorName.equals(course.instructorName) &&
               Objects.equals(syllabus, course.syllabus) &&
               Objects.equals(developedAbilities, course.developedAbilities) &&
               subcategory.equals(course.subcategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code,
                name,
                estimatedHoursToFinish,
                visible,
                targetAudience,
                instructorName,
                syllabus,
                developedAbilities,
                subcategory);
    }

    @Override
    public String toString() {
        return "Course{" +
               "code='" + code + '\'' +
               ", name='" + name + '\'' +
               ", estimatedHoursToFinish=" + estimatedHoursToFinish +
               ", visible=" + visible +
               ", targetAudience='" + targetAudience + '\'' +
               ", instructorName='" + instructorName + '\'' +
               ", syllabus='" + syllabus + '\'' +
               ", developedAbilities='" + developedAbilities + '\'' +
               ", subCategory=" + subcategory.getName() +
               '}';
    }

}
