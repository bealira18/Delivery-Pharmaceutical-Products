/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.time.LocalDate;
import java.time.Month;
import lapr.project.data.ClientDB;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Ricardo
 */
public class RegisterClientControllerTest {
    
    private static ClientDB cDB;
    
    public RegisterClientControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
        cDB = mock(ClientDB.class);
        when(cDB.addClient("test@email.com", "qwerty", "Joaquim Alberto", 123456789, 5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454, "Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411)).thenReturn(true);
        when(cDB.addClient("test", "qwerty", "Joaquim Alberto", 123456789, 5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454, "Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411)).thenReturn(false);
        when(cDB.addClient("test@email.com", "qwerty", "Joaquim Alberto", 123456789, 327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454, "Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411)).thenReturn(false);
        when(cDB.addClient("test@email.com", "qwerty", "Joaquim Alberto", 123456789, 5295360011327825L, LocalDate.of(2003, Month.MARCH, 1), (short) 454, "Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411)).thenReturn(false);
    }

    /**
     * Test of addClient method, of class RegisterClientController.
     */
    @Test
    public void testAddClient() {
        System.out.println("addClient");
        String email = "test@email.com";
        String password = "qwerty";
        String name = "Joaquim Alberto";
        int nif = 123456789;
        long creditCard = 5295360011327825L;
        LocalDate expirationDate = LocalDate.of(2077, Month.MARCH, 1);
        short ccv = 454;
        String address = "Rua Joaquim, 542";
        double latitude = 41.15796537787468;
        double longitude = -8.62910514603121;
        double altitude = 5.200514144411;
        RegisterClientController instance = new RegisterClientController(cDB);
        boolean expResult = true;
        boolean result = instance.addClient(email, password, name, nif, creditCard, expirationDate, ccv, address, latitude, longitude, altitude);
        assertEquals(expResult, result);
        
        expResult = false;
        email = "test";
        result = instance.addClient(email, password, name, nif, creditCard, expirationDate, ccv, address, latitude, longitude, altitude);
        assertEquals(expResult, result);
        
        email = "test@email.com";
        creditCard = 327825L;
        result = instance.addClient(email, password, name, nif, creditCard, expirationDate, ccv, address, latitude, longitude, altitude);
        assertEquals(expResult, result);
        
        creditCard = 5295360011327825L;
        expirationDate = LocalDate.of(2003, Month.MARCH, 1);
        result = instance.addClient(email, password, name, nif, creditCard, expirationDate, ccv, address, latitude, longitude, altitude);
        assertEquals(expResult, result);
    }
    
}
