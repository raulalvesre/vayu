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

        validateWording(wording);
        validateType(type);

        this.wording = wording;
        this.type = type;
    }

    public Question(String code,
                    String title,
                    boolean isActive,
                    int order,
                    Section section,
                    String wording,
                    QuestionType type) {
        super(code, title, isActive, order, section);

        validateWording(wording);
        validateType(type);

        this.wording = wording;
        this.type = type;
    }

    private void validateWording(String wording) {
        validateIfItIsNull(this.getClass().getSimpleName(), "wording", wording);
    }

    private void validateType(QuestionType type) {
        validateIfItIsNull(this.getClass().getSimpleName(), "type", type);
    }

    public String getWording() {
        return wording;
    }

    public QuestionType getType() {
        return type;
    }

}
