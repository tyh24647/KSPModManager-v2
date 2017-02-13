package Constants;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class StrConstants {
    public static final String
            DEFAULT_USERNAME = "default",
            DEFAULT_PASSWORD = "default",
            EMPTY = "",
            DEFAULT_DATA_DIRECTORY = System.getProperty("user.dir").concat("/KSPModManager/"),
            DEFAULT_USER_DATA_FILE_NAME = "user-data.xml",
            DEFAULT_APP_DATA_FILE_NAME = "appdata.xml",
            DEFAULT_USER_DATA_DIR_NAME = "UserData/",
            DEFAULT_APP_DATA_DIR_NAME = "AppData/",
            DEFAULT_USER_DATA_XML_PATH = DEFAULT_DATA_DIRECTORY.concat(DEFAULT_USER_DATA_DIR_NAME)
                    .concat(DEFAULT_USER_DATA_FILE_NAME),
            DEFAULT_APP_DATA_XML_PATH = DEFAULT_DATA_DIRECTORY.concat(DEFAULT_APP_DATA_DIR_NAME)
                    .concat(DEFAULT_APP_DATA_FILE_NAME);
    //endregion


    /*
    private HashMap<Defaults.Keys, Object> constantsMap;
    private static Defaults.Keys keys;
    */

    /**
     * Constructor
     */
    public StrConstants() {
        initConstMap();
    }

    private void initConstMap() {
        //String[] values = ((String[]) constantsMap.values().toArray());
        boolean empty = false;

        /*
        if (constantsMap == null) {
            constantsMap = new HashMap<>(Defaults.Keys.values().length);
        }

        if (constantsMap.isEmpty() || constantsMap.values().isEmpty()) {
            empty = true;
        } else {
            for (String key : values) {
                if (key != null && !key.equals("")) {
                    empty = false;
                    break;
                }
            }
        }

        if (empty) {
            setupDefaultMapVals();
        }
        */
    }

    private void setupDefaultMapVals() {
        /*
        switch (keys) {
            case DEFAULT_USERNAME:
                constantsMap.put(Defaults.Keys.DEFAULT_USERNAME, "default");
                break;
            case DEFAULT_PASSWORD:
                constantsMap.put(Defaults.Keys.DEFAULT_PASSWORD, "default");
                break;
            case CAN_EDIT:
                constantsMap.put(Defaults.Keys.CAN_EDIT, true);
                break;
            case IS_ADMIN:
                constantsMap.put(Defaults.Keys.IS_ADMIN, true);
                break;
            case REQUIRE_AUTH:
                constantsMap.put(Defaults.Keys.REQUIRE_AUTH, false);
                break;
            default:
                break;
        }
        */
    }

}
