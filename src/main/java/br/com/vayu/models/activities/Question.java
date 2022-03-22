package br.com.vayu.models.activities;

import br.com.vayu.enums.QuestionType;
import br.com.vayu.models.Section;

import javax.persistence.*;

import static br.com.vayu.services.ValidationService.validateIfItIsNull;

@Entity
@Table(name = "question")
public class Question extends Activity {

    @Column(nullable = false)
    private String wording;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    public Question(String code,
                    String title,
                    Section section,
                    String wording,
                    QuestionType type) {
        super(code, title, section);

        validateIfItIsNull("wording", wording);
        validateIfItIsNull("type", type);

        this.wording = wording;
        this.type = type;
    }

    @Deprecated
    public Question() {
        super();
    }

}
