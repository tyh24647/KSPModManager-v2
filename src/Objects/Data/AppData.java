package Objects.Data;

import Objects.Users.User;

import javax.annotation.Nonnull;

import static Constants.StrConstants.ApplicationDefaults.DEFAULT_PASSWORD;
import static Constants.StrConstants.ApplicationDefaults.DEFAULT_USERNAME;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class AppData {
    private User currentUser;


    public AppData() {
        this.currentUser = null;

        // TODO
    }


    public User setCurrentUser(@Nonnull User currentUser) {
        return this.currentUser = currentUser;
    }

    public @Nonnull User getCurrentUser() {
        return currentUser == null ? setCurrentUser(new User(DEFAULT_USERNAME, DEFAULT_PASSWORD)) : currentUser;
    }
}
