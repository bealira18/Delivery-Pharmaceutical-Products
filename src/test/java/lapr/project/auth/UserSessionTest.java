/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.auth;

import lapr.project.model.RegisteredUser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ricardo
 */
public class UserSessionTest {
    
    public UserSessionTest() {
    }

    /**
     * Test of getUserEmail method, of class UserSession.
     */
    @Test
    public void testGetUserEmail() {
        System.out.println("getUserEmail");
        UserSession instance = new UserSession(new RegisteredUser("a@b.c", "qwerty", "administrator"));
        String expResult = "a@b.c";
        String result = instance.getUserEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserRole method, of class UserSession.
     */
    @Test
    public void testGetUserRole() {
        System.out.println("getUserRole");
        UserSession instance = new UserSession(new RegisteredUser("a@b.c", "qwerty", "administrator"));
        String expResult = "administrator";
        String result = instance.getUserRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of doLogout method, of class UserSession.
     */
    @Test
    public void testDoLogout() {
        System.out.println("doLogout");
        UserSession instance = new UserSession(new RegisteredUser("a@b.c", "qwerty", "administrator"));
        UserSession expResult = new UserSession(new RegisteredUser("a@b.c", "qwerty", "administrator"));
        assertEquals(expResult, instance);
        
        instance.doLogout();
        assertEquals(false, instance.isLoggedIn());
    }

    /**
     * Test of isLoggedIn method, of class UserSession.
     */
    @Test
    public void testIsLoggedIn() {
        System.out.println("isLoggedIn");
        UserSession instance = new UserSession(new RegisteredUser("a@b.c", "qwerty", "administrator"));
        boolean expResult = true;
        boolean result = instance.isLoggedIn();
        assertEquals(expResult, result);
        
        instance.doLogout();
        expResult = false;
        result = instance.isLoggedIn();
        assertEquals(expResult, result); 
    }

    /**
     * Test of hashCode method, of class UserSession.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        UserSession instance = new UserSession(new RegisteredUser("a@b.c", "qwerty", "administrator"));
        int expResult = 91584648;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class UserSession.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Object obj = null;
        UserSession instance = new UserSession(new RegisteredUser("a@b.c", "qwerty", "administrator"));
        boolean result = instance.equals(instance);
        assertEquals(true, result);
        
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = "test";
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new UserSession(new RegisteredUser("c@d.e", "qwerty", "administrator"));
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new UserSession(new RegisteredUser("a@b.c", "qwerty", "administrator"));
        result = instance.equals(obj);
        assertEquals(true, result);
    }
    
}
