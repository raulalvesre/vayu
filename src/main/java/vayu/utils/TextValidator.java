package vayu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextValidator {

    public static boolean containsOnlyNumbersAndLowerCaseLetters(String text) {
        Pattern pattern = Pattern.compile("^[a-z0-9-]+$");
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }

    public static boolean isHexColorCode(String text) {
        Pattern pattern = Pattern.compile("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }

}
