package Objects.Users;

import Constants.Defaults;
import Utils.Validator;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class User {

    private String username, password;

    private Boolean isAdmin, requireAuthentication;

    /**
     * Default constructor
     */
    public User() {

    }

    /**
     * Constructs a user instance with the specified username and password
     *
     * @param username  The username value to be assigned
     * @param password  The password value to be assigned
     */
    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }


    public String setUsername(String username) {
        return this.username = Validator.isValidString(username) ? Defaults.USERNAME : username;
    }

    public String getUsername() {
        return Validator.isValidString(username) ? Defaults.USERNAME : username;
    }

    public String setPassword(String password) {
        return this.password = Validator.isValidString(password) ? Defaults.PASSWORD : password;
    }

    public String getPassword() {
        return Validator.isValidString(password) ? Defaults.PASSWORD : password;
    }
}
