package lapr.project.model;

import java.util.Objects;

public class RegisteredUser {

    private final String email;
    private final String password;
    private final String role;

    /**
     * Initializes the RegisteredUser object
     * @param email user email
     * @param password user password
     * @param role user role in the system
     */
    public RegisteredUser(String email, String password, String role) {

        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Creates a RegisteredUser already existing
     * @param user RegistedUser
     */
    public RegisteredUser(RegisteredUser user)
    {
        this.email = user.email;
        this.password = user.password;
        this.role = user.role;
    }

    /**
     * Gets the user email
     * @return user email
     */
    public String getEmail() {

        return email;
    }

    /**
     * Gets the user role
     * @return
     */
    public String getRole() {

        return role;
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.email);
        return hash;
    }

    /**
     * Compares the equality of the current object with the object of same type
     * @param obj object to compare with
     * @return true if equals, false if not
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final RegisteredUser other = (RegisteredUser) obj;

        return this.email.equals(other.email);
    }
}
