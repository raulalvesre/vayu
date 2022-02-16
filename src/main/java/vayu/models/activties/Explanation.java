package vayu.models.activties;

import vayu.models.Section;

import static vayu.services.ValidationService.validateIfIsNullOrBlankString;

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
