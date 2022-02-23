package br.com.vayu.models.activities;

import br.com.vayu.models.Section;

import static br.com.vayu.services.ValidationService.*;

public abstract class Activity {

    private final String code;
    private final String title;
    private boolean active;
    private int order;
    private final Section section;

    public Activity(String code,
                    String title,
                    Section section) {
        validateIfItIsValidCode(code);
        validateIfIsBlankString("title", title);
        validateIfItIsNull("section", section);

        this.code = code;
        this.title = title;
        this.section = section;
    }

}
