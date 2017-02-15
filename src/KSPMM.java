import Constants.StrConstants;
import Controllers.MainViewController;
import Models.KSPModManager;
import Objects.Users.User;
import Objects.Users.UserAttributes.Credentials;
import UserInterface.KSPMMMainView;
import Utils.Log;

import javax.swing.*;

import static Constants.StrConstants.ApplicationDefaults.DEFAULT_PASSWORD;
import static Constants.StrConstants.ApplicationDefaults.DEFAULT_USERNAME;
import static Constants.StrConstants.Messages.Debug.Actions.APP_LAUNCH;
import static Constants.StrConstants.Messages.Debug.InitializationMsgs.*;
import static Constants.StrConstants.Messages.Debug.Success.APP_LAUNCH_SUCCESS;

/**
 * <p>The main driver for the application</p>
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class KSPMM {
    private static final String TAG = StrConstants.generateTagForName(KSPMM.class.getSimpleName());

    /**
     * <p>The main method for running the program</p>
     * @param args  The arguments taken in to run the application.
     */
    public static void main(String[] args) {
        try {
            Log.DEBUG("Setting default look and feel to system value...");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Log.DEBUG("UIManager", "Look and feel set successfully");
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
        }

        Log.DEBUG(TAG, GENERATE_USER);
        User user = new Credentials(DEFAULT_USERNAME, DEFAULT_PASSWORD);
        Log.DEBUG(TAG, GENERATE_MODEL);
        KSPModManager model = new KSPModManager(user);
        Log.DEBUG(GENERATE_UI);
        KSPMMMainView view = new KSPMMMainView();
        Log.DEBUG(TAG, APP_LAUNCH);
        MainViewController controller = new MainViewController(model, view);
        controller.initUserInterface();
        Log.DEBUG(APP_LAUNCH_SUCCESS);
    }
}
