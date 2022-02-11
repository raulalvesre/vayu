package vayu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextValidator {

    public static boolean containsOnlyNumbersAndLowerCaseLetters(String text) {
        Pattern pattern = Pattern.compile("^[a-z0-9-]+$");
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }

}
