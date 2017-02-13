package Utils;

import Constants.StrConstants;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class Validator {

    public static final boolean isValidString(String val) {
        if (val == null) {
            return false;
        }

        return !val.isEmpty() || !val.equals(StrConstants.EMPTY);
    }
}
