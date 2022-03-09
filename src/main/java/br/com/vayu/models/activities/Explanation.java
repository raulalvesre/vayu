package br.com.vayu.models.activities;

import br.com.vayu.models.Section;

import static br.com.vayu.services.ValidationService.validateIfIsBlankString;

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
