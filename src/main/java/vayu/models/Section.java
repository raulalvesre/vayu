package vayu.models;

import vayu.utils.TextValidator;

public class Section {

    private final String code;
    private final String name;
    private final int order;
    private final boolean active;
    private final boolean isTest;
    private final Course course;

    public Section(String code,
                   String name,
                   int order,
                   boolean active,
                   boolean isTest,
                   Course course) {
        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Code should only contain lower case letters, numbers and '-'");

        this.code = code;
        this.name = name;
        this.order = order;
        this.active = active;
        this.isTest = isTest;
        this.course = course;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isTest() {
        return isTest;
    }

    public Course getCourse() {
        return course;
    }
}
