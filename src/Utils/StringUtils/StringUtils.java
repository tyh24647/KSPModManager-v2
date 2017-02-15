package Utils.StringUtils;

import Utils.Log;
import Utils.Validator;

import java.util.Random;

import static Constants.StrConstants.Characters.EMPTY;
import static Constants.StrConstants.Characters.SPACE;
import static Constants.StrConstants.generateTagForName;

/**
 * <p>Provides String-specific utilities</p>
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class StringUtils {
    private static final String TAG = generateTagForName(StringUtils.class.getSimpleName());

    /**
     * <p>Scrambles the characters in a given {@link String}</p>
     * <p>
     *     This method is used for generating salt values that are randomized, and
     *     this allows for much more secured encryption.
     * </p>
     *
     * @param str   The {@link String} to scramble.
     * @return      The scrambled {@link String}.
     * @see         Utils.EncryptionUtils.AESEncryption for usage
     */
    public static String scramble(String str) {
        if (!Validator.isValidStr(str)) {
            return EMPTY;
        } else if (str.contains(SPACE)) {
            str = KSPMMStringFormatter.format(str);
        }

        Random r = str.toCharArray().length != 0 ? new Random(generateRandomLong(str.toCharArray().length)) : new Random();
        Log.DEBUG(TAG, "Generating new random '" + r.toString() + "' for scramble");
        String tmp;

        char[] strChars = str.toCharArray();
        for (int i = 0; i < strChars.length - 1; i++) {
            int j = r.nextInt(strChars.length - 1);
            char c = strChars[i];
            strChars[j] = c;
        }

        tmp = new String(strChars);
        Log.DEBUG("StringUtils: ", "String successfully Scrambled: \"" + str + "\" --> \"" + tmp + "\"");
        return tmp;
    }

    /**
     * <p>
     *     Generates a random long value using the length of the given string to scramble. This
     *     allows for greater security, through the use of randomly scrambled salt to use in encryption.
     * </p>
     *
     * @param strlen    The length of the string to scramble
     * @return          The generated long value
     * @see             Utils.EncryptionUtils.AESEncryption for usage
     */
    private static Long generateRandomLong(int strlen) {
        Random r = new Random(128),
                x = new Random(r.nextLong() + strlen),
                z = new Random(r.nextLong() * (((r.nextLong() + strlen) / (strlen / 2))
                                / ((r.nextLong() + strlen) == 0 ? strlen : r.nextLong() + strlen)
                                / strlen) + (strlen * (strlen + x.nextLong() / 2)));
        return new Random(z.nextLong()).nextLong();
    }


}
