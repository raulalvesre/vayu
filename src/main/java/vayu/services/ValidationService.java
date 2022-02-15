package vayu.services;

public class ValidationService {

    public static void validateIfItIsNull(String className, String attrName, Object object) {
        if (object == null)
            throw new IllegalArgumentException(className + "'s " +  attrName + " should not be null");
    }

    public static void validateIfItIsValidCode(String className, String text) {
        if(text.matches("^[a-z0-9-]+$"))
            throw new IllegalArgumentException(className + "'s code should only contain lower case letters, numbers and '-'");
    }

    public static void validateIfIsBlankString(String className, String attrName, String str) {
        if (str.isBlank())
            throw new IllegalArgumentException(className + "'s " +  attrName + " should not be blank");
    }

    public static void validateIfIntIsWithinRange(String className, String attrName, int num, int min, int max) {
        if (num < min || num > max)
            throw new IllegalArgumentException(className + "'s " + attrName + " should be between " + min + " and " + max);
    }

    public static void validateIfItIsValidHexColorCode(String className, String attrName, String text) {
        if(text.matches("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$"))
            throw new IllegalArgumentException(className + "'s " + attrName + " is not a valid Hex Color Code");
    }

}
