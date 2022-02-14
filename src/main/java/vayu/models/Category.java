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
    private String iconPath;
    private String colorCode;

    public Category(String code,
                    String name,
                    String description,
                    String studyGuide,
                    boolean isActive,
                    int order,
                    String iconPath,
                    String colorCode) {
        this(code, name);

        validateColorCode(colorCode);

        this.description = description;
        this.studyGuide = studyGuide;
        this.isActive = isActive;
        this.order = order;
        this.iconPath = iconPath;
        this.colorCode = colorCode;
    }

    public Category(String code,
                    String name) {
        validateCode(code);
        validateName(name);

        this.code = code;
        this.name = name;
        this.isActive = false;
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

    private void validateColorCode(String colorCode) {
        if (colorCode == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("color's code", ValidationErrorType.Null));

        if (!TextValidationService.isHexColorCode(colorCode))
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("color's code", ValidationErrorType.HexColorCode));
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

    public String getIconPath() {
        return iconPath;
    }

    public String getColorCode() {
        return colorCode;
    }

}
