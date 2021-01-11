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
public class AddressTest {
    
    public AddressTest() {
    }

    /**
     * Test of getDescription method, of class Address.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Address instance = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        String expResult = "Rua Joaquim, 542";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLongitude method, of class Address.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Address instance = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        double expResult = -8.62910514603121;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of getLatitude method, of class Address.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Address instance = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        double expResult = 41.15796537787468;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of getAltitude method, of class Address.
     */
    @Test
    public void testGetAltitude() {
        System.out.println("getAltitude");
        Address instance = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        double expResult = 5.200514144411;
        double result = instance.getAltitude();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of setDescription method, of class Address.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "Rua Jaime, 234";
        Address instance = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        instance.setDescription(description);
        String result = instance.getDescription();
        assertEquals(description, result);
    }

    /**
     * Test of setLongitude method, of class Address.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = -9.62454114603121;
        Address instance = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        instance.setLongitude(longitude);
        double result = instance.getLongitude();
        assertEquals(longitude, result, 0.0001);
    }

    /**
     * Test of setLatitude method, of class Address.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 41.15496537787468;
        Address instance = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        instance.setLatitude(latitude);
        double result = instance.getLatitude();
        assertEquals(latitude, result, 0.0001);
    }

    /**
     * Test of setAltitude method, of class Address.
     */
    @Test
    public void testSetAltitude() {
        System.out.println("setAltitude");
        double altitude = 5.31005545144411;
        Address instance = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        instance.setAltitude(altitude);
        double result = instance.getAltitude();
        assertEquals(altitude, result, 0.0001);
    }

    /**
     * Test of toString method, of class Address.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Address instance = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        String expResult = "Address{description=Rua Joaquim, 542, latitude=41.15796537787468, longitude=-8.62910514603121, altitude=5.200514144411}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }    

    /**
     * Test of hashCode method, of class Address.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Address instance = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        int expResult = 1791652326;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Address.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Address instance = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        boolean result = instance.equals(instance);
        assertEquals(true, result);
        
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = "test";
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new Address("Rua João, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        result = instance.equals(obj);
        assertEquals(true, result);
    }
}
