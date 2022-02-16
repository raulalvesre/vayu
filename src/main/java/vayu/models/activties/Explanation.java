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

        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "text", text);
        this.text = text;
    }

}
