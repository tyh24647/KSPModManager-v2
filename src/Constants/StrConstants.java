package Constants;

import Models.KSPModManager;
import Objects.Users.UserAttributes.Credentials;
import Tasks.AsyncTask;
import UserInterface.KSPMMMainView;
import Utils.EncryptionUtils.AESEncryption;
import Utils.OSUtils.MacOSX.MacOSXUtils;
import Utils.OSUtils.OSUtils;
import Utils.Validator;

import java.io.File;

import static Constants.BoolConstants.*;
import static Constants.StrConstants.Console.Colors.*;
import static Constants.StrConstants.Tags.DEBUG_TAG;
import static Constants.StrConstants.Tags.Symbols.TAG_SYMBOL;

/**
 * Constant strings to be shared throughout the application
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public final class StrConstants {
    public static final String USER_OS_NAME = System.getProperty("os.name");
    public static final String ENTER_FULLSCREEN = "Enter Fullscreen";
    public static final String EXIT_FULLSCREEN = "Exit Fullscreen";
    public static final String APP_DATA_PATH = determineInstallDir();

    public static final String MODS_FOLDER_PATH = APP_DATA_PATH.concat(
            "/Steam/steamapps/common/Kerbal Space Program/GameData/Squad"
    );

    public static final class SystemPreferences {
        public static final String STEAM_MODS_DIR_PATH = "/Steam/steamapps/" +
                "common/Kerbal Space Program/GameData/Squad/";

        public static final class MacOSX {
            public static final String ROOT_DIR = System.getProperty("user.home");
            public static final String APP_DATA_PATH = ROOT_DIR.concat("/Library/Application Support");
            public static final String FULLSCREEN_UTILS = "com.apple.eawt.FullScreenUtilities";
            public static final String USE_SCREEN_MENU_BAR = "apple.laf.useScreenMenuBar";
            public static final String APP_ABOUT_NAME = "com.apple.mrj.application.apple.menu.about.name";
            //public static final String DEFAULT_APP_ABOUT_NAME = "WikiTeX";
            public static final String ABOUT_NAME_VALUE = "About KSPModManager";
        }

        public static final class Windows {
            public static final String APP_DATA_PATH = System.getenv("APPDATA");
            public static final String WIN_MOD_DIR_PATH_STEAM = System.getenv("ProgramFiles(x86)").concat(
                    "Steam/steamapps/"
            );
        }

        public static final class Linux {
            public static final String APP_DATA_PATH = getLinuxInstallPath();
        }
    }

    public static final class Messages {
        public static final class Debug {
            public static final class InitializationMsgs {
                public static final String GENERATE_USER = "Generating user object...";
                public static final String GENERATE_MODEL = "Creating model for user...";
                public static final String GENERATE_UI = "Generating UI...";
                public static final String GENERATE_TABLE_MODEL = "Generating table model...";
                public static final String GENERATE_MENU_ITEM = "Generating menu item ";
                public static final String GENERATE_MENU = "Generating menu ";
            }

            public static final class Success {
                public static final String GENERATE_USER_SUCCESS = CONSOLE_YELLOW + "User generated successfully" + CONSOLE_COLOR_RESET;
                public static final String GENERATE_MODEL_SUCCESS = CONSOLE_YELLOW + "Model created successfully" + CONSOLE_COLOR_RESET;
                public static final String GENERATE_UI_SUCCESS = CONSOLE_YELLOW + "UI generated successfully" + CONSOLE_COLOR_RESET;
                public static final String GENERATE_TABLE_MODEL_SUCCESS = CONSOLE_YELLOW + "Table model generated successfully" + CONSOLE_COLOR_RESET;
                public static final String GENERATE_MENU_ITEM_SUCCESS = CONSOLE_YELLOW + "Menu item generated successfully";
                public static final String GENERATE_MENU_SUCCESS = CONSOLE_YELLOW + "Menu generated successfully";

                public static final String L_AND_F_CONFIG_SUCCESS = CONSOLE_YELLOW + "Look and feel successfully configured" + CONSOLE_COLOR_RESET;

                public static final String APP_LAUNCH_SUCCESS = CONSOLE_GREEN + "Program launched successfully" + CONSOLE_COLOR_RESET;
                public static final String APP_TERMINATED = CONSOLE_GREEN + "Application terminated";
            }

            public static final class Actions {
                public static final String APP_LAUNCH = "Launching application...";
                public static final String CONFIG_L_AND_F = "Configuring look and feel for operating system: \"" + USER_OS_NAME + "\"...";
                public static final String APP_EXIT = "Exiting application...";
            }
        }

        public static final class Error {

        }

        public static final class JOptionPane {
            public static final String JOPTIONPANE_ABOUT = "about";
            public static final String JOPTIONPANE_PREFS =  "prefs";
            public static final String JOPTIONPANE_QUIT = "quit";
        }
    }

    public static final class ApplicationDefaults {
        public static final String APP_TITLE = "KSP Mods Manager";
        public static final String DEFAULT_USERNAME = "default";
        public static final String DEFAULT_PASSWORD = "12345";
    }

    public static final class Characters {
        public static final String EMPTY = "";
        public static final String SPACE = " ";
        public static final String BACKSLASH = "\"";
        public static final String FORMATTED_SPACE = "_";
    }

    public static final class LabelText {
        public static final String BOTTOM_LABEL_TXT = "© Tyler Hostager; 2017 - All Rights Reserved";
        public static final String TITLE_LABEL_TXT = "KERBAL SPACE PROGRAM - MODS MANAGER";
    }

    public static final class Tags {
        public static final class Symbols {
            public static final String TAG_SYMBOL = ": ";
        }

        public static final String DEBUG_TAG = generateTagForName("DEBUG");
        public static final String ERROR_TAG = generateTagForName("ERROR");
        public static final String SYSTEM_TAG = generateTagForName(System.class.getSimpleName(), ConsoleColor.BLUE);
        public static final String SYSTEM_PROPERTIES_TAG = generateTagForName("SYSTEM_PROPERTIES", ConsoleColor.MAGENTA);
        public static final String MODEL_TAG = generateTagForName(KSPModManager.class.getSimpleName());
        public static final String MAIN_VIEW_TAG = generateTagForName(KSPMMMainView.class.getSimpleName());
        public static final String XML_WRITER_TAG = generateTagForName("XMLWriter");
        public static final String XML_READER_TAG = generateTagForName("XMLReader");
        public static final String OS_UTILS_TAG = generateTagForName(OSUtils.class.getSimpleName());
        public static final String MAC_OSX_UTILS_TAG = generateTagForName(MacOSXUtils.class.getSimpleName());
        public static final String CREDENTIALS_TAG = generateTagForName(Credentials.class.getSimpleName());
    }

    public static final class Encryption {
        public static final String AES = "AES";
        public static final String DEFAULT_SALT = "D3F4UlT_S4LT";
        public static final String ENCRYPTED_USERNAME_TITLE = "encryptedUsername";
        public static final String ENCRYPTED_PASSWORD_TITLE = "encryptedPassword";
        public static final String ENCRYPTED_CREDENTIALS_TITLE = "EncryptedCredentials";
        public static final String ENCRYPTED_USERNAME_DATA_TITLE = "encryptedUsernameData";
        public static final String ENCRYPTED_PASSWORD_DATA_TITLE = "encryptedPasswordData";
        public static final String ENCRYPTED_CREDENTIALS_DATA_TITLE = "encryptedCredentialsData";
        public static final String CURRENT_CIPHER_TITLE = "currentCipher";
    }

    public static final class Names {
        public static final String DEFAULT_USER_DATA_FILE_NAME = "user-data.xml";
        public static final String DEFAULT_APP_DATA_FILE_NAME = "appdata.xml";

        public static final class Directories {
            public static final String DEFAULT_DATA_DIRECTORY = System.getProperty("user.dir").concat("/KSPModManager/");
            public static final String DEFAULT_USER_DATA_DIR_NAME = "UserData/";
            public static final String DEFAULT_APP_DATA_DIR_NAME = "AppData/";

            public static final class Paths {
                public static final String DEFAULT_USER_DATA_XML_PATH = DEFAULT_DATA_DIRECTORY.concat(DEFAULT_USER_DATA_DIR_NAME.concat(DEFAULT_USER_DATA_FILE_NAME));
                public static final String DEFAULT_APP_DATA_XML_PATH = DEFAULT_DATA_DIRECTORY.concat(DEFAULT_APP_DATA_DIR_NAME).concat(DEFAULT_APP_DATA_FILE_NAME);
            }
        }
    }

    public static final class Titles {
        public static final String USER_TITLE = "User";
        public static final String IS_ADMIN_TITLE = "isAdmin";
        public static final String REQUIRE_AUTH_TITLE = "requireAuthentication";
        public static final String USERNAME_TITLE = "username";
        public static final String PASSWORD_TITLE = "password";
        public static final String ASSOCIATED_USR_TITLE = "AssociatedUser";

        public static final class Encryption {
            public static final String ENCRYPTION_TITLE = "Encryption";
            public static final String AES_KEY_TITLE = "AESKey";
        }

        public static final class Menus {
            public static final String FILE_MENU_TITLE = "File";
            public static final String EDIT_MENU_TITLE = "Edit";
            public static final String VIEW_MENU_TITLE = "View";
            public static final String TOOLS_MENU_TITLE = "Tools";
            public static final String WINDOW_MENU_TITLE = "Window";
            public static final String DEBUG_MENU_TITLE = "Debug";
            public static final String HELP_MENU_TITLE = "Help";
        }

        public static final class Items {
            public static final String OPEN_ITEM_TITLE = "Open Mods Folder...";
            public static final String IMPORT_ITEM_TITLE = "Import Mod...";
            public static final String EXIT_ITEM_TITLE = "Quit KSP Mod Manager";
            public static final String UNDO_ITEM_TITLE = "Undo";
            public static final String REDO_ITEM_TITLE = "Redo";
            public static final String PREFS_ITEM_TITLE = "Preferences";
            public static final String DISABLE_SELECTED_ITEM = "Disable Selected Item";
            public static final String ENABLE_SELECTED_ITEM = "Enable Selected Item";
            public static final String VIEW_LOGS_ITEM_TITLE = "Show Log files...";
            public static final String SHOW_COMPONENTS_TITLE = "Show Components...";
        }
    }

    public static final class Console {
        public static final class Colors {
            public static final String CONSOLE_COLOR_FLAG = "\033[";
            public static final String CONSOLE_COLOR_RESET = CONSOLE_COLOR_FLAG + "0m";
            public static final String CONSOLE_RED = CONSOLE_COLOR_FLAG + "31m";
            public static final String CONSOLE_GREEN = CONSOLE_COLOR_FLAG + "32m";
            public static final String CONSOLE_YELLOW = CONSOLE_COLOR_FLAG + "33m";
            public static final String CONSOLE_BLUE = CONSOLE_COLOR_FLAG + "34m";
            public static final String CONSOLE_MAGENTA = CONSOLE_COLOR_FLAG + "35m";
            public static final String CONSOLE_CYAN = CONSOLE_COLOR_FLAG + "36m";
            public static final String CONSOLE_WHITE = CONSOLE_COLOR_FLAG + "37m";

            enum ConsoleColor {
                RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE, DEFAULT
            }
        }
    }

    public static String generateTagForName(String className) {
        return generateTagForName(className, null);
    }

    public static String generateTagForName(String className, ConsoleColor color) {
        String tag;

        if (!Validator.isValidStr(className)) {
            generateTagForName(DEBUG_TAG);
        }

        if (color == null) {
            color = ConsoleColor.DEFAULT;
        }

        if (className.equals(AsyncTask.class.getSimpleName())) {
            color = ConsoleColor.MAGENTA;
        } else if (className.equals(AESEncryption.class.getSimpleName())) {
            color = ConsoleColor.CYAN;
        } else if (className.equals("KSPMM")) {
            color = ConsoleColor.YELLOW;
        }

        switch (color) {
            case RED:
                tag = "\033[31m";
                break;
            case GREEN:
                tag = "\033[32m";
                break;
            case YELLOW:
                tag = "\033[33m";
                break;
            case BLUE:
                tag = "\033[34m";
                break;
            case MAGENTA:
                tag = "\033[35m";
                break;
            case CYAN:
                tag = "\033[36m";
                break;
            case WHITE:
                tag = "\033[37m";
                break;
            case DEFAULT:
                tag = "\033[0m";
                break;
            default:
                tag = "\033[0m";
                break;
        }

        return tag.concat(className.concat(TAG_SYMBOL));
    }

    private static String determineInstallDir() {
        String path = "";

        if (IS_USING_STEAM) {
            if (IS_MAC) {
                path = SystemPreferences.MacOSX.APP_DATA_PATH;
            } else if (IS_WINDOWS) {
                path = SystemPreferences.Windows.APP_DATA_PATH;
            } else if (IS_LINUX) {
                path = SystemPreferences.Linux.APP_DATA_PATH;
            } else {
                path = null;    // for now
            }
        } else {
            if (IS_MAC) {
                path = "~/Applications/".concat(SystemPreferences.STEAM_MODS_DIR_PATH);
                if (!new File(path).exists()) {
                    path = "/Applications/".concat(SystemPreferences.STEAM_MODS_DIR_PATH);
                    if (!new File(path).exists()) {
                        path = null;    // TODO: let user choose install dir
                    }
                }
            } else if (IS_WINDOWS) {
                // TODO figure this out
            }
        }


        return path;
    }


    private static String getLinuxInstallPath() {
        if (!IS_LINUX) {
            return null;
        }

        File tmpDir;
        tmpDir = new File("~/.steam".concat(SystemPreferences.STEAM_MODS_DIR_PATH));
        if (!tmpDir.exists()) {
            tmpDir = new File("~/.local/share/".concat(SystemPreferences.STEAM_MODS_DIR_PATH));
            if (!tmpDir.exists()) {
                tmpDir = new File("/home/${USER}/".concat(SystemPreferences.STEAM_MODS_DIR_PATH));
                if (!tmpDir.exists()) {
                    return null;
                }
            }
        }

        return tmpDir.getPath();
    }
}


