import Constants.StrConstants;
import Controllers.MainViewController;
import Models.KSPModManager;
import Objects.Users.User;
import Objects.Users.UserAttributes.Credentials;
import Utils.Log;
import UserInterface.KSPMMMainView;

import javax.swing.*;

import static Constants.StrConstants.CONSOLE_COLOR_RESET;
import static Constants.StrConstants.CONSOLE_YELLOW;

/**
 * The main driver for the application
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class KSPMMDriver {

    /**
     * <p>The main method for running the program</p>
     * @param args
     */
    public static void main(String[] args) {
        Log.DEBUG("Generating user object...");
        User user = new Credentials(StrConstants.DEFAULT_USERNAME, StrConstants.DEFAULT_PASSWORD);
        Log.DEBUG(CONSOLE_YELLOW + "User generated successfully");
        Log.DEBUG(CONSOLE_COLOR_RESET + "Creating model for user...");
        KSPModManager model = new KSPModManager(user);
        Log.DEBUG("Model created successfully");
        Log.DEBUG("Generating UI mainframe");
        KSPMMMainView view = new KSPMMMainView();
        Log.DEBUG("UI generated successfully");
        Log.DEBUG("Executing program...");
        executeProgram(model, view);
        Log.DEBUG("Program launched");
    }

    /**
     * <p>Starts the program execution</p>
     */
    private static void executeProgram(KSPModManager model, KSPMMMainView gui) {
        SwingUtilities.invokeLater(() -> {
            MainViewController controller = new MainViewController(model, gui);
            controller.initUserInterface();
        });
    }

}
