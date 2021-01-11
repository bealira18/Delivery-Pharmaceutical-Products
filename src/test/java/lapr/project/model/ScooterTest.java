package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScooterTest {

    public ScooterTest() {
    }

    /**
     * Test of getScooterStatusId method, of class Scooter.
     */
    @Test
    public void testGetScooterStatusId() {

        System.out.println("getScooterStatusId");
        Scooter instance = new Scooter(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);
        int expResult = 1;
        int result = instance.getScooterStatusId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setScooterStatusId method, of class Scooter.
     */
    @Test
    public void testSetScooterStatusId() {

        System.out.println("setScooterStatusId");
        int scooterStatusId = 2;
        Scooter instance = new Scooter(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);
        instance.setScooterStatusId(scooterStatusId);
        int result = instance.getScooterStatusId();
        assertEquals(scooterStatusId, result);
    }

    /**
     * Test of toString method, of class Scooter.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Scooter instance = new Scooter(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);
        String expResult = "Vehicle{idVehicle=1, idPharmacy=1, weight=1.0, aerodynamicCoeficient=1.0, "
                + "frontalArea=1.0, motor=1.0, currentBattery=1.0, maxBattery=1.0} Scooter{scooterStatusId=1}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
