package vayu.services;

import vayu.enums.ValidationErrorType;

public class ValidationService {

    public static void validateIfItIsNull(String attrName, Object object) {
        if (object == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage(attrName, ValidationErrorType.Null));
    }

    public static void validateIfItIsValidCode(String text) {
        if(text.matches("^[a-z0-9-]+$"))
            throw new IllegalArgumentException(ValidationErrorMessageService.getModelCodeMessage());
    }

    public static void validateIfIsBlankString(String attrName, String str) {
        if (str.isBlank())
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage(attrName, ValidationErrorType.Blank));
    }

    public static void validateIfIntIsWithinRange(String attrName, int num, int min, int max) {
        if (num < min || num > max)
            throw new IllegalArgumentException(ValidationErrorMessageService.getWithingRangeMessage(attrName, min, max));
    }

    public static void validateIfItIsValidHexColorCode(String attrName, String text) {
        if(text.matches("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$"))
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage(attrName, ValidationErrorType.HexColorCode));
    }

}
