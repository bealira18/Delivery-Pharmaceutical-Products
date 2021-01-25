package lapr.project.auth;

import java.util.Objects;
import lapr.project.model.RegisteredUser;

public class UserSession {

    /**
     * The session's user.
     */
    private RegisteredUser user = null;

    /**
     * Creates an instance of a user session with a user given as argument.
     * Throws an exception (IllegalArgumentException) if the user given as
     * argument equals null.
     *
     * @param user the user.
     */
    public UserSession(RegisteredUser user) {

        if (user == null) {
            throw new IllegalArgumentException("Session's User cannot be null!");
        }
        this.user = user;
    }

    /**
     * Returns the session's user's email.
     *
     * @return the session's user's email if he's currently logged in. Otherwise
     * it returns null.
     */
    public String getUserEmail() {

        if (isLoggedIn()) {
            return this.user.getEmail();
        }
        return null;
    }

    /**
     * Returns the session's user's role.
     *
     * @return the session's user's role if he's currently logged in. Otherwise
     * it returns null.
     */
    public String getUserRole() {

        if (isLoggedIn()) {
            return this.user.getRole();
        }
        return null;
    }

    /**
     * Ends the user session.
     */
    public void doLogout() {

        this.user = null;
    }

    /**
     * Verifies if the user is currently logged in.
     *
     * @return true, if the user is logged in. Otherwise it returns false.
     */
    public boolean isLoggedIn() {

        return this.user != null;
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserSession other = (UserSession) obj;
        return this.user.equals(other.user);
    }
}
