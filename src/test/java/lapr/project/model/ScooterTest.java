package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

public class ScooterTest {

    public ScooterTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {

        Scooter s = new Scooter(1, 1);
    }

    /**
     * Test of getScooterStatusId method, of class Scooter.
     */
    @Test
    public void testGetScooterStatusId() {

        System.out.println("getScooterStatusId");
        Scooter instance = new Scooter(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1);
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
        Scooter instance = new Scooter(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1);
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
        Scooter instance = new Scooter(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1);
        String expResult = "Vehicle{idVehicle=1, idPharmacy=1, weight=1.0, aerodynamicCoeficient=1.0, "
                + "frontalArea=1.0, motor=1.0, currentBattery=1.0, maxBattery=1.0, averageSpeed=8.9} Scooter{scooterStatusId=1}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Scooter.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Scooter instance = new Scooter(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1);
        int expResult = 134;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Scooter.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        Scooter instance = new Scooter(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new Scooter(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 2));
        assertEquals(false, result);

        result = instance.equals(new Scooter(2, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1));
        assertEquals(false, result);
    }
}
