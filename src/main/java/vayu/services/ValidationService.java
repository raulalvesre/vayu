package vayu.services;

public class ValidationService {

    public static final String MODEL_CODE_REGEX = "^[a-z0-9-]+$";
    public static final String HEX_COLOR_REGEX = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";
    public static final String URL_REGEX = 
            "https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)";

    public static void validateIfItIsNull(String attrName, Object object) {
        if (object == null)
            throw new IllegalArgumentException(attrName + " should not be null");
    }

    public static void validateIfItIsValidCode(String code) {
        validateIfIsBlankString("code", code);

        if (!code.matches(MODEL_CODE_REGEX)) {
            throw new IllegalArgumentException("Code should only contain lower case letters," +
                    " numbers and '-'");
        }
    }

    public static void validateIfIsBlankString(String attrName, String str) {
        validateIfItIsNull(attrName, str);

        if (str.isBlank())
            throw new IllegalArgumentException(attrName + " should not be blank");
    }

    public static void validateIfIntIsWithinRange(String attrName, int num, int min, int max) {
        if (num < min || num > max)
            throw new IllegalArgumentException(attrName + " should be between " + min + " and " + max);
    }

    public static void validateIfIsValidHexColorCode(String attrName, String hexCode) {
        validateIfItIsNull(attrName, hexCode);

        if (!hexCode.matches(HEX_COLOR_REGEX))
            throw new IllegalArgumentException(attrName + " is not a valid Hex Color Code");
    }

    public static void validateIfItIsValidURL(String attrName, String URL) {
        validateIfItIsNull(attrName, URL);

        if (!URL.matches(URL_REGEX))
            throw new IllegalArgumentException(attrName + " is not a valid URL");
    }

}
