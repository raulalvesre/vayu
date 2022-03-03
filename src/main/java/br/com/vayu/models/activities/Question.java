package br.com.vayu.models.activities;

import br.com.vayu.enums.QuestionType;
import br.com.vayu.models.Section;
import br.com.vayu.services.ValidationService;

public class Question extends Activity {

    private final String wording;
    private final QuestionType type;

    public Question(String code,
                    String title,
                    Section section,
                    String wording,
                    QuestionType type) {
        super(code, title, section);

        ValidationService.validateIfItIsNull("wording", wording);
        ValidationService.validateIfItIsNull("type", type);

        this.wording = wording;
        this.type = type;
    }

}
