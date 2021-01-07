/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.data.UserDB;

/**
 *
 * @author Ricardo
 */
public class User {

    private String email;
    private String password;
    private String role;

    public User(String email, String password, String role) {
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
    }

    public void setEmail(String email) {
        if ((email == null) || email.isEmpty()) {
            throw new IllegalArgumentException("Email field is not valid or blank.");
        }
        this.email = email;
    }

    private void setPassword(String password) {
        if ((password == null) || password.isEmpty() || (password.length() < 8)) //TODO: Maybe configurable?
        {
            throw new IllegalArgumentException("Password field is not valid, blank or has less than 8 characters.");
        }
        this.password = password;
    }

    public void setRole(String role) {
        if ((role == null) || role.isEmpty()) {
            throw new IllegalArgumentException("Role field is not valid or blank.");

        }
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
    
    public static User findUser(String strEmail) {
        return new UserDB().findUser(strEmail, strEmail);
    }

    public static boolean addUser(User usr) {
        return new UserDB().addUser(usr.email, usr.password, usr.role);
    }

}
