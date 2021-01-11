package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryStatusTest {

    public DeliveryStatusTest() {
    }

    /**
     * Test of getDeliveryStatusId method, of class DeliveryStatus.
     */
    @Test
    public void testGetDeliveryStatusId() {

        System.out.println("getDeliveryStatusId");
        DeliveryStatus instance = new DeliveryStatus(1, "Test");
        int expResult = 1;
        int result = instance.getDeliveryStatusId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryStatusName method, of class DeliveryStatus.
     */
    @Test
    public void testGetDeliveryStatusName() {

        System.out.println("getDeliveryStatusName");
        DeliveryStatus instance = new DeliveryStatus(1, "Test");
        String expResult = "Test";
        String result = instance.getDeliveryStatusName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDeliveryStatusId method, of class DeliveryStatus.
     */
    @Test
    public void testSetDeliveryStatusId() {

        System.out.println("setDeliveryStatusId");
        int deliveryStatusId = 2;
        DeliveryStatus instance = new DeliveryStatus(1, "Test");
        instance.setDeliveryStatusId(deliveryStatusId);
        int result = instance.getDeliveryStatusId();
        assertEquals(deliveryStatusId, result);
    }

    /**
     * Test of setDeliveryStatusName method, of class DeliveryStatus.
     */
    @Test
    public void testSetDeliveryStatusName() {

        System.out.println("setDeliveryStatusName");
        String deliveryStatusName = "Test2";
        DeliveryStatus instance = new DeliveryStatus(1, "Test");
        instance.setDeliveryStatusName(deliveryStatusName);
        String result = instance.getDeliveryStatusName();
        assertEquals(deliveryStatusName, result);
    }

    /**
     * Test of toString method, of class DeliveryStatus.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        DeliveryStatus instance = new DeliveryStatus(1, "Test");
        String expResult = "DeliveryStatus{deliveryStatusId=1, deliveryStatusName=Test}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class DeliveryStatus.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        DeliveryStatus instance = new DeliveryStatus(1, "Test");
        int expResult = 582;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class DeliveryStatus.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        DeliveryStatus instance = new DeliveryStatus(1, "Test");
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new DeliveryStatus(1, "Test2"));
        assertEquals(true, result);

        result = instance.equals(new DeliveryStatus(2, "Test"));
        assertEquals(false, result);
    }
}
