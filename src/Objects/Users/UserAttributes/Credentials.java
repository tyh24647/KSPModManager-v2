package Objects.Users.UserAttributes;

import Constants.StrConstants;
import Objects.Users.User;
import Utils.EncryptionUtils.StringEncryption.KSPMMStringEncrypter;

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
        // TODO: TEST TEST TEST TEST TEST
        setUsername(StrConstants.DEFAULT_USERNAME);
        setPassword(StrConstants.DEFAULT_PASSWORD);
        // TODO: TEST TEST TEST TEST TEST

        encryptUserData();
    }

    public Credentials(User user) {
        super();
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        encryptUserData();
    }

    public Credentials(@Nonnull String username, @Nonnull String password) {
        super.setUsername(username);
        super.setPassword(password);
        super.setCredentials(this);
        //super(username, password);
        encryptUserData();
    }

    private void encryptUserData() {
        stringEncrypter = new KSPMMStringEncrypter(this);
        aesKey = stringEncrypter.getEncryptionKey();
        encryptedUsernameData = stringEncrypter.encrypt(username, aesKey, stringEncrypter.getCurrentCipher());
        encryptedPasswordData = stringEncrypter.encrypt(password, aesKey, stringEncrypter.getCurrentCipher());
        encryptedPassword = new String(encryptedPasswordData, StandardCharsets.UTF_8);
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

    /*
    @Override
    public String toString() {
        prefsArr = new ArrayList<>();
        if (stringEncrypter != null) {
            prefsArr.add(new PrefsElement(ENCRYPTION_TITLE, stringEncrypter));
        }
        if (aesKey != null) {
            prefsArr.add(new PrefsElement(AES_KEY_TITLE, aesKey));
        }
        if (encryptedUsername != null) {
            prefsArr.add(new PrefsElement(ENCRYPTED_USERNAME_TITLE, encryptedUsername));
        }
        if (encryptedPassword != null) {
            prefsArr.add(new PrefsElement(ENCRYPTED_PASSWORD_TITLE, encryptedPassword));
        }
        if (encryptedCredentials != null) {
            prefsArr.add(new PrefsElement(ENCRYPTED_CREDENTIALS_TITLE, encryptedCredentials));
        }
        if (encryptedUsernameData != null) {
            prefsArr.add(new PrefsElement(ENCRYPTED_USERNAME_DATA_TITLE, encryptedUsernameData));
        }
        if (encryptedPasswordData != null) {
            prefsArr.add(new PrefsElement(ENCRYPTED_PASSWORD_DATA_TITLE, encryptedPasswordData));
        }
        if (encryptedCredentialsData != null) {
            prefsArr.add(new PrefsElement(ENCRYPTED_CREDENTIALS_DATA_TITLE, encryptedCredentialsData));
        }

        StringBuilder sb = new StringBuilder(prefsArr.size());
        prefsArr.forEach(sb::append);
        return super.toString() + sb.toString();
    }
    */
}

/*
 private static AESEncryption encryption;
    private Key aesKey;
    private User associatedUser;
    private String username, password, encryptedUsername, encryptedPassword, encryptedCredentials;
    private Data encryptedUsernameData, encryptedPasswordData, encryptedCredentialsData;
 */
