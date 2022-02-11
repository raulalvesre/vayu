package vayu.models.activties;

import vayu.models.Section;
import vayu.models.activties.base.Activity;

public class Explication extends Activity {

    private String text;

    public Explication(String code,
                       String title,
                       boolean active,
                       int order,
                       Section section,
                       String text) {
        super(code, title, active, order, section);
        setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text.isBlank())
            throw new IllegalArgumentException("An explanation text should not be blank!");

        this.text = text;
    }

}
