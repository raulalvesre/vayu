package vayu.models;

import vayu.enums.ValidationErrorType;
import vayu.services.TextValidationService;
import vayu.services.ValidationErrorMessageService;

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
        if (code == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("code", ValidationErrorType.Null));

        if (code.isBlank())
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("code", ValidationErrorType.Blank));

        if (!TextValidationService.isValidCode(code))
            throw new IllegalArgumentException(ValidationErrorMessageService.getModelCodeMessage());
    }

    private void validateName(String name) {
        if (name == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("name", ValidationErrorType.Null));

        if (name.isBlank())
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("name", ValidationErrorType.Blank));
    }

    private void validateCourse(Course course) {
        if (course == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("course", ValidationErrorType.Null));
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
