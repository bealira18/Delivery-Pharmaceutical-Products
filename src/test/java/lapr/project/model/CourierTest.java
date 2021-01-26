package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourierTest {

    public CourierTest() {
    }

    /**
     * Test of getName method, of class Courier.
     */
    @Test
    public void testGetName() {

        System.out.println("getName");
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        String expResult = "Name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNif method, of class Courier.
     */
    @Test
    public void testGetNif() {

        System.out.println("getNif");
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        int expResult = 1;
        int result = instance.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSocialSecurity method, of class Courier.
     */
    @Test
    public void testGetSocialSecurity() {

        System.out.println("getSocialSecurity");
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        long expResult = 1L;
        long result = instance.getSocialSecurity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacyId method, of class Courier.
     */
    @Test
    public void testGetPharmacyId() {

        System.out.println("getPharmacyId");
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        int expResult = 1;
        int result = instance.getPharmacyId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeight method, of class Courier.
     */
    @Test
    public void testGetWeight() {

        System.out.println("getWeight");
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        double expResult = 1.0;
        double result = instance.getWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setName method, of class Courier.
     */
    @Test
    public void testSetName() {

        System.out.println("setName");
        String name = "Toze";
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of setNif method, of class Courier.
     */
    @Test
    public void testSetNif() {

        System.out.println("setNif");
        int nif = 2;
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        instance.setNif(nif);
        int result = instance.getNif();
        assertEquals(nif, result);
    }

    /**
     * Test of setSocialSecurity method, of class Courier.
     */
    @Test
    public void testSetSocialSecurity() {

        System.out.println("setSocialSecurity");
        long socialSecurity = 2L;
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        instance.setSocialSecurity(socialSecurity);
        long result = instance.getSocialSecurity();
        assertEquals(socialSecurity, result);
    }

    /**
     * Test of setPharmacyId method, of class Courier.
     */
    @Test
    public void testSetPharmacyId() {

        System.out.println("setPharmacyId");
        int pharmacyId = 2;
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        instance.setPharmacyId(pharmacyId);
        int result = instance.getPharmacyId();
        assertEquals(pharmacyId, result);
    }

    /**
     * Test of setWeight method, of class Courier.
     */
    @Test
    public void testSetWeight() {

        System.out.println("setWeight");
        double weight = 2.0;
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        instance.setWeight(weight);
        double result = instance.getWeight();
        assertEquals(weight, result, 0.0);
    }

    /**
     * Test of toString method, of class Courier.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        String expResult = "Courier{name=Name, nif=1, socialSecurity=1, pharmacyId=1, weight=1.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Courier.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        int expResult = 186;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Courier.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        Courier instance = new Courier("Test", "qwerty", "Name", 1, 1L, 1, 1.0);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new Courier("Tests", "qwerty", "Name", 1, 1L, 1, 1.0));
        assertEquals(false, result);

        result = instance.equals(new Courier("Test", "qwerty", "Name", 2, 1L, 1, 1.0));
        assertEquals(false, result);
    }
}
