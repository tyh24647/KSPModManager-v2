import Constants.StrConstants;
import Models.KSPModManager;
import Objects.Users.User;
import Objects.Users.UserAttributes.Credentials;
import Utils.Log;

import javax.swing.*;

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
        Log.DEBUG("User generated successfully");
        Log.DEBUG("Initializing model...");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
        }
        Log.DEBUG("Model successfully generated");
        Log.DEBUG("Adding user to model...");
        KSPModManager model = new KSPModManager(user);
        Log.DEBUG("User added successfully");
        //model.setUser(user);


        /*
        TODO: Add more functionality here
         */
        Log.DEBUG("Executing program...");
        executeProgram();
        Log.DEBUG("Program launched");
    }

    /**
     * <p>Starts the program execution</p>
     */
    private static void executeProgram() {
        SwingUtilities.invokeLater(() -> {
            /*
            Log.DEBUG("Generating user interface...");
            view = new KSPMMMainView();
            Log.DEBUG("UI generated successfully");
            */
        });
    }

}
