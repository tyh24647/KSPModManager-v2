package Constants;

import Utils.Validator;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

/**
 * User Defaults
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public final class Defaults {

    //region CONSTANTS
    public static final Object[][] EMPTY_DATA_TABLE = new Object[0][0];

    public static final String
            DEFAULT_USERNAME = "default",
            PASSWORD = "default";

    public static final boolean
            IS_ADMIN = true,
            DEBUG_MODE = false;
    //endregion

    public HashMap<PrefsKey, Object> defaultSettings;

    /**
     * <p>Constructs a singleton instance of an object specifying the default values for user preferences</p>
     */
    public Defaults() {
        setDefaultSettings(this.defaultSettings == null ? initDefaultSettings() : this.defaultSettings);
    }

    private HashMap<String, Object> initDefaultSettings() {
        HashMap<String, Object> tmp = new HashMap<>();

        for (String keyName :)


        //defaultSettings.put(Prefs.DEFAULT_USERNAME.getKeyName(), );
        return tmp;
    }

    public Object getValueForKey(String key) {
        if ()
    }

    private void setDefaultSettings(@Nonnull HashMap<String, Object> defaultSettings) {
        this.defaultSettings = defaultSettings;
    }

    public List<String> getDefaultSettingsKeys() {

    }

    private static String getUserDirectoryFilePathStr() {
        String defaultDirPath = System.getProperty("user.dir");
        String rVal = StrConstants.EMPTY;
        String fullPath;
        if (defaultDirPath != null && !defaultDirPath.isEmpty()) {
            StringBuilder sb = new StringBuilder(defaultDirPath);
            sb.append("/KSPModManager/AppData");
            fullPath = sb.toString();
            File tmp = new File(fullPath);
            if (!tmp.exists()) {
                if (tmp.mkdirs()) {
                    try {
                        rVal = tmp.getCanonicalPath();
                    } catch (Exception e) {
                        rVal = tmp.getAbsolutePath();

                        validationLoop:
                        if (! Validator.isValidString(rVal)) {
                            rVal = tmp.getPath();
                            if (! Validator.isValidString(rVal)) {
                                rVal = tmp.getParent();
                                if (! Validator.isValidString(rVal)) {
                                    rVal = tmp.getName();
                                }

                                if (Validator.isValidString(rVal)) {
                                    rVal = StrConstants.EMPTY;
                                    break validationLoop;
                                }
                            }
                        } else {
                            break validationLoop;
                        }
                    }
                }
            }
        }

        return rVal;
    }
}

