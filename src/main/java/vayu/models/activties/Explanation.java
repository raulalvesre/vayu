package vayu.models.activties;

import vayu.enums.ValidationErrorType;
import vayu.models.Section;
import vayu.models.activties.base.Activity;
import vayu.services.ValidationErrorMessageService;

public class Explanation extends Activity {

    private final String text;

    public Explanation(String code,
                       String title,
                       Section section,
                       String text) {
        super(code, title, section);

        validateText(text);
        this.text = text;
    }

    public Explanation(String code,
                       String title,
                       boolean isActive,
                       int order,
                       Section section,
                       String text) {
        super(code, title, isActive, order, section);

        validateText(text);
        this.text = text;
    }

    private void validateText(String text) {
        if (text == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("text", ValidationErrorType.Null));

        if (text.isBlank())
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("text", ValidationErrorType.Blank));
    }

    public String getText() {
        return text;
    }

}
