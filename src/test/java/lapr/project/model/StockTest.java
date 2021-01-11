package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StockTest {

    public StockTest() {
    }

    /**
     * Test of getPharmacyId method, of class Stock.
     */
    @Test
    public void testGetPharmacyId() {

        System.out.println("getPharmacyId");
        Stock instance = new Stock(1, 1, 1);
        int expResult = 1;
        int result = instance.getPharmacyId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductId method, of class Stock.
     */
    @Test
    public void testGetProductId() {

        System.out.println("getProductId");
        Stock instance = new Stock(1, 1, 1);
        int expResult = 1;
        int result = instance.getProductId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuantity method, of class Stock.
     */
    @Test
    public void testGetQuantity() {

        System.out.println("getQuantity");
        Stock instance = new Stock(1, 1, 1);
        int expResult = 1;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPharmacyId method, of class Stock.
     */
    @Test
    public void testSetPharmacyId() {

        System.out.println("setPharmacyId");
        int pharmacyId = 2;
        Stock instance = new Stock(1, 1, 1);
        instance.setPharmacyId(pharmacyId);
        int result = instance.getPharmacyId();
        assertEquals(pharmacyId, result);
    }

    /**
     * Test of setProductId method, of class Stock.
     */
    @Test
    public void testSetProductId() {

        System.out.println("setProductId");
        int productId = 2;
        Stock instance = new Stock(1, 1, 1);
        instance.setProductId(productId);
        int result = instance.getProductId();
        assertEquals(productId, result);
    }

    /**
     * Test of setQuantity method, of class Stock.
     */
    @Test
    public void testSetQuantity() {

        System.out.println("setQuantity");
        int quantity = 2;
        Stock instance = new Stock(1, 1, 1);
        instance.setQuantity(quantity);
        int result = instance.getQuantity();
        assertEquals(quantity, result);
    }

    /**
     * Test of toString method, of class Stock.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Stock instance = new Stock(1, 1, 1);
        String expResult = "Stock{pharmacyId=1, productId=1, quantity=1}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Stock.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Stock instance = new Stock(1, 1, 1);
        int expResult = 48307;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Stock.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        Stock instance = new Stock(1, 1, 1);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);
        
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);
        
        result = instance.equals(new Stock(1, 1, 0));
        assertEquals(true, result);
        
        result = instance.equals(new Stock(1, 2, 1));
        assertEquals(false, result);
        
        result = instance.equals(new Stock(2, 1, 1));
        assertEquals(false, result);
    }
}
