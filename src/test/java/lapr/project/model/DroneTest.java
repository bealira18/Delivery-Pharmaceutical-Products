package lapr.project.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class DroneTest {

    public DroneTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {

        Drone s = new Drone(1, 1, 1, 1);
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getDroneStatusId method, of class Drone.
     */
    @Test
    public void testGetDroneStatusId() {

        System.out.println("getDroneStatusId");
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1, 1, 1);
        int expResult = 1;
        int result = instance.getDroneStatusId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScooterStatusId method, of class Scooter.
     */
    @Test
    public void testSetDroneStatusId() {

        System.out.println("setDroneStatusId");
        int droneStatusId = 2;
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1, 1, 1);
        instance.setDroneStatusId(droneStatusId);
        int result = instance.getDroneStatusId();
        assertEquals(droneStatusId, result);
    }

    /**
     * Test of toString method, of class Scooter.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1, 1, 1);
        String expResult = "Drone{droneStatusId=1, width=1.0, averageVerticalSpeed=1.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWidth method, of class Drone.
     */
    @Test
    public void testGetWidth() {

        System.out.println("getWidth");
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1, 1, 1);
        double expResult = 1;
        double result = instance.getDroneStatusId();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getAverageVerticalSpeed method, of class Drone.
     */
    @Test
    public void testGetAverageVerticalSpeed() {
        System.out.println("getAverageVerticalSpeed");
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1, 1, 1);
        double expResult = 1;
        double result = instance.getDroneStatusId();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setWidth method, of class Drone.
     */
    @Test
    public void testSetWidth() {

        System.out.println("setWidth");
        double width = 2;
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1, 1, 1);
        instance.setWidth(width);
        double expResult = 2;
        double result = instance.getWidth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAverageVerticalSpeed method, of class Drone.
     */
    @Test
    public void testSetAverageVerticalSpeed() {

        System.out.println("setAverageVerticalSpeed");
        double averageVerticalSpeed = 2;
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1, 1, 1);
        instance.setAverageVerticalSpeed(averageVerticalSpeed);
        double expResult = 2;
        double result = instance.getAverageVerticalSpeed();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Drone.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1, 1, 1);
        int expResult = 624;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Drone.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1, 1, 1);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1, 1, 2));
        assertEquals(false, result);

        result = instance.equals(new Drone(2, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1, 1, 1));
        assertEquals(false, result);
    }
}
