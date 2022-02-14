package vayu.models.activties;

import vayu.enums.QuestionType;
import vayu.enums.ValidationErrorType;
import vayu.models.Section;
import vayu.models.activties.base.Activity;
import vayu.services.ValidationErrorMessageService;

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
        if (wording == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("wording", ValidationErrorType.Null));
    }

    private void validateType(QuestionType type) {
        if (type == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("type", ValidationErrorType.Null));
    }

    public String getWording() {
        return wording;
    }

    public QuestionType getType() {
        return type;
    }

}
