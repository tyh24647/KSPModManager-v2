package Utils.EncryptionUtils;

import Objects.Users.User;
import Utils.EncryptionUtils.StringEncryption.KSPMMStringEncrypter;
import Utils.Log;
import Utils.StringUtils.KSPMMStringFormatter;
import Utils.StringUtils.StringUtils;
import Utils.Validator;
import Utils.XMLUtils.XMLUtils;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Nonnull;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import static Constants.StrConstants.*;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class AESEncryption {

    private static final String TAG = "AESEncryption: ";

    protected static Cipher cipher;

    //region PROTECTED VARS
    protected User user;
    protected Key encryptionKey;
    protected Cipher currentCipher;
    //endregion

    //region CONSTRUCTORS
    public AESEncryption() {
        setUser(null);
        generateEncKey();
        generateCipher();
    }

    public AESEncryption(User user) {
        setUser(user);
        user.aesKey = generateEncKey();
        Log.DEBUG(TAG, "Generating AES cipher...");
        generateCipher();
        Log.DEBUG(TAG, "Cipher generated successfully");
    }

    public AESEncryption(String username, String password) {

    }

    public AESEncryption(User user, String secretKeyStr) {

    }

    public AESEncryption(User user, Key encryptionKey) {

    }

    public AESEncryption(String username, String password, String secretKeyStr) {

    }

    public AESEncryption(String username, String password, Key encryptionKey) {

    }

    public AESEncryption(User user, String salt, String secretKeyStr) {

    }

    public AESEncryption(User user, String salt, Key encryptionKey) {

    }

    public AESEncryption(String username, String password, String salt, String secretKeyStr) {

    }

    public AESEncryption(String username, String password, String salt, Key encryptionKey) {

    }
    //endregion

    //region ACCESSOR METHODS
    public User setUser(User user) {
        Log.DEBUG(TAG, "Attempting to assign \"user\" object to value \"" + user + "\"...");
        this.user = user;

        if (user != null) {
            Log.DEBUG(TAG, "User assigned successfully");
        }

        return user;
    }

    public User getUser() {
        return user == null ? setUser(null) : this.user;
    }
    //endregion

    //region ENCRYPTION METHODS
    public String encryptUserCredentials(User user) {
        //return user.toString();     // do this for now
        return encryptUserCredentials(user.getUsername(), user.getPassword());

        // TODO
    }

    public String encryptUserCredentials(String username, String password) {
        String encryptedCredentialsStr;
        String encName = new KSPMMStringEncrypter().encrypt(username, DEFAULT_SALT);
        String encPwd = new KSPMMStringEncrypter().encrypt(password, DEFAULT_SALT);
        user.getCredentials().setEncryptedUsername(encName);
        user.getCredentials().setEncryptedPassword(encPwd);

        if (! Validator.isValidStr(user.toString())) {
            encryptedCredentialsStr = user.toString();
        } else {
            // TODO change this
            encryptedCredentialsStr = user.getUsername().concat(" : " + user.getPassword());
        }

        return encryptedCredentialsStr;
    }

    @Nonnull
    public Key setEncryptionKey(byte[] keyBytes) {  // TODO: eventually make this private
        return this.encryptionKey = new SecretKeySpec(keyBytes, AES);
    }

    @Nonnull
    public Key getEncryptionKey() {
        return encryptionKey == null ? generateEncKey() : this.encryptionKey;
    }

    @Nonnull
    private Key generateEncKey() {
        String tmp = StringUtils.scramble(user.getUsername().concat(user.getPassword().concat(Objects.TimeStamp.currentDateTime())));
        Log.DEBUG(TAG, "Converting to Base64 encoding...");
        tmp = KSPMMStringFormatter.format(Base64.encode(DigestUtils.sha1(tmp.substring(0, 15))));
        Log.DEBUG(TAG, "Key successfully converted");
        Log.DEBUG(TAG, "The user's master encryption key is: '" + tmp + "'");
        XMLUtils.writeToXMLFile(AES_KEY_TITLE, tmp);
        Log.DEBUG(TAG, "Setting application encryption key to \"" + tmp + "\"...");
        byte[] keyBytes = tmp.getBytes();
        Log.DEBUG(TAG, "Encryption key assigned successfully");
        return setEncryptionKey(keyBytes);
    }

    @Nonnull
    private Cipher generateCipher() {
        try {
            setCurrentCipher(Cipher.getInstance(AES));
            cipher = currentCipher;
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
        }

        return this.currentCipher;
    }

    @Nonnull
    private Cipher setCurrentCipher(Cipher cipher) {
        try {
            return this.currentCipher = cipher == null ? Cipher.getInstance(AES) : cipher;
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
        }

        return this.currentCipher;
    }
    //endregion

    public Cipher getCurrentCipher() {
        return cipher == null ? this.currentCipher == null ? setCurrentCipher(null) : this.currentCipher : cipher;
    }

    /*
    @Override
    public String toString() {
        ArrayList<PrefsElement> elements = new ArrayList<>();

        if (user != null) {
            if (Validator.isValidStr(user.toString())) {
                elements.add(new PrefsElement(
                        USER_TITLE, user.toString()
                ));
            }
            if (Validator.isValidStr(encryptionKey.toString())) {
                elements.add(new PrefsElement(
                        AES_KEY_TITLE, encryptionKey.toString()
                ));
            }
            if (Validator.isValidStr(currentCipher.toString())) {
                elements.add(new PrefsElement(
                        CURRENT_CIPHER_TITLE, currentCipher.toString()
                ));
            }
        }

        StringBuilder sb;
        if (elements.size() > 0) {
            sb = new StringBuilder(elements.size());
            for (PrefsElement element : elements) {
                String tmp = element.toString().concat("\n");
                sb.append(tmp);
            }
        } else {
            sb = new StringBuilder();
            sb.append(EMPTY);
        }

        return super.toString() + sb.toString();
    }
    */
}
/*
            String text = "Hello World";
            String key = "Bar12345Bar12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b: encrypted) {
                sb.append((char)b);
            }

            // the encrypted String
            String enc = sb.toString();
            System.out.println("encrypted:" + enc);

            // now convert the string to byte array
            // for decryption
            byte[] bb = new byte[enc.length()];
            for (int i=0; i<enc.length(); i++) {
                bb[i] = (byte) enc.charAt(i);
            }

            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(bb));
            System.err.println("decrypted:" + decrypted);

        }
        */

