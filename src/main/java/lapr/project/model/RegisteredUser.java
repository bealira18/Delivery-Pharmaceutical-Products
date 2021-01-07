package lapr.project.model;

import lapr.project.data.RegisteredUserDB;

public class RegisteredUser {

    private String email;
    private String password;
    private String role;

    public RegisteredUser(String email, String password, String role) {

        setEmail(email);
        setPassword(password);
        setRole(role);
    }

    public String getEmail() {

        return email;
    }

    public String getRole() {

        return role;
    }

    public final void setEmail(String email) {

        if ((email == null) || email.isEmpty()) {
            throw new IllegalArgumentException("Email field is not valid or blank.");
        }
        this.email = email;
    }

    private void setPassword(String password) {

        //TODO: Maybe configurable? 
        if ((password == null) || password.isEmpty() || (password.length() < 8)) {
            throw new IllegalArgumentException("Password field is not valid, blank or has less than 8 characters.");
        }
        this.password = password;
    }

    public final void setRole(String role) {

        if ((role == null) || role.isEmpty()) {
            throw new IllegalArgumentException("Role field is not valid or blank.");
        }
        this.role = role;
    }

    public static RegisteredUser findUser(String strEmail) {

        return new RegisteredUserDB().findUser(strEmail, strEmail);
    }

    public static boolean addUser(RegisteredUser usr) {

        return new RegisteredUserDB().addUser(usr.email, usr.password, usr.role);
    }
}
