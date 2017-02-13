package Utils.EncryptionUtils.StringEncryption;

import Objects.Users.User;
import Utils.EncryptionUtils.AESEncryption;
import Utils.Log;
import Utils.StringUtils.StringUtils;
import Utils.Validator;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Nonnull;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

import static Constants.StrConstants.DEFAULT_SALT;
import static Constants.StrConstants.EMPTY;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class KSPMMStringEncrypter extends AESEncryption {

    public KSPMMStringEncrypter() {

    }

    public KSPMMStringEncrypter(User user) {
        super(user);
    }

    @Nonnull
    public String encrypt(String str) {
        String tmp = EMPTY;
        if (!Validator.isValidStr(str)) {
            return tmp;
        }

        return encrypt(str, DEFAULT_SALT);
    }

    @Nonnull
    public String encrypt(String str, String salt) {
        String tmp = EMPTY;
        if (!Validator.isValidStr(str) || !Validator.isValidStr(salt)) {
            return tmp;
        }

        byte[] encrypted;
        try {
            SecureRandom rand = new SecureRandom(salt.getBytes());
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] ivBytes = new byte[16];
            StringBuilder sb = new StringBuilder(16);
            if (salt.length() > 16) {
                for (int i = 0; i < 16; i++) sb.append(salt.charAt(i));
            }
            rand.nextBytes(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.ENCRYPT_MODE, encryptionKey, iv);
            tmp = StringUtils.scramble(str.concat(salt));
            encrypted = cipher.doFinal(tmp.getBytes());
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
            encrypted = new byte[0];
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : encrypted) sb.append((char) b);
        return sb.toString();
    }

    public byte[] encrypt(String str, Key secretKey) {
        if (!Validator.isValidStr(str) || secretKey == null) {
            return null;
        }

        byte[] encrypted;
        try {
            currentCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encrypted = currentCipher.doFinal(str.getBytes());
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
            encrypted = null;
        }

        return encrypted;
    }

    public byte[] encrypt(String str, Key secretKey, Cipher cipher) {
        if (! Validator.isValidStr(str) || secretKey == null || cipher == null) {
            return null;
        }

        byte[] encrypted = null;
        try {
            byte[] keyBytes = Base64.encode(DigestUtils.sha1(secretKey.toString())).substring(0, 16).getBytes();
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, key);
            encrypted = c.doFinal(str.getBytes());
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
        }

        return encrypted;
    }

    public String encrypt(String str, String salt, Key secretKey) {
        String tmp = EMPTY;
        if (!Validator.isValidStr(str) || !Validator.isValidStr(salt) || secretKey == null) {
            return tmp;
        }

        byte[] encrypted;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            tmp = StringUtils.scramble(str.concat(salt));
            encrypted = cipher.doFinal(tmp.getBytes());
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
            encrypted = new byte[0];
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : encrypted) sb.append((char) b);
        return sb.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
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