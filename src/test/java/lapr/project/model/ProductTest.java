/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ricardo
 */
public class ProductTest {
    
    public ProductTest() {
    }

    /**
     * Test of getId method, of class Product.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        int expResult = 42;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Product.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        String expResult = "SARS-CoV2 Vaccine POGGERS";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method, of class Product.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        double expResult = 1337.42;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getWeight method, of class Product.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        double expResult = 548.54;
        double result = instance.getWeight();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getCategoryId method, of class Product.
     */
    @Test
    public void testGetCategoryId() {
        System.out.println("getCategoryId");
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        int expResult = 446;
        int result = instance.getCategoryId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Product.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 66;
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        instance.setId(id);
        int result = instance.getId();
        assertEquals(id, result);
    }

    /**
     * Test of setName method, of class Product.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Essential Oil";
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of setPrice method, of class Product.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 24.70;
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        instance.setPrice(price);
        double result = instance.getPrice();
        assertEquals(price, result, 0.01);
    }

    /**
     * Test of setWeight method, of class Product.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        double weight = 0.66;
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        instance.setWeight(weight);
        double result = instance.getWeight();
        assertEquals(weight, result, 0.01);
    }

    /**
     * Test of setCategoryId method, of class Product.
     */
    @Test
    public void testSetCategoryId() {
        System.out.println("setCategoryId");
        int categoryId = 87;
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        instance.setCategoryId(categoryId);
        int result = instance.getCategoryId();
        assertEquals(categoryId, result);
    }

    /**
     * Test of toString method, of class Product.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        String expResult = "{name=SARS-CoV2 Vaccine POGGERS, price=1337.42€, weight=548.54kg}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Product.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        int expResult = 809;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Product.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Product instance = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        boolean result = instance.equals(instance);
        assertEquals(true, result);
        
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = "test";
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new Product(45, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new Product(42, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446);
        result = instance.equals(obj);
        assertEquals(true, result);
    }
    
}
