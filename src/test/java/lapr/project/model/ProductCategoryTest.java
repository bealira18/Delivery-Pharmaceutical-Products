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
public class ProductCategoryTest {
    
    public ProductCategoryTest() {
    }

    /**
     * Test of getId method, of class ProductCategory.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ProductCategory instance = new ProductCategory(3, "Vacinas");
        int expResult = 3;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class ProductCategory.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        ProductCategory instance = new ProductCategory(3, "Vacinas");
        String expResult = "Vacinas";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class ProductCategory.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 72;
        ProductCategory instance = new ProductCategory(3, "Vacinas");
        instance.setId(id);
        int result = instance.getId();
        assertEquals(id, result);
    }

    /**
     * Test of setName method, of class ProductCategory.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Óleos";
        ProductCategory instance = new ProductCategory(3, "Vacinas");
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of toString method, of class ProductCategory.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ProductCategory instance = new ProductCategory(3, "Vacinas");
        String expResult = "ProductCategory{id=3, name=Vacinas}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ProductCategory.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ProductCategory instance = new ProductCategory(3, "Vacinas");
        int expResult = 1494;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ProductCategory.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        ProductCategory instance = new ProductCategory(3, "Vacinas");
        boolean result = instance.equals(instance);
        assertEquals(true, result);
        
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = "test";
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new ProductCategory(5, "Vacinas");
        result = instance.equals(obj);
        assertEquals(false, result);
        
        obj = new ProductCategory(3, "Vacinas");
        result = instance.equals(obj);
        assertEquals(true, result);
    }
    
}
