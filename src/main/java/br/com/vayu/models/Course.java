package br.com.vayu.models;

import br.com.vayu.dto.CourseFormDTO;
import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

    public Course(CourseFormDTO form, Subcategory subcategory) {
        this.id = form.getId();
        this.name = form.getName();
        this.code = form.getCode();
        this.estimatedHoursToFinish = form.getEstimatedHoursToFinish();
        this.instructorName = form.getInstructorName();
        this.visible = form.isVisible();
        this.targetAudience = form.getTargetAudience();
        this.syllabus = form.getSyllabus();
        this.developedAbilities = form.getDevelopedAbilities();
        this.subcategory = subcategory;
    }

    public void merge(CourseFormDTO form, Subcategory subcategory) {
        this.code = form.getCode();
        this.name = form.getName();
        this.estimatedHoursToFinish = form.getEstimatedHoursToFinish();
        this.visible = form.isVisible();
        this.targetAudience = form.getTargetAudience();
        this.instructorName = form.getInstructorName();
        this.syllabus = form.getSyllabus();
        this.developedAbilities = form.getDevelopedAbilities();
        this.subcategory = subcategory;
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

}
