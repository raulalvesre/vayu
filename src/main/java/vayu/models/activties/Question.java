package vayu.models.activties;

import vayu.enums.QuestionType;
import vayu.models.Section;
import vayu.models.activties.base.Activity;

import static vayu.services.ValidationService.validateIfItIsNull;

public class Question extends Activity {

    private final String wording;
    private final QuestionType type;

    public Question(String code,
                    String title,
                    Section section,
                    String wording,
                    QuestionType type) {
        super(code, title, section);

        validateIfItIsNull(this.getClass().getSimpleName(), "wording", wording);
        validateIfItIsNull(this.getClass().getSimpleName(), "type", type);

        this.wording = wording;
        this.type = type;
    }

}
