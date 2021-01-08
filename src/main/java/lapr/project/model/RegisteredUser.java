package lapr.project.model;

import java.util.Objects;
import lapr.project.data.RegisteredUserDB;

public class RegisteredUser {

    private final String email;
    private final String password;
    private final String role;

    public RegisteredUser(String email, String password, String role) {

        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public RegisteredUser(RegisteredUser user)
    {
        this.email = user.email;
        this.password = user.password;
        this.role = user.role;
    }

    public String getEmail() {

        return email;
    }

    public String getRole() {

        return role;
    }

    public static RegisteredUser findUser(String strEmail, String strPassword) {

        return new RegisteredUserDB().findUser(strEmail, strPassword);
    }

    public static boolean addUser(RegisteredUser usr) {

        return new RegisteredUserDB().addUser(usr.email, usr.password, usr.role);
    }
    
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.email);
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
        final RegisteredUser other = (RegisteredUser) obj;

        return this.email.equals(other.email);
    }
}
