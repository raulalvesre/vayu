package vayu.models;

import vayu.enums.ValidationErrorType;
import vayu.services.ValidationService;
import vayu.services.ValidationErrorMessageService;

import static vayu.services.ValidationService.*;

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
        validateIfItIsNull("code", code);
        validateIfIsBlankString("code", code);
        validateIfItIsValidCode(code);
    }

    private void validateName(String name) {
        validateIfItIsNull("name", name);
        validateIfIsBlankString("name", name);
    }

    private void validateColorCode(String colorCode) {
        validateIfItIsNull("color code", colorCode);
        validateIfItIsValidHexColorCode("color code", colorCode);
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
