package Utils.OSUtils;

import Utils.Log;
import Utils.OSUtils.MacOSX.MacOSXUtils;
import com.google.common.base.Preconditions;

import javax.swing.*;
import java.awt.*;

import static Constants.BoolConstants.IS_MAC;
import static Constants.StrConstants.Messages.Debug.Actions.CONFIG_L_AND_F;
import static Constants.StrConstants.Messages.Debug.Success.L_AND_F_CONFIG_SUCCESS;
import static Constants.StrConstants.Tags.OS_UTILS_TAG;

/**
 * @author Tyler Hostager
 * @version 2/14/17
 */
public class OSUtils {
    private static final String TAG = OS_UTILS_TAG;

    public static void configureOSLookAndFeel(Window window) {
        Log.DEBUG(TAG, CONFIG_L_AND_F);
        Preconditions.checkNotNull(window);

        if (IS_MAC) {
            MacOSXUtils.configureOSXProperties();
            MacOSXUtils.enableOSXFullscreen(window);
        }

        configureOSLookAndFeel();
    }

    public static void configureOSLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Log.DEBUG(TAG, L_AND_F_CONFIG_SUCCESS);
        } catch (Exception e) {
            Log.ERROR(e.getMessage());
        }
    }
}
