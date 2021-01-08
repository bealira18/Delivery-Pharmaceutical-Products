package lapr.project.auth;

import lapr.project.model.RegisteredUser;

public class AuthFacade {

    public AuthFacade() {
    }
    

    /**
     * Holds the information about the user that is currently logged in.
     */
    private UserSession session = null;

    /**
     * Performs the login process to allow the user to access the Platform.
     *
     * @param strEmail the user's email.
     * @param strPwd the user's password.
     * @return valid UserSession object if the process is successfull. Otherwise
     * it returns null.
     */
    public UserSession doLogin(String strEmail, String strPwd) {

        RegisteredUser utlz = RegisteredUser.findUser(strEmail, strPwd);

        if (utlz != null) {
            this.session = new UserSession(utlz);
        }
        return getCurrentSession();
    }

    /**
     * Returns the current session.
     *
     * @return current user session. Otherwise, it returns null if no user is
     * logged in.
     */
    public UserSession getCurrentSession() {

        return this.session;
    }

    /**
     * Logs out the user, if a user is currently logged in.
     */
    public void doLogout() {

        if (this.session != null) {
            this.session.doLogout();
        }
        this.session = null;
    }
}
