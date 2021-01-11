package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpaceTest {

    public ParkingSpaceTest() {
    }

    /**
     * Test of getParkingSpaceId method, of class ParkingSpace.
     */
    @Test
    public void testGetParkingSpaceId() {

        System.out.println("getParkingSpaceId");
        ParkingSpace instance = new ParkingSpace(1, 1, 1, true);
        int expResult = 1;
        int result = instance.getParkingSpaceId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getParkId method, of class ParkingSpace.
     */
    @Test
    public void testGetParkId() {

        System.out.println("getParkId");
        ParkingSpace instance = new ParkingSpace(1, 1, 1, true);
        int expResult = 1;
        int result = instance.getParkId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooterId method, of class ParkingSpace.
     */
    @Test
    public void testGetScooterId() {

        System.out.println("getScooterId");
        ParkingSpace instance = new ParkingSpace(1, 1, 1, true);
        int expResult = 1;
        int result = instance.getScooterId();
        assertEquals(expResult, result);
    }

    /**
     * Test of isIsChargingStation method, of class ParkingSpace.
     */
    @Test
    public void testIsIsChargingStation() {

        System.out.println("isIsChargingStation");
        ParkingSpace instance = new ParkingSpace(1, 1, 1, true);
        boolean expResult = true;
        boolean result = instance.isIsChargingStation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setParkingSpaceId method, of class ParkingSpace.
     */
    @Test
    public void testSetParkingSpaceId() {

        System.out.println("setParkingSpaceId");
        int parkingSpaceId = 2;
        ParkingSpace instance = new ParkingSpace(1, 1, 1, true);
        instance.setParkingSpaceId(parkingSpaceId);
        int result = instance.getParkingSpaceId();
        assertEquals(parkingSpaceId, result);
    }

    /**
     * Test of setParkId method, of class ParkingSpace.
     */
    @Test
    public void testSetParkId() {

        System.out.println("setParkId");
        int parkId = 2;
        ParkingSpace instance = new ParkingSpace(1, 1, 1, true);
        instance.setParkId(parkId);
        int result = instance.getParkId();
        assertEquals(parkId, result);
    }

    /**
     * Test of setScooterId method, of class ParkingSpace.
     */
    @Test
    public void testSetScooterId() {

        System.out.println("setScooterId");
        int scooterId = 2;
        ParkingSpace instance = new ParkingSpace(1, 1, 1, true);
        instance.setScooterId(scooterId);
        int result = instance.getScooterId();
        assertEquals(scooterId, result);
    }

    /**
     * Test of setIsChargingStation method, of class ParkingSpace.
     */
    @Test
    public void testSetIsChargingStation() {

        System.out.println("setIsChargingStation");
        boolean isChargingStation = false;
        ParkingSpace instance = new ParkingSpace(1, 1, 1, true);
        instance.setIsChargingStation(isChargingStation);
        boolean result = instance.isIsChargingStation();
        assertEquals(isChargingStation, result);
    }

    /**
     * Test of toString method, of class ParkingSpace.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        ParkingSpace instance = new ParkingSpace(1, 1, 1, true);
        String expResult = "ParkingSpace{parkingSpaceId=1, parkId=1, scooterId=1, isChargingStation=true}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ParkingSpace.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        ParkingSpace instance = new ParkingSpace(1, 1, 1, true);
        int expResult = 5591;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ParkingSpace.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        ParkingSpace instance = new ParkingSpace(1, 1, 1, true);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new ParkingSpace(1, 1, 2, true));
        assertEquals(true, result);

        result = instance.equals(new ParkingSpace(2, 1, 1, true));
        assertEquals(false, result);

        result = instance.equals(new ParkingSpace(1, 2, 1, true));
        assertEquals(false, result);
    }
}
