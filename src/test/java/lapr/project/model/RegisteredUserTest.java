/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
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
        RegisteredUser instance = new RegisteredUser("a@b.c", "qwerty", "Administrator");
        String expResult = "a@b.c";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        
        instance = new RegisteredUser("c@d.e", "qwerty1", "Client");
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
        RegisteredUser instance = new RegisteredUser("a@b.c", "qwerty", "Administrator");
        String expResult = "Administrator";
        String result = instance.getRole();
        assertEquals(expResult, result);
        
        instance = new RegisteredUser("c@d.e", "qwerty1", "Client");
        expResult = "Client";
        result = instance.getRole();
        assertEquals(expResult, result);
    }
    
}
