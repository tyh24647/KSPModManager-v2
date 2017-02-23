package Models;

import Constants.Defaults;
import Objects.Users.User;
import Utils.Log;

import java.io.File;
import java.io.FileNotFoundException;

import static Constants.StrConstants.ApplicationDefaults.DEFAULT_PASSWORD;
import static Constants.StrConstants.ApplicationDefaults.DEFAULT_USERNAME;
import static Constants.StrConstants.MODS_FOLDER_PATH;
import static Constants.StrConstants.Messages.Debug.Success.GENERATE_MODEL_SUCCESS;
import static Constants.StrConstants.Tags.MODEL_TAG;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class KSPModManager {
    private static final String TAG = MODEL_TAG;

    private User user;
    private Object[][] data;
    private KSPMMTableModel tableModel;

    /**
     * Default constructor
     */
    public KSPModManager() {
        setUser(new User(DEFAULT_USERNAME, DEFAULT_PASSWORD));
        Log.DEBUG(TAG, "Default user object generated");
        loadUserDataFromDefaultPath();
    }

    public KSPModManager(User user) {
        setUser(user);
        loadUserDataFromDefaultPath();
        Log.DEBUG(TAG, GENERATE_MODEL_SUCCESS);
    }

    private void loadUserDataFromDefaultPath() {
        File kspDir = new File(MODS_FOLDER_PATH);
        if (kspDir.exists() && kspDir.isDirectory()) {
            Log.DEBUG("HEYYO!");
        } else {
            Log.DEBUG("DANG!");
        }
    }

    public KSPModManager(String username, String password) {

    }

    public void saveData() throws FileNotFoundException {

    }

    public void setTableModel(KSPMMTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public KSPMMTableModel getTableModel() {
        return tableModel;
    }

    public void setUser(User user) {
        this.user = user == null ? new User(DEFAULT_USERNAME, DEFAULT_PASSWORD) : user;
    }

    public User getUser() {
        return user == null ? new User(DEFAULT_USERNAME, DEFAULT_PASSWORD) : user;
    }

    public void setData(Object[][] data) {
        this.data = data == null ? Defaults.EMPTY_DATA_TABLE : data;
    }


    public Object[][] getData() {
        return data == null ? Defaults.EMPTY_DATA_TABLE : data;
    }
}
