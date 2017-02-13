package Models;

import Constants.Defaults;
import Constants.StrConstants;
import Objects.Users.User;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class KSPModManager {
    private User user;
    private Object[][] data;

    /**
     * Default constructor
     */
    public KSPModManager() {

    }

    public KSPModManager(User user) {
        setUser(user);
    }

    public KSPModManager(String username, String password) {

    }

    public void setUser(User user) {
        this.user = user == null ? new User(StrConstants.DEFAULT_USERNAME, StrConstants.DEFAULT_PASSWORD) : user;
    }

    public User getUser() {
        return user == null ? new User(StrConstants.DEFAULT_USERNAME, StrConstants.DEFAULT_PASSWORD) : user;
    }

    public void setData(Object[][] data) {
        this.data = data == null ? Defaults.EMPTY_DATA_TABLE : data;
    }


    public Object[][] getData() {
        return data == null ? Defaults.EMPTY_DATA_TABLE : data;
    }
}
