package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductLineTest {

    public ProductLineTest() {
    }

    /**
     * Test of getOrderId method, of class ProductLine.
     */
    @Test
    public void testGetOrderId() {

        System.out.println("getOrderId");
        ProductLine instance = new ProductLine(1, 1, 1, 1.0);
        int expResult = 1;
        int result = instance.getOrderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductId method, of class ProductLine.
     */
    @Test
    public void testGetProductId() {

        System.out.println("getProductId");
        ProductLine instance = new ProductLine(1, 1, 1, 1.0);
        int expResult = 1;
        int result = instance.getProductId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductQuantity method, of class ProductLine.
     */
    @Test
    public void testGetProductQuantity() {

        System.out.println("getProductQuantity");
        ProductLine instance = new ProductLine(1, 1, 1, 1.0);
        int expResult = 1;
        int result = instance.getProductQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method, of class ProductLine.
     */
    @Test
    public void testGetPrice() {

        System.out.println("getPrice");
        ProductLine instance = new ProductLine(1, 1, 1, 1.0);
        double expResult = 1.0;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setOrderId method, of class ProductLine.
     */
    @Test
    public void testSetOrderId() {

        System.out.println("setOrderId");
        int orderId = 2;
        ProductLine instance = new ProductLine(1, 1, 1, 1.0);
        instance.setOrderId(orderId);
        int result = instance.getOrderId();
        assertEquals(orderId, result);
    }

    /**
     * Test of setProductId method, of class ProductLine.
     */
    @Test
    public void testSetProductId() {

        System.out.println("setProductId");
        int productId = 2;
        ProductLine instance = new ProductLine(1, 1, 1, 1.0);
        instance.setProductId(productId);
        int result = instance.getProductId();
        assertEquals(productId, result);
    }

    /**
     * Test of setProductQuantity method, of class ProductLine.
     */
    @Test
    public void testSetProductQuantity() {

        System.out.println("setProductQuantity");
        int productQuantity = 2;
        ProductLine instance = new ProductLine(1, 1, 1, 1.0);
        instance.setProductQuantity(productQuantity);
        int result = instance.getProductQuantity();
        assertEquals(productQuantity, result);
    }

    /**
     * Test of setPrice method, of class ProductLine.
     */
    @Test
    public void testSetPrice() {

        System.out.println("setPrice");
        double price = 2.0;
        ProductLine instance = new ProductLine(1, 1, 1, 1.0);
        instance.setPrice(price);
        double result = instance.getPrice();
        assertEquals(price, result, 0.0);
    }

    /**
     * Test of toString method, of class ProductLine.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        ProductLine instance = new ProductLine(1, 1, 1, 1.0);
        String expResult = "ProductLine{orderId=1, productId=1, productQuantity=1, price=1.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ProductLine.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        ProductLine instance = new ProductLine(1, 1, 1, 1.0);
        int expResult = 13535;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ProductLine.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        ProductLine instance = new ProductLine(1, 1, 1, 1.0);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new ProductLine(1, 1, 2, 2.0));
        assertEquals(true, result);

        result = instance.equals(new ProductLine(1, 2, 1, 1.0));
        assertEquals(false, result);

        result = instance.equals(new ProductLine(2, 1, 1, 1.0));
        assertEquals(false, result);
    }
}
