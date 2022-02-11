package vayu.models;

import vayu.utils.TextValidator;

public class Section {

    private String code;
    private String name;
    private int order;
    private boolean active;
    private boolean isTest;
    private Course course;

    public Section(String code,
                   String name,
                   Course course) {
        setCode(code);
        setName(name);
        this.active = false;
        this.isTest = false;
        setCourse(course);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Code should not be null");

        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Code should only contain lower case letters, numbers and '-'");

        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Name should not be null or blank");

        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isTest() {
        return isTest;
    }

    public void setTest(boolean test) {
        isTest = test;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if (course == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Course should not be null");

        this.course = course;
    }

}
