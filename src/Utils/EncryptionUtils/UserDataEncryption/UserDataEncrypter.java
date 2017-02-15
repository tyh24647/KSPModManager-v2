package Utils.EncryptionUtils.UserDataEncryption;

import Utils.EncryptionUtils.AESEncryption;

import javax.annotation.Nonnull;

import static Constants.StrConstants.Characters.EMPTY;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class UserDataEncrypter extends AESEncryption {
    private String encUsername, encPassword, encCredentials;

    public UserDataEncrypter() {
        super();
        initDefaults();
    }

    public UserDataEncrypter(@Nonnull String username, @Nonnull String password) {
        //byte[] eU = KSPMMStringEncrypter.encrypt(username, StringUtils);
    }

    private void initDefaults() {

    }

    public String encryptUserDataStr(String uDatStr) {
        return EMPTY;
    }
}
