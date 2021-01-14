package lapr.project.model;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PurchaseOrderTest {

    public PurchaseOrderTest() {
    }

    /**
     * Test of getId method, of class PurchaseOrder.
     */
    @Test
    public void testGetId() {

        System.out.println("getId");
        PurchaseOrder instance = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacyId method, of class PurchaseOrder.
     */
    @Test
    public void testGetPharmacyId() {

        System.out.println("getPharmacyId");
        PurchaseOrder instance = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        int expResult = 1;
        int result = instance.getPharmacyId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientEmail method, of class PurchaseOrder.
     */
    @Test
    public void testGetClientEmail() {

        System.out.println("getClientEmail");
        PurchaseOrder instance = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        String expResult = "Test";
        String result = instance.getClientEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmissionDate method, of class PurchaseOrder.
     */
    @Test
    public void testGetEmissionDate() {

        System.out.println("getEmissionDate");
        PurchaseOrder instance = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        LocalDate expResult = LocalDate.of(2077, Month.MARCH, 1);
        LocalDate result = instance.getEmissionDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryFee method, of class PurchaseOrder.
     */
    @Test
    public void testGetDeliveryFee() {

        System.out.println("getDeliveryFee");
        System.setProperty("purchase.order.delivery.fee", "0.0");
        double expResult = 0.0;
        double result = PurchaseOrder.getDeliveryFee();
        assertEquals(expResult, result, 0.01);
        
        System.setProperty("purchase.order.delivery.fee", "5.4");
        expResult = 5.4;
        result = PurchaseOrder.getDeliveryFee();
        assertEquals(expResult, result, 0.01);
        
        System.setProperty("purchase.order.delivery.fee", "12.54");
        expResult = 12.54;
        result = PurchaseOrder.getDeliveryFee();
        assertEquals(expResult, result, 0.01);
        
        
        System.setProperty("purchase.order.delivery.fee", "-5.4");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            PurchaseOrder.getDeliveryFee();
        });
        assertEquals("Invalid Numeric Value (Negative Delivery Fee). Please check your configuration file.", ex.getMessage());
        
        
    }

    /**
     * Test of setId method, of class PurchaseOrder.
     */
    @Test
    public void testSetId() {

        System.out.println("setId");
        int id = 2;
        PurchaseOrder instance = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        instance.setId(id);
        int result = instance.getId();
        assertEquals(id, result);
    }

    /**
     * Test of setPharmacyId method, of class PurchaseOrder.
     */
    @Test
    public void testSetPharmacyId() {

        System.out.println("setPharmacyId");
        int pharmacyId = 2;
        PurchaseOrder instance = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        instance.setPharmacyId(pharmacyId);
        int result = instance.getPharmacyId();
        assertEquals(pharmacyId, result);
    }

    /**
     * Test of setClientEmail method, of class PurchaseOrder.
     */
    @Test
    public void testSetClientEmail() {

        System.out.println("setClientEmail");
        String clientEmail = "Test2";
        PurchaseOrder instance = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        instance.setClientEmail(clientEmail);
        String result = instance.getClientEmail();
        assertEquals(clientEmail, result);
    }

    /**
     * Test of setEmissionDate method, of class PurchaseOrder.
     */
    @Test
    public void testSetEmissionDate() {

        System.out.println("setEmissionDate");
        LocalDate emissionDate = LocalDate.of(2077, Month.MARCH, 2);
        PurchaseOrder instance = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        instance.setEmissionDate(emissionDate);
        LocalDate result = instance.getEmissionDate();
        assertEquals(emissionDate, result);
    }

    /**
     * Test of setDeliveryFee method, of class PurchaseOrder.
     */
    @Test
    public void testSetDeliveryFee() {

        System.out.println("setDeliveryFee");
        System.setProperty("purchase.order.delivery.fee", "5.4");
        double deliveryFee = 1.0;
        PurchaseOrder.setDeliveryFee(deliveryFee);
        double result = Double.parseDouble(System.getProperty("purchase.order.delivery.fee"));
        assertEquals(deliveryFee, result, 0.0);

        deliveryFee = 0;
        PurchaseOrder.setDeliveryFee(deliveryFee);
        result = Double.parseDouble(System.getProperty("purchase.order.delivery.fee"));
        assertEquals(deliveryFee, result, 0.0);

        final double deliveryFee2 = -1.0;
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            PurchaseOrder.setDeliveryFee(deliveryFee2);
        });
        assertEquals("Invalid Numeric Value (Negative Delivery Fee)", ex.getMessage());
    }

    /**
     * Test of toString method, of class PurchaseOrder.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        PurchaseOrder instance = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        String expResult = "PurchaseOrder{id=1, pharmacyId=1, clientEmail=Test, emissionDate=2077-03-01}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class PurchaseOrder.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        PurchaseOrder instance = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        int expResult = 204;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class PurchaseOrder.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        PurchaseOrder instance = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new PurchaseOrder(2, 1, "Test", LocalDate.of(2077, Month.MARCH, 1)));
        assertEquals(false, result);

        result = instance.equals(new PurchaseOrder(1, 2, "Tests", LocalDate.of(2077, Month.MARCH, 1)));
        assertEquals(true, result);
    }
}
