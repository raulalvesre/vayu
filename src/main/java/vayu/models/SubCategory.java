package vayu.models;

import vayu.enums.ValidationErrorType;
import vayu.services.TextValidationService;
import vayu.services.ValidationErrorMessageService;

public class SubCategory {

    private final String code;
    private final String name;
    private String description;
    private String studyGuide;
    private boolean isActive;
    private int order;
    private final Category category;

    public SubCategory(String code,
                       String name,
                       String description,
                       String studyGuide,
                       boolean isActive,
                       int order,
                       Category category) {
        this(code, name, category);

        this.description = description;
        this.studyGuide = studyGuide;
        this.isActive = isActive;
        this.order = order;
    }

    public SubCategory(String code,
                       String name,
                       Category category) {
        validateCode(code);
        validateName(name);
        validateCategory(category);

        this.code = code;
        this.name = name;
        this.category = category;
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

    private void validateCategory(Category category) {
        if (category == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("category", ValidationErrorType.Null));
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getOrder() {
        return order;
    }

    public Category getCategory() {
        return category;
    }

}
