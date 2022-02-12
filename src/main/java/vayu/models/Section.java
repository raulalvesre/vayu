package vayu.models;

import vayu.utils.TextValidator;

public class Section {

    private final String code;
    private final String name;
    private int order;
    private boolean isActive;
    private boolean isTest;
    private final Course course;

    public Section(String code,
                   String name,
                   int order,
                   boolean isActive,
                   boolean isTest,
                   Course course) {
        this(code, name, course);

        this.order = order;
        this.isActive = isActive;
        this.isTest = isTest;
    }

    public Section(String code,
                   String name,
                   Course course) {
        if (code == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s code should not be null");

        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s code should only contain lower case letters, numbers and '-'");

        if (name == null || name.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s name should not be null or blank");

        if (course == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s course should not be null");

        this.code = code;
        this.name = name;
        this.order = 0;
        this.isActive = false;
        this.isTest = false;
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
        return isActive;
    }

    public boolean isTest() {
        return isTest;
    }

    public Course getCourse() {
        return course;
    }

}
