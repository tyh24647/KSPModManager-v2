package Utils;

import Constants.Defaults;

import javax.annotation.Nonnull;

/**
 * <p>Console logger for error and debug messages</p>
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class Log {

    public static void DEBUG(@Nonnull String msg) {
        if (Defaults.DEBUG_MODE) {

        }
    }

    public static void ERROR(@Nonnull String msg) {
        if (Defaults.DEBUG_MODE) {
            System.err.println(msg);

        }
    }
}
