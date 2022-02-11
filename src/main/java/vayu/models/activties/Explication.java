package vayu.models.activties;

import vayu.models.Section;
import vayu.models.activties.base.Activity;

public class Explication extends Activity {

    private String text;

    public Explication(String code,
                       String title,
                       Section section,
                       String text) {
        super(code, title, section);
        setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text == null || text.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Text should not be null or blank");

        this.text = text;
    }

}
