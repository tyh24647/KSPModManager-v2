package Constants;

import Utils.Validator;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * User Defaults
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public final class Defaults {

    //region CONSTANTS
    public static final Object[][] EMPTY_DATA_TABLE = new Object[0][0];

    /*
    public static final String
            DEFAULT_USERNAME = "default",
            DEFAULT_PASSWORD = "default";
    */

    public static final boolean
            IS_ADMIN = true,
            REQUIRE_AUTH = false,
            DEBUG = true;

    //endregion



    //public HashMap<PrefsKey, Object> defaultSettings;

    /**
     * <p>Constructs a singleton instance of an object specifying the default values for user preferences</p>
     */
    public Defaults() {
        /*
        setDefaultSettings(this.defaultSettings == null ? initDefaultSettings() : this.defaultSettings);
        */
    }

    private HashMap<String, Object> initDefaultSettings() {
        HashMap<String, Object> tmp = new HashMap<>();

        //for (String keyName :)


        //defaultSettings.put(Prefs.DEFAULT_USERNAME.getKeyName(), );
        return tmp;
    }

    public Object getValueForKey(String key) {
        return null;
    }

    private void setDefaultSettings(@Nonnull HashMap<String, Object> defaultSettings) {
        //this.defaultSettings = defaultSettings;
    }

    public List<String> getDefaultSettingsKeys() {
        return null;
    }

    private static String getUserDirectoryFilePathStr() {
        String defaultDirPath = System.getProperty("user.dir");
        String rVal = EMPTY;
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
                        if (! Validator.isValidStr(rVal)) {
                            rVal = tmp.getPath();
                            if (! Validator.isValidStr(rVal)) {
                                rVal = tmp.getParent();
                                if (! Validator.isValidStr(rVal)) {
                                    rVal = tmp.getName();
                                }

                                if (Validator.isValidStr(rVal)) {
                                    rVal = EMPTY;
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

