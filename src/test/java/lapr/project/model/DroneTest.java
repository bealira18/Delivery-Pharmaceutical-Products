package lapr.project.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DroneTest {

    public DroneTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {

        Drone s = new Drone(1, 1,1,1);
    }

    /**
     * Test of getDroneStatusId method, of class Drone.
     */
    @Test
    public void testGetScooterStatusId() {

        System.out.println("getDroneStatusId");
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1,1,1);
        int expResult = 1;
        int result = instance.getDroneStatusId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScooterStatusId method, of class Scooter.
     */
    @Test
    public void testSetScooterStatusId() {

        System.out.println("setDroneStatusId");
        int droneStatusId = 2;
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1,1,1);
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
        Drone instance = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1,1,1);
        String expResult = "Drone{droneStatusId=1, width=1.0, averageVerticalSpeed=1.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
