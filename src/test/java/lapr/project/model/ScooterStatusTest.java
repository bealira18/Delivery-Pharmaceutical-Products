package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScooterStatusTest {

    public ScooterStatusTest() {
    }

    /**
     * Test of getScooterStatusId method, of class ScooterStatus.
     */
    @Test
    public void testGetScooterStatusId() {

        System.out.println("getScooterStatusId");
        VehicleStatus instance = new VehicleStatus(1, "Test");
        int expResult = 1;
        int result = instance.getVehicleStatusId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooterStatusName method, of class ScooterStatus.
     */
    @Test
    public void testGetScooterStatusName() {

        System.out.println("getScooterStatusName");
        VehicleStatus instance = new VehicleStatus(1, "Test");
        String expResult = "Test";
        String result = instance.getVehicleStatusName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setScooterStatusId method, of class ScooterStatus.
     */
    @Test
    public void testSetScooterStatusId() {

        System.out.println("setScooterStatusId");
        int scooterStatusId = 2;
        VehicleStatus instance = new VehicleStatus(1, "Test");
        instance.setVehicleStatusId(scooterStatusId);
        int result = instance.getVehicleStatusId();
        assertEquals(scooterStatusId, result);
    }

    /**
     * Test of setScooterStatusName method, of class ScooterStatus.
     */
    @Test
    public void testSetScooterStatusName() {

        System.out.println("setScooterStatusName");
        String scooterStatusName = "Test2";
        VehicleStatus instance = new VehicleStatus(1, "Test");
        instance.setVehicleStatusName(scooterStatusName);
        String result = instance.getVehicleStatusName();
        assertEquals(scooterStatusName, result);
    }

    /**
     * Test of toString method, of class ScooterStatus.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        VehicleStatus instance = new VehicleStatus(1, "Test");
        String expResult = "VehicleStatus{vehicleStatusId=1, vehicleStatusName=Test}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ScooterStatus.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        VehicleStatus instance = new VehicleStatus(1, "Test");
        int expResult = 554;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ScooterStatus.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        VehicleStatus instance = new VehicleStatus(1, "Test");
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new VehicleStatus(2, "Test"));
        assertEquals(false, result);

        result = instance.equals(new VehicleStatus(1, "Test2"));
        assertEquals(true, result);
    }
}
