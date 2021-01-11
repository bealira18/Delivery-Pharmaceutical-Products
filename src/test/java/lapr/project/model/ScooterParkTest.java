package lapr.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScooterParkTest {

    public ScooterParkTest() {
    }

    /**
     * Test of getScooterParkId method, of class ScooterPark.
     */
    @Test
    public void testGetScooterParkId() {

        System.out.println("getScooterParkId");
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        int expResult = 1;
        int result = instance.getScooterParkId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacyId method, of class ScooterPark.
     */
    @Test
    public void testGetPharmacyId() {

        System.out.println("getPharmacyId");
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        int expResult = 1;
        int result = instance.getPharmacyId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLimit method, of class ScooterPark.
     */
    @Test
    public void testGetLimit() {

        System.out.println("getLimit");
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        int expResult = 1;
        int result = instance.getLimit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumChargingStations method, of class ScooterPark.
     */
    @Test
    public void testGetNumChargingStations() {

        System.out.println("getNumChargingStations");
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        int expResult = 1;
        int result = instance.getNumChargingStations();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class ScooterPark.
     */
    @Test
    public void testGetAddress() {

        System.out.println("getAddress");
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        Address expResult = new Address("Test", 1, 1, 1);
        Address result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScooterParkId method, of class ScooterPark.
     */
    @Test
    public void testSetScooterParkId() {

        System.out.println("setScooterParkId");
        int scooterParkId = 2;
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        instance.setScooterParkId(scooterParkId);
        int result = instance.getScooterParkId();
        assertEquals(scooterParkId, result);
    }

    /**
     * Test of setPharmacyId method, of class ScooterPark.
     */
    @Test
    public void testSetPharmacyId() {

        System.out.println("setPharmacyId");
        int pharmacyId = 2;
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        instance.setPharmacyId(pharmacyId);
        int result = instance.getPharmacyId();
        assertEquals(pharmacyId, result);
    }

    /**
     * Test of setLimit method, of class ScooterPark.
     */
    @Test
    public void testSetLimit() {

        System.out.println("setLimit");
        int limit = 2;
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        instance.setLimit(limit);
        int result = instance.getLimit();
        assertEquals(limit, result);
    }

    /**
     * Test of setNumChargingStations method, of class ScooterPark.
     */
    @Test
    public void testSetNumChargingStations() {

        System.out.println("setNumChargingStations");
        int numChargingStations = 2;
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        instance.setNumChargingStations(numChargingStations);
        int result = instance.getNumChargingStations();
        assertEquals(numChargingStations, result);
    }

    /**
     * Test of setAddress method, of class ScooterPark.
     */
    @Test
    public void testSetAddress() {

        System.out.println("setAddress");
        Address address = new Address("Test2", 2, 2, 2);
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        instance.setAddress(address);
        Address result = instance.getAddress();
        assertEquals(address, result);
    }

    /**
     * Test of toString method, of class ScooterPark.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        String expResult = "ScooterPark{scooterParkId=1, pharmacyId=1, limit=1, numChargingStations=1,"
                + " address=Address{description=Test, latitude=1.0, longitude=1.0, altitude=1.0}}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ScooterPark.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        int expResult = 184;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScooterPark.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Address a = new Address("Test", 1, 1, 1);
        ScooterPark instance = new ScooterPark(1, 1, 1, 1, a);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new ScooterPark(2, 1, 1, 1, a));
        assertEquals(false, result);

        result = instance.equals(new ScooterPark(1, 2, 2, 2, a));
        assertEquals(true, result);
    }
}
