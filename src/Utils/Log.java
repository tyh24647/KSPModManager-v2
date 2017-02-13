package Utils;

import Constants.Defaults;
import Constants.StrConstants;

import javax.annotation.Nonnull;

/**
 * <p>Console logger for error and debug messages</p>
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class Log {

    public static void DEBUG(@Nonnull String debugMsg) {
        if (Defaults.DEBUG) {
            System.out.println(StrConstants.DEBUG_TAG.concat(debugMsg));
        }
    }

    public static void DEBUG(@Nonnull String customTag, @Nonnull String debugMsg) {
        if (Defaults.DEBUG) {
            System.out.println(customTag.concat(debugMsg));
        }
    }

    public static void ERROR(@Nonnull String errMsg) {
        if (Defaults.DEBUG) {
            System.err.println(StrConstants.ERROR_TAG.concat(errMsg));
        }
    }

    public static void ERROR(@Nonnull Throwable err, @Nonnull String errMsg) {
        if (Defaults.DEBUG) {
            System.err.println(StrConstants.ERROR_TAG.concat(errMsg));
            err.printStackTrace();
        }
    }

    public static void ERROR(@Nonnull String customErrTag, @Nonnull String errMsg) {
        if (Defaults.DEBUG) {
            System.err.println(customErrTag.concat(errMsg));
        }
    }

}
