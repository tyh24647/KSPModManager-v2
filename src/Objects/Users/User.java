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

    public User getCurrentInstance() {
        return this;
    }

    /*
    @Override
    public String toString() {
        if (prefsArr == null) {
            prefsArr = new ArrayList<>();
        }

        PrefsElement adminEl = new PrefsElement(IS_ADMIN_TITLE, isAdmin == null ? Defaults.IS_ADMIN : this.isAdmin.toString());

        boolean shouldAdd = true;
        if (!prefsArr.contains(adminEl)) {
            for (PrefsElement element : prefsArr) {
                if (element.getKey().equals(adminEl.getKey())) {
                    shouldAdd = false;
                }
            }
        }

        if (shouldAdd) {
            prefsArr.add(adminEl);
        }

        shouldAdd = true;
        PrefsElement authEl = new PrefsElement(REQUIRE_AUTH_TITLE, requireAuthentication == null ? Defaults.REQUIRE_AUTH : this.requireAuthentication.toString());

        if (!prefsArr.contains(authEl)) {
            for (PrefsElement el : prefsArr) {
                if (el.getKey().equals(authEl.getKey())) {
                    shouldAdd = false;
                }
            }
        }

        if (shouldAdd) {
            prefsArr.add(authEl);
        }

        StringBuilder sb;
        if (prefsArr.size() > 0) {
            sb = new StringBuilder(prefsArr.size());
            for (PrefsElement element : prefsArr) {
                String tmp = prefsArr.size() == 1 ? element.toString() : element.toString().concat(", ");
                sb.append(tmp);
            }
        } else {
            sb = new StringBuilder();
            sb.append(EMPTY);
        }

        return sb.toString();
    }
    */
}
