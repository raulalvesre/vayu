package vayu.models;

import static vayu.services.ValidationService.*;

public class Section {

    private final String code;
    private final String name;
    private int order;
    private boolean active;
    private boolean test;
    private final Course course;

    public Section(String code,
                   String name,
                   Course course) {
        validateIfItIsValidCode(code);
        validateIfIsBlankString("name", name);
        validateIfItIsNull("course", course);

        this.code = code;
        this.name = name;
        this.order = 0;
        this.course = course;
    }

}
