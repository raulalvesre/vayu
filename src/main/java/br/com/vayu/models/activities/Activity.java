package br.com.vayu.models.activities;

import br.com.vayu.models.Section;

import javax.persistence.*;

import static br.com.vayu.services.ValidationService.*;

@MappedSuperclass
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

    @Column(columnDefinition = "TINYINT")
    private int order;

    public Activity(String code,
                    String title,
                    Section section) {
        validateIfItIsValidCode(code);
        validateIfIsBlankString("title", title);
        validateIfItIsNull("section", section);

        this.code = code;
        this.title = title;
        this.section = section;
    }

    @Deprecated
    public Activity() {}

}
