package vayu.services;

import vayu.enums.ValidationErrorType;

public class ValidationErrorMessageService {

    public static String getMessage(String className, String classAttribute, ValidationErrorType type) {
        return switch (type) {
            case Null -> className + "'s " +  classAttribute + " should not be null";
            case Blank -> className + "'s " +  classAttribute + " should not be blank";
            case ModelCode -> className + "'s code should only contain lower case letters, numbers and '-'";
            case WithinRange -> className + "'s " + classAttribute + " is not within correct range";
            case HexColorCode -> className + "'s " + classAttribute + " is not a valid Hex Color Code";
        };
    }

    public static String getModelCodeMessage(String className) {
        return className + "'s code should only contain lower case letters, numbers and '-'";
    }

    public static String getWithingRangeMessage(String className, String classAttribute, int min, int max) {
        return className + "'s " + classAttribute + " should be between " + min + " and " + max;
    }

}
