package Utils.StringUtils;

import Utils.Log;

import static Constants.StrConstants.*;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class KSPMMStringFormatter extends StringUtils {
    private static final String TAG = "KSPMMStringFormatter: ";

    public static String format(String str) {
        Log.DEBUG(TAG, "formatting \"" + str + "\"");
        String formattedStr = str;
        if (formattedStr.contains(BACKSLASH) || formattedStr.contains("\b") || formattedStr.contains("\f") || formattedStr.contains("\n") || formattedStr.contains(("\t"))) {
            formattedStr = formattedStr.replaceAll(BACKSLASH, FORMATTED_SPACE);
        }
        if (str.contains(SPACE)) {
            formattedStr = formatStrWithSpaces(formattedStr, false);
        }

        Log.DEBUG(TAG, "String successfully formatted: \"" + str + "\" --> \"" + formattedStr + "\"");

        /*
        TODO: Other conditions
         */

        return formattedStr;

    }

    public static String formatStrWithSpaces(String str, boolean allowsSpaces) {
        if (!allowsSpaces) {
            return str.replaceAll(SPACE, FORMATTED_SPACE);
        }

        return EMPTY;
    }
}