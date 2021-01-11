package lapr.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Marineiji
 */
public class PharmacyTest {

    public PharmacyTest() {
    }

    /**
     * Test of getId method, of class Pharmacy.
     */
    @Test
    public void testGetId() {

        System.out.println("getId");
        Address a = new Address("TestAddress", 0, 0, 0);
        Pharmacy instance = new Pharmacy(1, "Test", a);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Pharmacy.
     */
    @Test
    public void testGetName() {

        System.out.println("getName");
        Address a = new Address("TestAddress", 0, 0, 0);
        Pharmacy instance = new Pharmacy(1, "Test", a);
        String expResult = "Test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class Pharmacy.
     */
    @Test
    public void testGetAddress() {

        System.out.println("getAddress");
        Address a = new Address("TestAddress", 0, 0, 0);
        Pharmacy instance = new Pharmacy(1, "Test", a);
        Address expResult = new Address("TestAddress", 0, 0, 0);
        Address result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Pharmacy.
     */
    @Test
    public void testSetId() {

        System.out.println("setId");
        int id = 2;
        Address a = new Address("TestAddress", 0, 0, 0);
        Pharmacy instance = new Pharmacy(1, "Test", a);
        instance.setId(id);
        int result = instance.getId();
        assertEquals(id, result);
    }

    /**
     * Test of setName method, of class Pharmacy.
     */
    @Test
    public void testSetName() {

        System.out.println("setName");
        String name = "Test2";
        Address a = new Address("TestAddress", 0, 0, 0);
        Pharmacy instance = new Pharmacy(1, "Test", a);
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of setAddress method, of class Pharmacy.
     */
    @Test
    public void testSetAddress() {

        System.out.println("setAddress");
        Address address = new Address("TestAddress2", 0, 0, 0);
        Address a = new Address("TestAddress", 0, 0, 0);
        Pharmacy instance = new Pharmacy(1, "Test", a);
        instance.setAddress(address);
        Address result = instance.getAddress();
        assertEquals(address, result);
    }

    /**
     * Test of toString method, of class Pharmacy.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Address a = new Address("TestAddress", 0, 0, 0);
        Pharmacy instance = new Pharmacy(1, "Test", a);
        String expResult = "Pharmacy{id=1, name=Test, address=Address{description=TestAddress, latitude=0.0, longitude=0.0, altitude=0.0}}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Pharmacy.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Address a = new Address("TestAddress", 0, 0, 0);
        Pharmacy instance = new Pharmacy(1, "Test", a);
        int expResult = 416;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pharmacy.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        Address a = new Address("TestAddress", 0, 0, 0);
        Pharmacy instance = new Pharmacy(1, "Test", a);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new Pharmacy(2, "Test", new Address("TestAddress", 0, 0, 0)));
        assertEquals(false, result);

        result = instance.equals(new Pharmacy(1, "Test2", new Address("TestAddress2", 1, 1, 1)));
        assertEquals(true, result);
    }
}
