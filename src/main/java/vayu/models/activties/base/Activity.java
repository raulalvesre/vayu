package vayu.models.activties.base;

import vayu.models.Section;

import static vayu.services.ValidationService.*;

public abstract class Activity {

    private final String code;
    private final String title;
    private boolean isActive;
    private int order;
    private final Section section;

    public Activity(String code,
                    String title,
                    Section section) {
        validateIfItIsValidCode(this.getClass().getSimpleName(), code);
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "title", title);
        validateIfItIsNull(this.getClass().getSimpleName(), "section", section);

        this.code = code;
        this.title = title;
        this.isActive = false;
        this.section = section;
    }

}
