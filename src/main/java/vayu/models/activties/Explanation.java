package vayu.models.activties;

import vayu.models.Section;
import vayu.models.activties.base.Activity;

public class Explanation extends Activity {

    private final String text;

    public Explanation(String code,
                       String title,
                       Section section,
                       String text) {
        super(code, title, section);

        if (text == null || text.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s text should not be null or blank");

        this.text = text;
    }

    public String getText() {
        return text;
    }

}
