package Objects.Users.UserAttributes;

import Objects.Users.User;

import javax.annotation.Nullable;
import javax.xml.crypto.Data;

/**
 * <p>Interface for a credentials object</p>
 * <br>
 * <u>This interface provides accessor methods for the following:</u>
 * <ul>
 *     <li>The user's username</li>
 * </ul>
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public interface Credentials {

    //region STRING METHODS
    String getUsername();
    String getPassword();
    String getEncryptedUsernameStr();
    String getEncryptedPasswordStr();
    String getEncryptedStr();
    //endregion

    //region DATA METHODS
    Data getEncryptedUsername();
    Data getEncryptedPassword();
    Data getEncrypted();
    //endregion

    //region USER METHODS
    @Nullable User getAssociatedUser();
    //endregion

    //region OVERRIDE METHODS
    String toString();
    //endregion
}
