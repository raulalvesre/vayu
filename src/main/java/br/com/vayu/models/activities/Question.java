package br.com.vayu.models.activities;

import br.com.vayu.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("question")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Question extends Activity {

    @Column(nullable = false)
    private String wording;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType question_type;

}
