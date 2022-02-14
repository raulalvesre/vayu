package vayu.models;

import vayu.enums.ValidationErrorType;
import vayu.services.ValidationService;
import vayu.services.ValidationErrorMessageService;

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
        validateCode(code);
        validateName(name);
        validateCourse(course);

        this.code = code;
        this.name = name;
        this.order = 0;
        this.isActive = false;
        this.isTest = false;
        this.course = course;
    }

    private void validateCode(String code) {
        validateIfItIsNull("code", code);
        validateIfIsBlankString("code", code);
        validateIfItIsValidCode(code);
    }

    private void validateName(String name) {
        validateIfItIsNull("name", name);
        validateIfIsBlankString("name", name);
    }

    private void validateCourse(Course course) {
        validateIfItIsNull("course", course);
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
