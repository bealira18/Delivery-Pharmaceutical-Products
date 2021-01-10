/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.auth;

import lapr.project.data.RegisteredUserDB;
import lapr.project.model.RegisteredUser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Ricardo
 */
public class AuthFacadeTest {
    
    private static RegisteredUserDB ruDB;
    
    public AuthFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
        ruDB = mock(RegisteredUserDB.class);
        RegisteredUser ru1 = new RegisteredUser("a@b.c", "qwerty", "administrator");
        RegisteredUser ru2 = new RegisteredUser("c@d.e", "qwerty1", "client");
        when(ruDB.findUser("a@b.c", "qwerty")).thenReturn(ru1);
        when(ruDB.findUser("a@b.c", "qwerty32")).thenReturn(null);
        when(ruDB.findUser("c@d.e", "qwerty1")).thenReturn(ru2);
        when(ruDB.findUser("c@d.e", "qwerty")).thenReturn(null);
        
        AuthFacade af = new AuthFacade();        
    }

    /**
     * Test of doLogin method, of class AuthFacade.
     */
    @Test
    public void testDoLogin() {
        System.out.println("doLogin");
        String strEmail = "a@b.c";
        String strPwd = "qwerty32";
        AuthFacade instance = new AuthFacade(ruDB);
        UserSession expResult = null;
        UserSession result = instance.doLogin(strEmail, strPwd);
        assertEquals(expResult, result);
        
        strEmail = "c@d.e";
        strPwd = "qwerty";
        result = instance.doLogin(strEmail, strPwd);
        assertEquals(expResult, result);

        strPwd = "qwerty1";
        expResult = new UserSession(new RegisteredUser("c@d.e", "qwerty1", "client"));
        result = instance.doLogin(strEmail, strPwd);
        assertEquals(expResult, result);
        
        strEmail = "a@b.c";
        strPwd = "qwerty";
        expResult = new UserSession(new RegisteredUser("a@b.c", "qwerty", "administrator"));
        result = instance.doLogin(strEmail, strPwd);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentSession method, of class AuthFacade.
     */
    @Test
    public void testGetCurrentSession() {
        System.out.println("getCurrentSession");
        AuthFacade instance = new AuthFacade(ruDB);
        UserSession expResult = null;
        UserSession result = instance.getCurrentSession();
        assertEquals(expResult, result);
        
        expResult = new UserSession(new RegisteredUser("c@d.e", "qwerty1", "client"));
        instance.doLogin("c@d.e", "qwerty1");
        assertEquals(expResult, instance.getCurrentSession());
    }

    /**
     * Test of doLogout method, of class AuthFacade.
     */
    @Test
    public void testDoLogout() {
        System.out.println("doLogout");
        AuthFacade instance = new AuthFacade(ruDB);
        assertEquals(null, instance.getCurrentSession());
        
        UserSession expResult = new UserSession(new RegisteredUser("c@d.e", "qwerty1", "client"));
        UserSession result = instance.doLogin("c@d.e", "qwerty1");
        assertEquals(expResult, result);
             
        instance.doLogout();
        assertEquals(null, instance.getCurrentSession());
        
        instance.doLogout();
        assertEquals(null, instance.getCurrentSession());
    }
    
}
