package Objects.Users.UserAttributes;

import javax.annotation.Nonnull;

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
public interface ICredentials {

    //region STRING METHODS
    String getEncryptedUsername();
    String getEncryptedPassword();
    String setEncryptedUsername(@Nonnull String username);
    String setEncryptedPassword(@Nonnull String password);
    //endregion

    //region DATA METHODS
    byte[] getEncryptedUsernameData();
    byte[] getEncryptedPasswordData();
    //endregion
}
