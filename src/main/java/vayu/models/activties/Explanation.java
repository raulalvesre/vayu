package vayu.models.activties;

import vayu.models.Section;

import static vayu.services.ValidationService.validateIfIsBlankString;

public class Explanation extends Activity {

    private final String text;

    public Explanation(String code,
                       String title,
                       Section section,
                       String text) {
        super(code, title, section);

        validateIfIsBlankString("text", text);
        this.text = text;
    }

}
