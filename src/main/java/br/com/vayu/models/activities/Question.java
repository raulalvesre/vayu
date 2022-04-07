package br.com.vayu.models.activities;

import br.com.vayu.enums.QuestionType;
import br.com.vayu.models.Section;

import javax.persistence.*;

@Entity
@DiscriminatorValue("question")
public class Question extends Activity {

    @Column(nullable = false)
    private String wording;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType question_type;

    public Question(String code,
                    String title,
                    Section section,
                    String wording,
                    QuestionType type) {
        super(code, title, section);

        this.wording = wording;
        this.question_type = type;
    }

    @Deprecated
    public Question() {
        super();
    }

}
