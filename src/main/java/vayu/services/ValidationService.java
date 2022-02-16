package vayu.services;

public class ValidationService {

    public static final String MODEL_CODE_REGEX = "^[a-z0-9-]+$";
    public static final String HEX_COLOR_REGEX = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";
    public static final String URL_REGEX = "https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}" +
            "\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)";

    public static void validateIfItIsNull(String className, String attrName, Object object) {
        if (object == null)
            throw new IllegalArgumentException(className + "'s " +  attrName + " should not be null");
    }

    public static void validateIfItIsValidCode(String className, String code) {
        validateIfIsNullOrBlankString(className, "code", code);

        if (!code.matches(MODEL_CODE_REGEX))
            throw new IllegalArgumentException(className + "'s code should only contain lower case letters, " +
                    "numbers and '-'");
    }

    public static void validateIfIsNullOrBlankString(String className, String attrName, String str) {
        validateIfItIsNull(className, attrName, str);

        if (str.isBlank())
            throw new IllegalArgumentException(className + "'s " +  attrName + " should not be blank");
    }

    public static void validateIfIntIsWithinRange(String className, String attrName, int num, int min, int max) {
        if (num < min || num > max)
            throw new IllegalArgumentException(className + "'s " + attrName + " should be between "
                    + min + " and " + max);
    }

    public static void validateIfIsValidHexColorCode(String className, String attrName, String hexCode) {
        validateIfItIsNull(className, attrName, hexCode);

        if (!hexCode.matches(HEX_COLOR_REGEX))
            throw new IllegalArgumentException(className + "'s " + attrName + " is not a valid Hex Color Code");
    }

    public static void validateIfItIsValidURL(String className, String attrName, String URL) {
        validateIfItIsNull(className, attrName, URL);

        if (!URL.matches(URL_REGEX))
            throw new IllegalArgumentException(className + "'s " + attrName + " is not a valid URL");
    }

}
