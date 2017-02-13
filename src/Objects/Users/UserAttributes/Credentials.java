package Objects.Users.UserAttributes;

import Constants.StrConstants;
import Objects.Users.User;
import Utils.EncryptionUtils.StringEncryption.KSPMMStringEncrypter;
import Utils.StringUtils.KSPMMStringFormatter;
import Utils.StringUtils.StringUtils;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;

import static Constants.StrConstants.DEFAULT_USERNAME;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class Credentials extends User implements ICredentials {
    private static KSPMMStringEncrypter stringEncrypter;
    private String encryptedUsername, encryptedPassword;
    private byte[] encryptedUsernameData, encryptedPasswordData;

    /**
     * Default constructor
     */
    public Credentials() {
        super();
        setUserLoginInfo(StrConstants.DEFAULT_USERNAME, StrConstants.DEFAULT_PASSWORD);
    }

    public Credentials(User user) {
        super();
        setUserLoginInfo(user.getUsername(), user.getPassword());
    }

    public Credentials(@Nonnull String username, @Nonnull String password) {
        setUserLoginInfo(username, password);
    }

    private void setUserLoginInfo(@Nonnull String uName, @Nonnull String pWord) {
        setUsername(uName);
        setPassword(pWord);
        setCredentials(this);
        encryptUserData();
    }

    private void encryptUserData() {
        stringEncrypter = new KSPMMStringEncrypter(this);
        aesKey = stringEncrypter.getEncryptionKey();
        encryptedUsernameData = stringEncrypter.encrypt(username, aesKey, stringEncrypter.getCurrentCipher());
        encryptedPasswordData = stringEncrypter.encrypt(password, aesKey, stringEncrypter.getCurrentCipher());
        encryptedUsername = generateEncryptedUsername(encryptedUsernameData);
        encryptedPassword = generateEncryptedPassword(encryptedPasswordData);
    }

    /**
     * <p>Generates the <code>String</code>value of a <code>Base64</code> hash value</p>
     *
     * @param encryptedUsernameData The username data to format
     * @return                      The <code>String</code> value of the decoded data
     * @see                         KSPMMStringFormatter for usage
     */
    @Nonnull
    private String generateEncryptedUsername(@Nonnull byte[] encryptedUsernameData) {
        return StringUtils.scramble(KSPMMStringFormatter.format(
                Base64.encode(new String(encryptedUsernameData, StandardCharsets.UTF_8).getBytes())
        ));
    }

    @Nonnull
    private String generateEncryptedPassword(@Nonnull byte[] encryptedPasswordData) {
        return StringUtils.scramble(KSPMMStringFormatter.format(
                Base64.encode(new String(encryptedPasswordData, StandardCharsets.UTF_8).getBytes())
        ));
    }

    private void validateUsernameAndPassword() {
        this.username = username == null ? initDefaultUsername() : username;
        this.password = password == null ? initDefaultPassword() : password;
    }

    private String initDefaultUsername() {
        return setUsername(DEFAULT_USERNAME);
    }

    private String initDefaultPassword() {
        return setPassword(StrConstants.DEFAULT_PASSWORD);
    }

    @Nonnull
    @Override
    public String setEncryptedUsername(@Nonnull String username) {
        return this.encryptedUsername = username;
    }

    @Nonnull
    @Override
    public String getEncryptedUsername() {
        return this.encryptedUsername == null ? setEncryptedUsername(stringEncrypter.encrypt(username)) : encryptedUsername;
    }

    @Override
    public String setEncryptedPassword(@Nonnull String password) {
        return this.encryptedPassword = password;
    }

    @Override
    public String getEncryptedPassword() {
        return this.encryptedPassword == null ? setEncryptedPassword(stringEncrypter.encrypt(password)) : encryptedPassword;
    }

    @Override
    public byte[] getEncryptedUsernameData() {
        return encryptedUsernameData;
    }

    @Override
    public byte[] getEncryptedPasswordData() {
        return encryptedPasswordData;
    }
}
