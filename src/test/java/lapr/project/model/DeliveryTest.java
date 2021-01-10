package lapr.project.model;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTest {

    public DeliveryTest() {
    }

    /**
     * Test of getOrderId method, of class Delivery.
     */
    @Test
    public void testGetOrderId() {

        System.out.println("getOrderId");
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        int expResult = 1;
        int result = instance.getOrderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVehicleId method, of class Delivery.
     */
    @Test
    public void testGetVehicleId() {

        System.out.println("getVehicleId");
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        int expResult = 1;
        int result = instance.getVehicleId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourierEmail method, of class Delivery.
     */
    @Test
    public void testGetCourierEmail() {

        System.out.println("getCourierEmail");
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        String expResult = "Test";
        String result = instance.getCourierEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryStatusId method, of class Delivery.
     */
    @Test
    public void testGetDeliveryStatusId() {

        System.out.println("getDeliveryStatusId");
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        int expResult = 1;
        int result = instance.getDeliveryStatusId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryStart method, of class Delivery.
     */
    @Test
    public void testGetDeliveryStart() {

        System.out.println("getDeliveryStart");
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        LocalDate expResult = LocalDate.of(2077, Month.MARCH, 1);
        LocalDate result = instance.getDeliveryStart();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryEnd method, of class Delivery.
     */
    @Test
    public void testGetDeliveryEnd() {

        System.out.println("getDeliveryEnd");
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        LocalDate expResult = LocalDate.of(2077, Month.MARCH, 2);
        LocalDate result = instance.getDeliveryEnd();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderId method, of class Delivery.
     */
    @Test
    public void testSetOrderId() {

        System.out.println("setOrderId");
        int orderId = 2;
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        instance.setOrderId(orderId);
        int result = instance.getOrderId();
        assertEquals(orderId, result);
    }

    /**
     * Test of setVehicleId method, of class Delivery.
     */
    @Test
    public void testSetVehicleId() {

        System.out.println("setVehicleId");
        int vehicleId = 2;
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        instance.setVehicleId(vehicleId);
        int result = instance.getVehicleId();
        assertEquals(vehicleId, result);
    }

    /**
     * Test of setCourierEmail method, of class Delivery.
     */
    @Test
    public void testSetCourierEmail() {

        System.out.println("setCourierEmail");
        String courierEmail = "Test2";
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        instance.setCourierEmail(courierEmail);
        String result = instance.getCourierEmail();
        assertEquals(courierEmail, result);
    }

    /**
     * Test of setDeliveryStatusId method, of class Delivery.
     */
    @Test
    public void testSetDeliveryStatusId() {

        System.out.println("setDeliveryStatusId");
        int deliveryStatusId = 2;
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        instance.setDeliveryStatusId(deliveryStatusId);
        int result = instance.getDeliveryStatusId();
        assertEquals(deliveryStatusId, result);
    }

    /**
     * Test of setDeliveryStart method, of class Delivery.
     */
    @Test
    public void testSetDeliveryStart() {

        System.out.println("setDeliveryStart");
        LocalDate deliveryStart = LocalDate.of(2077, Month.MARCH, 2);
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        instance.setDeliveryStart(deliveryStart);
        LocalDate result = instance.getDeliveryStart();
        assertEquals(deliveryStart, result);
    }

    /**
     * Test of setDeliveryEnd method, of class Delivery.
     */
    @Test
    public void testSetDeliveryEnd() {

        System.out.println("setDeliveryEnd");
        LocalDate deliveryEnd = LocalDate.of(2077, Month.MARCH, 3);
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        instance.setDeliveryEnd(deliveryEnd);
        LocalDate result = instance.getDeliveryEnd();
        assertEquals(deliveryEnd, result);
    }

    /**
     * Test of toString method, of class Delivery.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        String expResult = "Delivery{orderId=1, vehicleId=1, courierEmail=Test, deliveryStatusId=1, deliveryStart=2077-03-01, deliveryEnd=2077-03-02}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Delivery.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        int expResult = 214;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Delivery.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        Delivery instance = new Delivery(1, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        boolean result = instance.equals(instance);
        assertEquals(true, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "Test";
        result = instance.equals(obj);
        assertEquals(false, result);

        obj = new Delivery(2, 1, "Test", 1, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        result = instance.equals(obj);
        assertEquals(false, result);

        obj = new Delivery(1, 2, "Test", 2, LocalDate.of(2077, Month.MARCH, 1), LocalDate.of(2077, Month.MARCH, 2));
        result = instance.equals(obj);
        assertEquals(true, result);
    }
}
