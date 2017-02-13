package Utils.XML;

import Constants.Defaults;
import Exceptions.XMLExceptions.XMLDocNotFoundException;
import Utils.Log;
import Utils.Validator;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;

import static Constants.StrConstants.DEFAULT_APP_DATA_DIR_NAME;
import static Constants.StrConstants.DEFAULT_USER_DATA_DIR_NAME;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class XMLUtils {



    public static boolean xmlDocExistsAtPath(String filePath, boolean isUserData) {
        if (! Validator.isValidString(filePath)) {
            return false;
        }

        File tmp;
        try {
            tmp = createFileAtPath(filePath);
        } catch (XMLDocNotFoundException e) {
            if (Defaults.DEBUG_MODE) {
                Log.ERROR(e.getMessage());
            }

            String dir = isUserData ? DEFAULT_USER_DATA_DIR_NAME : DEFAULT_APP_DATA_DIR_NAME;
            tmp = new File(dir);

        }

        return tmp.exists();
    }

    private static File createFileAtPath(@NonNull String filePath) throws XMLDocNotFoundException {
        File tmp = new File(filePath);

        // TODO

        return tmp;
    }


}
