package br.com.vayu.models.activities;

import br.com.vayu.models.Section;

import javax.persistence.*;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name="activity_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "section_id", nullable = false)
    private Section section;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String title;

    private boolean active;

    private int order;

    public Activity(String code,
                    String title,
                    Section section) {
        this.code = code;
        this.title = title;
        this.section = section;
    }

    @Deprecated
    public Activity() {}

}
