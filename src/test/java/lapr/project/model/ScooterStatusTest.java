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
        ScooterStatus instance = new ScooterStatus(1, "Test");
        int expResult = 1;
        int result = instance.getScooterStatusId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooterStatusName method, of class ScooterStatus.
     */
    @Test
    public void testGetScooterStatusName() {

        System.out.println("getScooterStatusName");
        ScooterStatus instance = new ScooterStatus(1, "Test");
        String expResult = "Test";
        String result = instance.getScooterStatusName();
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
        ScooterStatus instance = new ScooterStatus(1, "Test");
        instance.setScooterStatusId(scooterStatusId);
        int result = instance.getScooterStatusId();
        assertEquals(scooterStatusId, result);
    }

    /**
     * Test of setScooterStatusName method, of class ScooterStatus.
     */
    @Test
    public void testSetScooterStatusName() {

        System.out.println("setScooterStatusName");
        String scooterStatusName = "Test2";
        ScooterStatus instance = new ScooterStatus(1, "Test");
        instance.setScooterStatusName(scooterStatusName);
        String result = instance.getScooterStatusName();
        assertEquals(scooterStatusName, result);
    }

    /**
     * Test of toString method, of class ScooterStatus.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        ScooterStatus instance = new ScooterStatus(1, "Test");
        String expResult = "ScooterStatus{scooterStatusId=1, scooterStatusName=Test}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ScooterStatus.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        ScooterStatus instance = new ScooterStatus(1, "Test");
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
        ScooterStatus instance = new ScooterStatus(1, "Test");
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new ScooterStatus(2, "Test"));
        assertEquals(false, result);

        result = instance.equals(new ScooterStatus(1, "Test2"));
        assertEquals(true, result);
    }
}
