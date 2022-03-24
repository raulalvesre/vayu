package br.com.vayu.models;

import javax.persistence.*;

import static br.com.vayu.services.ValidationService.*;

@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TINYINT")
    private int order;

    private boolean active;
    private boolean test;

    public Section(String code,
                   String name,
                   Course course) {
        validateIfItIsValidCode(code);
        validateIfIsBlankString("name", name);
        validateIfItIsNull("course", course);

        this.code = code;
        this.name = name;
        this.order = 0;
        this.course = course;
    }

    @Deprecated
    public Section() {}
}
