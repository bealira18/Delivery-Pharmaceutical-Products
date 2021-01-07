package lapr.project.model;

import lapr.project.data.RegisteredUserDB;

public class RegisteredUser {

    private String email;
    private final String password;
    private String role;

    public RegisteredUser(String email, String password, String role) {

        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {

        return email;
    }

    public String getRole() {

        return role;
    }

    public final void setEmail(String email) {

        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static RegisteredUser findUser(String strEmail) {

        return new RegisteredUserDB().findUser(strEmail, strEmail);
    }

    public static boolean addUser(RegisteredUser usr) {

        return new RegisteredUserDB().addUser(usr.email, usr.password, usr.role);
    }
}
