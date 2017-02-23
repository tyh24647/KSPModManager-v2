package Constants;

import java.io.File;

import static org.apache.commons.lang.SystemUtils.*;

/**
 * @author Tyler Hostager
 * @version 2/14/17
 */
public final class BoolConstants {
    public static final boolean IS_MAC = IS_OS_MAC || IS_OS_MAC_OSX;
    public static final boolean IS_WINDOWS = IS_OS_WINDOWS || IS_OS_WINDOWS_7 || IS_OS_WINDOWS_VISTA || IS_OS_WINDOWS_XP;
    public static final boolean IS_SOLARIS = IS_OS_SOLARIS;
    public static final boolean IS_LINUX = IS_OS_LINUX || IS_OS_UNIX;
    public static final boolean IS_UNKNOWN = !IS_MAC && IS_WINDOWS && !IS_SOLARIS && IS_LINUX;
    public static final boolean IS_USING_STEAM = checkIfUsingSteam();
    public static final boolean IS_STANDALONE = !IS_USING_STEAM;

    private static boolean checkIfUsingSteam() {
        return new File(IS_MAC ?
                StrConstants.SystemPreferences.MacOSX.APP_DATA_PATH :
                StrConstants.SystemPreferences.Windows.APP_DATA_PATH
        ).exists();
    }
}
