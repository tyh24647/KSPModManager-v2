package Utils.XMLUtils;

import Exceptions.XMLExceptions.XMLDocNotFoundException;
import Utils.Log;
import Utils.Validator;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;

import static Constants.Defaults.DEBUG;
import static Constants.StrConstants.Names.Directories.DEFAULT_APP_DATA_DIR_NAME;
import static Constants.StrConstants.Names.Directories.DEFAULT_USER_DATA_DIR_NAME;
import static Constants.StrConstants.Tags.XML_WRITER_TAG;
import static Constants.StrConstants.generateTagForName;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class XMLUtils {
    private static final String TAG = generateTagForName(XMLUtils.class.getSimpleName());

    public static boolean xmlDocExistsAtPath(String filePath, boolean isUserData) {
        if (! Validator.isValidStr(filePath)) {
            return false;
        }

        File tmp;
        try {
            tmp = createFileAtPath(filePath);
        } catch (XMLDocNotFoundException e) {
            if (DEBUG) {
                Log.ERROR(e.getMessage());
            }

            String dir = isUserData ? DEFAULT_USER_DATA_DIR_NAME : DEFAULT_APP_DATA_DIR_NAME;
            tmp = new File(dir);

        }

        return tmp.exists();
    }

    public static boolean writeToXMLFile(String title, Object value) {
        boolean success;

        Log.DEBUG(XML_WRITER_TAG, "Writing '" + value.toString() + "' to XML document with element name '" + title + "'");

        if (DEBUG) {
            success = false;
        } else {
            success = true;
        }

        /*
        TODO
         */

        if (!success) {
            Log.ERROR("Unable to write to document -- File not found");
            Log.DEBUG(TAG, "Skipping procedure");
        }

        return success;
    }

    private static File createFileAtPath(@NonNull String filePath) throws XMLDocNotFoundException {
        File tmp = new File(filePath);

        // TODO

        return tmp;
    }



}
