package Objects.Users;

import Constants.Defaults;
import Objects.Users.UserAttributes.Credentials;
import Utils.Validator;

import java.security.Key;

import static Constants.StrConstants.DEFAULT_PASSWORD;
import static Constants.StrConstants.DEFAULT_USERNAME;


/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class User {

    //region PRIVATE VARS
    private Credentials credentials;
    private Boolean isAdmin, requireAuthentication;
    //endregion
    
    //region PROTECTED VARS
    protected String username, password;
    //endregion

    //region PUBLIC VARS
    public Key aesKey;
    //endregion

    /**
     * <p>Default constructor</p>
     */
    public User() {
        initDefaults();
    }

    /**
     * <p>Constructs a user instance with the specified username and password</p>
     *
     * @param username  The username value to be assigned
     * @param password  The password value to be assigned
     */
    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        initDefaults();
    }

    private void initDefaults() {
        //prefsArr = new ArrayList<>();
        if (!Validator.isValidStr(username)) setUsername(DEFAULT_USERNAME);
        if (!Validator.isValidStr(password)) setPassword(DEFAULT_PASSWORD);

        // TODO: Change this if needed elsewhere, if readonly is desired
        requireAuthentication = Defaults.REQUIRE_AUTH;
        // TODO TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST

        setAdminPermissions(Defaults.IS_ADMIN);
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String setUsername(String username) {
        return this.username = Validator.isValidStr(username) ? DEFAULT_USERNAME : username;
    }

    public String getUsername() {
        return Validator.isValidStr(username) ? DEFAULT_USERNAME : username;
    }

    public String setPassword(String password) {
        return this.password = Validator.isValidStr(password) ? DEFAULT_PASSWORD : password;
    }

    public String getPassword() {
        return Validator.isValidStr(password) ? DEFAULT_PASSWORD : password;
    }

    public Boolean setAdminPermissions(Boolean isAdmin) {
        requireAuthentication = false;
        return this.isAdmin = isAdmin == null ? Defaults.IS_ADMIN : isAdmin;
    }

    public Boolean isAdmin() {
        return isAdmin == null ? setAdminPermissions(null) : isAdmin;
    }

    public Boolean setRequireAuthentication(Boolean requireAuth) {
        return this.requireAuthentication = requireAuth == null ? Defaults.REQUIRE_AUTH : requireAuth;
    }

    public Boolean requiresAuthentication() {
        return requireAuthentication == null ? Defaults.REQUIRE_AUTH : this.requireAuthentication;
    }
}
