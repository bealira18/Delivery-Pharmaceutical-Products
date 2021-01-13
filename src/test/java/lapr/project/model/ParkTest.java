package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkTest {

    public ParkTest() {
    }

    /**
     * Test of getScooterParkId method, of class Park.
     */
    @Test
    public void testGetScooterParkId() {

        System.out.println("getScooterParkId");
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        int expResult = 1;
        int result = instance.getScooterParkId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacyId method, of class Park.
     */
    @Test
    public void testGetPharmacyId() {

        System.out.println("getPharmacyId");
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        int expResult = 1;
        int result = instance.getPharmacyId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLimit method, of class Park.
     */
    @Test
    public void testGetLimit() {

        System.out.println("getLimit");
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        int expResult = 1;
        int result = instance.getLimit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumChargingStations method, of class Park.
     */
    @Test
    public void testGetNumChargingStations() {

        System.out.println("getNumChargingStations");
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        int expResult = 1;
        int result = instance.getNumChargingStations();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCategory method, of class Park.
     */
    @Test
    public void testGetCategory() {

        System.out.println("getCategory");
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        String expResult = "Test";
        String result = instance.getCategory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class Park.
     */
    @Test
    public void testGetAddress() {

        System.out.println("getAddress");
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        Address expResult = new Address("Test", 1, 1, 1);
        Address result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScooterParkId method, of class Park.
     */
    @Test
    public void testSetScooterParkId() {

        System.out.println("setScooterParkId");
        int scooterParkId = 2;
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        instance.setScooterParkId(scooterParkId);
        int result = instance.getScooterParkId();
        assertEquals(scooterParkId, result);
    }

    /**
     * Test of setPharmacyId method, of class Park.
     */
    @Test
    public void testSetPharmacyId() {

        System.out.println("setPharmacyId");
        int pharmacyId = 2;
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        instance.setPharmacyId(pharmacyId);
        int result = instance.getPharmacyId();
        assertEquals(pharmacyId, result);
    }

    /**
     * Test of setLimit method, of class Park.
     */
    @Test
    public void testSetLimit() {

        System.out.println("setLimit");
        int limit = 2;
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        instance.setLimit(limit);
        int result = instance.getLimit();
        assertEquals(limit, result);
    }

    /**
     * Test of setNumChargingStations method, of class Park.
     */
    @Test
    public void testSetNumChargingStations() {

        System.out.println("setNumChargingStations");
        int numChargingStations = 2;
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        instance.setNumChargingStations(numChargingStations);
        int result = instance.getNumChargingStations();
        assertEquals(numChargingStations, result);
    }

    /**
     * Test of setCategory method, of class Park.
     */
    @Test
    public void testSetCategory() {

        System.out.println("setCategory");
        String category = "Test";
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        instance.setCategory(category);
        String result = instance.getCategory();
        assertEquals(category, result);
    }

    /**
     * Test of setAddress method, of class Park.
     */
    @Test
    public void testSetAddress() {

        System.out.println("setAddress");
        Address address = new Address("Test2", 2, 2, 2);
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        instance.setAddress(address);
        Address result = instance.getAddress();
        assertEquals(address, result);
    }

    /**
     * Test of toString method, of class Park.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        String expResult = "ScooterPark{scooterParkId=1, pharmacyId=1, limit=1, numChargingStations=1"
                + ", category=Test, address=Address{description=Test, latitude=1.0, longitude=1.0, altitude=1.0}}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Park.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        int expResult = 184;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Park.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Address a = new Address("Test", 1, 1, 1);
        Park instance = new Park(1, 1, 1, 1, "Test",a);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new Park(2, 1, 1, 1, "Test",a));
        assertEquals(false, result);

        result = instance.equals(new Park(1, 2, 2, 2, "Test",a));
        assertEquals(true, result);
    }
}
