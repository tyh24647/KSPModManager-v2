package Constants;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class StrConstants {
    public static final String
            DEFAULT_USERNAME = "default",
            DEFAULT_PASSWORD = "default",
            AES = "AES",
            AES_KEY_TITLE = "AESKey",
            ENCRYPTION_TITLE = "Encryption",
            USER_TITLE = "User",
            IS_ADMIN_TITLE = "isAdmin",
            REQUIRE_AUTH_TITLE = "requireAuthentication",
            USERNAME_TITLE = "username",
            PASSWORD_TITLE = "password",
            ASSOCIATED_USR_TITLE = "AssociatedUser",
            ENCRYPTED_USERNAME_TITLE = "encryptedUsername",
            ENCRYPTED_PASSWORD_TITLE = "encryptedPassword",
            ENCRYPTED_CREDENTIALS_TITLE = "EncryptedCredentials",
            ENCRYPTED_USERNAME_DATA_TITLE = "encryptedUsernameData",
            ENCRYPTED_PASSWORD_DATA_TITLE = "encryptedPasswordData",
            ENCRYPTED_CREDENTIALS_DATA_TITLE = "encryptedCredentialsData",
            CURRENT_CIPHER_TITLE = "currentCipher",

    // TODO put these in separate tags class
            DEBUG_TAG = "DEBUG: ",
            ERROR_TAG = "ERROR: ",
            XML_WRITER_TAG = "XMLWRITER: ",
            XML_READER_TAG = "XMLREADER: ",
    // TODO put these in separate tags class

            EMPTY = "",
            SPACE = " ",
            BACKSLASH = "\"",
            FORMATTED_SPACE = "_",
            DEFAULT_DATA_DIRECTORY = System.getProperty("user.dir").concat("/KSPModManager/"),
            DEFAULT_USER_DATA_FILE_NAME = "user-data.xml",
            DEFAULT_APP_DATA_FILE_NAME = "appdata.xml",
            DEFAULT_USER_DATA_DIR_NAME = "UserData/",
            DEFAULT_APP_DATA_DIR_NAME = "AppData/",
            DEFAULT_USER_DATA_XML_PATH = DEFAULT_DATA_DIRECTORY.concat(DEFAULT_USER_DATA_DIR_NAME)
                    .concat(DEFAULT_USER_DATA_FILE_NAME),
            DEFAULT_APP_DATA_XML_PATH = DEFAULT_DATA_DIRECTORY.concat(DEFAULT_APP_DATA_DIR_NAME)
                    .concat(DEFAULT_APP_DATA_FILE_NAME),
            DEFAULT_SALT = "D3F4UlT_S4LT";
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
