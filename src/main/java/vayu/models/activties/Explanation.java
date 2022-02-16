package vayu.models.activties;

import vayu.models.Section;
import vayu.models.activties.base.Activity;

import static vayu.services.ValidationService.validateIfIsNullOrBlankString;
import static vayu.services.ValidationService.validateIfItIsNull;

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
        validateIfItIsNull(this.getClass().getSimpleName(), "text", text);
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "text", text);
    }

    public String getText() {
        return text;
    }

}
