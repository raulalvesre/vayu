package vayu.models;

import vayu.enums.ValidationErrorType;
import vayu.services.TextValidationService;
import vayu.services.ValidationErrorMessageService;

public class Category {

    private final String code;
    private final String name;
    private String description;
    private String studyGuide;
    private boolean isActive;
    private int order;
    private String imagePath;
    private String colorCode;

    public Category(String code,
                    String name,
                    String description,
                    String studyGuide,
                    boolean isActive,
                    int order,
                    String imagePath,
                    String colorCode) {
        this(code, name);

        this.description = description;
        this.studyGuide = studyGuide;
        this.isActive = isActive;
        this.order = order;
        this.imagePath = imagePath;
        this.colorCode = colorCode;
    }

    public Category(String code,
                    String name) {
        validateCode(code);
        validateName(name);

        this.code = code;
        this.isActive = false;
        this.name = name;
    }

    private void validateCode(String code) {
        if (code == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("code", ValidationErrorType.Null));

        if (!TextValidationService.isValidCode(code))
            throw new IllegalArgumentException(ValidationErrorMessageService.getModelCodeMessage());
    }

    private void validateName(String name) {
        if (name == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("name", ValidationErrorType.Null));

        if (name.isBlank())
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("name", ValidationErrorType.Blank));
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

    public String getImagePath() {
        return imagePath;
    }

    public String getColorCode() {
        return colorCode;
    }

}
