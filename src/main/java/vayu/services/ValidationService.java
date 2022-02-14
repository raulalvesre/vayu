package vayu.services;

import vayu.enums.ValidationErrorType;

public class ValidationService {

    public static void validateIfItIsNull(String className, String attrName, Object object) {
        if (object == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage(className, attrName, ValidationErrorType.Null));
    }

    public static void validateIfItIsValidCode(String className, String text) {
        if(text.matches("^[a-z0-9-]+$"))
            throw new IllegalArgumentException(ValidationErrorMessageService.getModelCodeMessage(className));
    }

    public static void validateIfIsBlankString(String className, String attrName, String str) {
        if (str.isBlank())
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage(className, attrName, ValidationErrorType.Blank));
    }

    public static void validateIfIntIsWithinRange(String className, String attrName, int num, int min, int max) {
        if (num < min || num > max)
            throw new IllegalArgumentException(ValidationErrorMessageService.getWithingRangeMessage(className, attrName, min, max));
    }

    public static void validateIfItIsValidHexColorCode(String className, String attrName, String text) {
        if(text.matches("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$"))
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage(className, attrName, ValidationErrorType.HexColorCode));
    }

}
