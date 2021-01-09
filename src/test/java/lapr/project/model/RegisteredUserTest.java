/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ricardo
 */
public class RegisteredUserTest {
    
    public RegisteredUserTest() {
    }

    /**
     * Test of getEmail method, of class RegisteredUser.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        RegisteredUser instance = new RegisteredUser("a@b.c", "qwerty", "administrator");
        String expResult = "a@b.c";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        
        instance = new RegisteredUser("c@d.e", "qwerty1", "client");
        expResult = "c@d.e";
        result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRole method, of class RegisteredUser.
     */
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        RegisteredUser instance = new RegisteredUser("a@b.c", "qwerty", "administrator");
        String expResult = "administrator";
        String result = instance.getRole();
        assertEquals(expResult, result);
        
        instance = new RegisteredUser("c@d.e", "qwerty1", "client");
        expResult = "client";
        result = instance.getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class RegisteredUser.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        RegisteredUser instance = new RegisteredUser("a@b.c", "qwerty", "administrator");
        int expResult = 91584277;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class RegisteredUser.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        RegisteredUser instance = new RegisteredUser("a@b.c", "qwerty", "administrator");
        boolean result = instance.equals(instance);
        assertEquals(true, result);
        
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = "Meme";
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new RegisteredUser("c@d.c", "qwerty", "administrator");
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new RegisteredUser("a@b.c", "qwerty", "administrator");
        result = instance.equals(obj);
        assertEquals(true, result);
    }
    
}
