package br.com.vayu.models.activities.questions;

import br.com.vayu.models.activities.Question;

import javax.persistence.*;

@Entity
@Table(name = "alternative")
public class Alternative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false)
    private String text;

    @Column(columnDefinition = "TINYINT")
    private int order;

    private boolean correct;
    private String justification;

    public Alternative(String text, boolean correct, Question question) {
        this.text = text;
        this.correct = correct;
        this.question = question;
    }

    @Deprecated
    public Alternative() {}

}
