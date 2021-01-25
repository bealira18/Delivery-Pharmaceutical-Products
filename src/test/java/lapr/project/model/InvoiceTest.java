package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTest {

    public InvoiceTest() {
    }

    /**
     * Test of getId method, of class Invoice.
     */
    @Test
    public void testGetId() {

        System.out.println("getId");
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderId method, of class Invoice.
     */
    @Test
    public void testGetOrderId() {

        System.out.println("getOrderId");
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        int expResult = 1;
        int result = instance.getOrderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacyId method, of class Invoice.
     */
    @Test
    public void testGetPharmacyId() {
        System.out.println("getPharmacyId");
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        int expResult = 1;
        int result = instance.getPharmacyId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientEmail method, of class Invoice.
     */
    @Test
    public void testGetClientEmail() {

        System.out.println("getClientEmail");
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        String expResult = "Test";
        String result = instance.getClientEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryFee method, of class Invoice.
     */
    @Test
    public void testGetDeliveryFee() {
        System.out.println("getDeliveryFee");
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        double expResult = 2.90;
        double result = instance.getDeliveryFee();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTotalPrice method, of class Invoice.
     */
    @Test
    public void testGetTotalPrice() {
        System.out.println("getTotalPrice");
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        double expResult = 1.0;
        double result = instance.getTotalPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setId method, of class Invoice.
     */
    @Test
    public void testSetId() {

        System.out.println("setId");
        int id = 2;
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        instance.setId(id);
        int expResult = instance.getId();
        assertEquals(expResult, id);
    }

    /**
     * Test of setOrderId method, of class Invoice.
     */
    @Test
    public void testSetOrderId() {

        System.out.println("setOrderId");
        int orderId = 2;
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        instance.setOrderId(orderId);
        int expResult = instance.getOrderId();
        assertEquals(expResult, orderId);
    }

    /**
     * Test of setPharmacyId method, of class Invoice.
     */
    @Test
    public void testSetPharmacyId() {

        System.out.println("setPharmacyId");
        int pharmacyId = 2;
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        instance.setPharmacyId(pharmacyId);
        int expResult = instance.getPharmacyId();
        assertEquals(expResult, pharmacyId);
    }

    /**
     * Test of setClientEmail method, of class Invoice.
     */
    @Test
    public void testSetClientEmail() {

        System.out.println("setClientEmail");
        String clientEmail = "Test2";
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        instance.setClientEmail(clientEmail);
        String expResult = instance.getClientEmail();
        assertEquals(expResult, clientEmail);
    }

    /**
     * Test of setDeliveryFee method, of class Invoice.
     */
    @Test
    public void testSetDeliveryFee() {

        System.out.println("setDeliveryFee");
        double deliveryFee = 2.0;
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        instance.setDeliveryFee(deliveryFee);
        double expResult = instance.getDeliveryFee();
        assertEquals(expResult, deliveryFee, 0.0);
    }


    /**
     * Test of setTotalPrice method, of class Invoice.
     */
    @Test
    public void testSetTotalPrice() {

        System.out.println("setTotalPrice");
        double totalPrice = 2.0;
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        instance.setTotalPrice(totalPrice);
        double expResult = instance.getTotalPrice();
        assertEquals(expResult, totalPrice, 0.0);
    }

    /**
     * Test of toString method, of class Invoice.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        String expResult = "Invoice{id=1, orderId=1, pharmacyId=1, clientEmail='Test', deliveryFee=2.9, totalPrice=1.0, noVATprice=1.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Invoice.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        int expResult = 330;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Invoice.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        Invoice instance = new Invoice(1, 1, 1, "Test", 2.90, 1,1);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new Invoice(2, 1, 1, "Test", 2.90, 1,1));
        assertEquals(false, result);

        result = instance.equals(new Invoice(1, 2, 3, "Test2", 2.90, 4,1));
        assertEquals(true, result);
    }

    /**
     * Test of getIVA method, of class Invoice.
     */
    @Test
    public void testGetIVA() {
        System.out.println("getIVA");
        System.setProperty("invoice.iva", "0.0");
        double expResult = 0.0;
        double result = Invoice.getIVA();
        assertEquals(expResult, result, 0.01);
        
        System.setProperty("invoice.iva", "5.4");
        expResult = 5.4;
        result = Invoice.getIVA();
        assertEquals(expResult, result, 0.01);
        
        System.setProperty("invoice.iva", "12.54");
        expResult = 12.54;
        result = Invoice.getIVA();
        assertEquals(expResult, result, 0.01);
        
        
        System.setProperty("invoice.iva", "-5.4");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            Invoice.getIVA();
        });
        assertEquals("Invalid Numeric Value (Negative IVA). Please check your configuration file.", ex.getMessage());
    }

    /**
     * Test of setIVA method, of class Invoice.
     */
    @Test
    public void testSetIVA() {
        System.out.println("setIVA");
        System.setProperty("invoice.iva", "5.4");
        double iva = 1.0;
        Invoice.setIVA(iva);
        double result = Double.parseDouble(System.getProperty("invoice.iva"));
        assertEquals(iva, result, 0.0);

        iva = 0;
        Invoice.setIVA(iva);
        result = Double.parseDouble(System.getProperty("invoice.iva"));
        assertEquals(iva, result, 0.0);

        final double iva2 = -1.0;
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            Invoice.setIVA(iva2);
        });
        assertEquals("Invalid Numeric Value (Negative IVA)", ex.getMessage());
    }
}
