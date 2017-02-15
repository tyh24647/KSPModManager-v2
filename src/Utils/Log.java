package Utils;

import javax.annotation.Nonnull;

import static Constants.Defaults.DEBUG;
import static Constants.StrConstants.Tags.DEBUG_TAG;
import static Constants.StrConstants.Tags.ERROR_TAG;

/**
 * <p>Console logger for error and debug messages</p>
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public final class Log {
    public static void DEBUG(@Nonnull String debugMsg) {
        if (DEBUG) {
            DEBUG(DEBUG_TAG, debugMsg);
        }
    }

    public static void DEBUG(@Nonnull String customTag, @Nonnull String debugMsg) {
        if (DEBUG) {
            System.out.println(customTag.concat(debugMsg));
        }
    }

    public static void ERROR(@Nonnull String errMsg) {
        if (DEBUG) {
            System.err.println(ERROR_TAG.concat(errMsg));
        }
    }

    public static void ERROR(@Nonnull Throwable err, @Nonnull String errMsg) {
        if (DEBUG) {
            System.err.println(ERROR_TAG.concat(errMsg));
            err.printStackTrace();
        }
    }

    public static void ERROR(@Nonnull String errTitle, @Nonnull String errMsg) {
        if (DEBUG) {
            System.err.println(errTitle.concat(errMsg));
        }
    }

    public static void ERROR(@Nonnull Throwable err, @Nonnull String errTitle, @Nonnull String errMsg) {
        if (DEBUG) {
            ERROR(errTitle, errMsg);
            err.printStackTrace();
        }
    }
}
