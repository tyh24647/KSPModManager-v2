package Utils;

import Constants.StrConstants;

import java.util.List;

/**
 * <p>
 *     Function object which validates the state of the given parameters in
 *     order to determine whether or not the parameter can be used in execution.
 * </p>
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class Validator {

    /**
     *
     *
     * @param val   The value to evaluate
     * @return      Whether or not the string is not null or empty
     * @see         Objects.Users.UserAttributes.Credentials for usage
     */
    public static boolean isValidStr(String val) {
        if (val == null) {
            return false;
        }

        return !val.isEmpty() || !val.equals(StrConstants.EMPTY);
    }

    /**
     *
     * @param list
     * @return
     */
    public static boolean strLstIsValid(List<String> list) {
        return strArrIsValid((String[]) list.toArray());
    }

    /**
     *
     * @param arr
     * @return
     */
    public static boolean strArrIsValid(String[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        boolean isValid = true;
        for (int i = 0; i < arr.length; i++) { if (!isValidStr(arr[i])) { isValid = false; break; } }
        return isValid;
    }


}
