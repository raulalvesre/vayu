package vayu.services;

import vayu.enums.ValidationErrorType;

public class ValidationErrorMessageService {

    public static String getMessage(String classAttribute, ValidationErrorType type) {
        String classWhoCalledName = Thread.currentThread().getStackTrace()[2].getClassName();

        return switch (type) {
            case Null -> classWhoCalledName + "'s " +  classAttribute + " should not be null";
            case Blank -> classWhoCalledName + "'s " +  classAttribute + " should not be blank";
            case ModelCode -> classWhoCalledName + "'s code should only contain lower case letters, numbers and '-'";
            case Range -> classWhoCalledName + "'s " + classAttribute + " is not within correct range";
        };
    }

    public static String getModelCodeMessage() {
        String classWhoCalledName = Thread.currentThread().getStackTrace()[2].getClassName();

        return classWhoCalledName + "'s code should only contain lower case letters, numbers and '-'";
    }

    public static String getRangeMessage(String classAttribute, int min, int max) {
        String classWhoCalledName = Thread.currentThread().getStackTrace()[2].getClassName();

        return classWhoCalledName + "'s " + classAttribute + " should be between " + min + " and " + max;
    }

}
