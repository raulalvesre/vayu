package vayu.models;

import static vayu.services.ValidationService.*;

public class Section {

    private final String code;
    private final String name;
    private int order;
    private boolean isActive;
    private boolean isTest;
    private final Course course;

    public Section(String code,
                   String name,
                   Course course) {
        validateIfItIsValidCode(this.getClass().getSimpleName(), code);
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "name", name);
        validateIfItIsNull(this.getClass().getSimpleName(), "course", course);

        this.code = code;
        this.name = name;
        this.order = 0;
        this.isActive = false;
        this.isTest = false;
        this.course = course;
    }

}
