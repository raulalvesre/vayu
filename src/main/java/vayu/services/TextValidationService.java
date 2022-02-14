package vayu.services;

public class TextValidationService {

    public static boolean isValidCode(String text) {
        return text.matches("^[a-z0-9-]+$");
    }

    public static boolean isHexColorCode(String text) {
        return text.matches("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");
    }

}
