package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {

    public AdministratorTest() {
    }

    /**
     * Test of getPharmacyId method, of class Administrator.
     */
    @Test
    public void testGetPharmacyId() {

        System.out.println("getPharmacyId");
        Administrator instance = new Administrator("Test@email.com", "", 1, "Test", 0, 0L);
        int expResult = 1;
        int result = instance.getPharmacyId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Administrator.
     */
    @Test
    public void testGetName() {

        System.out.println("getName");
        Administrator instance = new Administrator("Test@email.com", "", 1, "Test", 0, 0L);
        String expResult = "Test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNif method, of class Administrator.
     */
    @Test
    public void testGetNif() {

        System.out.println("getNif");
        Administrator instance = new Administrator("Test@email.com", "", 1, "Test", 0, 0L);
        int expResult = 0;
        int result = instance.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSocialSecurity method, of class Administrator.
     */
    @Test
    public void testGetSocialSecurity() {

        System.out.println("getSocialSecurity");
        Administrator instance = new Administrator("Test@email.com", "", 1, "Test", 0, 0L);
        long expResult = 0L;
        long result = instance.getSocialSecurity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPharmacyId method, of class Administrator.
     */
    @Test
    public void testSetPharmacyId() {

        System.out.println("setPharmacyId");
        int pharmacyId = 1;
        Administrator instance = new Administrator("Test@email.com", "", 0, "Test", 0, 0L);
        instance.setPharmacyId(pharmacyId);
        int expResult = instance.getPharmacyId();
        assertEquals(expResult, pharmacyId);

    }

    /**
     * Test of setName method, of class Administrator.
     */
    @Test
    public void testSetName() {

        System.out.println("setName");
        String name = "Toze";
        Administrator instance = new Administrator("Test@email.com", "", 0, "Test", 0, 0L);
        instance.setName(name);
        String expResult = instance.getName();
        assertEquals(expResult, name);
    }

    /**
     * Test of setNif method, of class Administrator.
     */
    @Test
    public void testSetNif() {

        System.out.println("setNif");
        int nif = 1;
        Administrator instance = new Administrator("Test@email.com", "", 0, "Test", 0, 0L);
        instance.setNif(nif);
        int expResult = instance.getNif();
        assertEquals(expResult, nif);
    }

    /**
     * Test of setSocialSecurity method, of class Administrator.
     */
    @Test
    public void testSetSocialSecurity() {

        System.out.println("setSocialSecurity");
        long socialSecurity = 1L;
        Administrator instance = new Administrator("Test@email.com", "", 0, "Test", 0, 0L);
        instance.setSocialSecurity(socialSecurity);
        long expResult = instance.getSocialSecurity();
        assertEquals(expResult, socialSecurity);
    }

    /**
     * Test of toString method, of class Administrator.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Administrator instance = new Administrator("Test@email.com", "", 0, "Test", 0, 0L);
        String expResult = "Administrator{pharmacyId=0, name=Test, nif=0, socialSecurity=0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Administrator.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Administrator instance = new Administrator("Test@email.com", "", 0, "Test", 0, 0L);
        int expResult = 371;
        int result = instance.hashCode();
        assertEquals(expResult, result);

        instance = new Administrator("Test@email.com", "", 0, "Test", -1, 0L);
        expResult = 370;
        result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Administrator.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        Administrator instance = new Administrator("Test@email.com", "", 0, "Test", 0, 0L);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new Administrator("Test@emails.com", "", 0, "Test", 0, 0L));
        assertEquals(false, result);

        result = instance.equals(new Administrator("Test@email.com", "", 0, "Test", 1, 0L));
        assertEquals(false, result);
    }
}
