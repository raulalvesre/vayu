package br.com.vayu.models.activities;

import br.com.vayu.models.Section;
import br.com.vayu.services.ValidationService;

public class Explanation extends Activity {

    private final String text;

    public Explanation(String code,
                       String title,
                       Section section,
                       String text) {
        super(code, title, section);

        ValidationService.validateIfIsBlankString("text", text);
        this.text = text;
    }

}
