package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathTest {

    public PathTest() {
    }

    /**
     * Test of getAddress1 method, of class Path.
     */
    @Test
    public void testGetAddress1() {

        System.out.println("getAddress1");
        Address a1 = new Address("TestAddress1", 0.0, 0.0, 0.0);
        Address a2 = new Address("TestAddress2", 0.0, 0.0, 0.0);
        Path instance = new Path(a1, a2, 1.0, 90, 12);
        Address expResult = new Address("TestAddress1", 0.0, 0.0, 0.0);
        Address result = instance.getAddress1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress2 method, of class Path.
     */
    @Test
    public void testGetAddress2() {

        System.out.println("getAddress2");
        Address a1 = new Address("TestAddress1", 0.0, 0.0, 0.0);
        Address a2 = new Address("TestAddress2", 0.0, 0.0, 0.0);
        Path instance = new Path(a1, a2, 1.0, 90, 12);
        Address expResult = new Address("TestAddress2", 0.0, 0.0, 0.0);
        Address result = instance.getAddress2();
        assertEquals(expResult, result);
    }

    /**
     * Test of getKineticCoeficient method, of class Path.
     */
    @Test
    public void testGetKineticCoeficient() {

        System.out.println("getKineticCoeficient");
        Address a1 = new Address("TestAddress1", 0.0, 0.0, 0.0);
        Address a2 = new Address("TestAddress2", 0.0, 0.0, 0.0);
        Path instance = new Path(a1, a2, 1.0, 90, 12);
        double expResult = 1.0;
        double result = instance.getKineticCoeficient();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setAddress1 method, of class Path.
     */
    @Test
    public void testSetAddress1() {

        System.out.println("setAddress1");
        Address address1 = new Address("TestAddress3", 0.0, 0.0, 0.0);
        Address a1 = new Address("TestAddress1", 0.0, 0.0, 0.0);
        Address a2 = new Address("TestAddress2", 0.0, 0.0, 0.0);
        Path instance = new Path(a1, a2, 1.0, 90, 12);
        instance.setAddress1(address1);
        Address result = instance.getAddress1();
        assertEquals(address1, result);
    }

    /**
     * Test of setAddress2 method, of class Path.
     */
    @Test
    public void testSetAddress2() {

        System.out.println("setAddress2");
        Address address2 = new Address("TestAddress4", 0.0, 0.0, 0.0);
        Address a1 = new Address("TestAddress1", 0.0, 0.0, 0.0);
        Address a2 = new Address("TestAddress2", 0.0, 0.0, 0.0);
        Path instance = new Path(a1, a2, 1.0, 90, 12);
        instance.setAddress2(address2);
        Address result = instance.getAddress2();
        assertEquals(address2, result);
    }

    /**
     * Test of setKineticCoeficient method, of class Path.
     */
    @Test
    public void testSetKineticCoeficient() {

        System.out.println("setKineticCoeficient");
        double kineticCoeficient = 2.0;
        Address a1 = new Address("TestAddress1", 0.0, 0.0, 0.0);
        Address a2 = new Address("TestAddress2", 0.0, 0.0, 0.0);
        Path instance = new Path(a1, a2, 1.0, 90, 12);
        instance.setKineticCoeficient(kineticCoeficient);
        double result = instance.getKineticCoeficient();
        assertEquals(kineticCoeficient, result, 0.0);
    }

    /**
     * Test of toString method, of class Path.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Address a1 = new Address("TestAddress1", 0.0, 0.0, 0.0);
        Address a2 = new Address("TestAddress2", 0.0, 0.0, 0.0);
        Path instance = new Path(a1, a2, 1.0, 90, 12);
        String expResult = "Path{address1=Address{description=TestAddress1, latitude=0.0, longitude=0.0, altitude=0.0}, " +
                "address2=Address{description=TestAddress2, latitude=0.0, longitude=0.0, altitude=0.0}," +
                " kineticCoeficient=1.0, windAngle=90.0, windSpeed=12.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Path.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Address a1 = new Address("TestAddress1", 0.0, 0.0, 0.0);
        Address a2 = new Address("TestAddress2", 0.0, 0.0, 0.0);
        Path instance = new Path(a1, a2, 1.0, 90, 12);
        int expResult = -540061560;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Path.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        Address a1 = new Address("TestAddress1", 0.0, 0.0, 0.0);
        Address a2 = new Address("TestAddress2", 0.0, 0.0, 0.0);
        Path instance = new Path(a1, a2, 1.0, 90, 12);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        Address address1 = new Address("TestAddress3", 0.0, 0.0, 0.0);
        result = instance.equals(new Path(address1, a1, 1.0, 90, 12));
        assertEquals(false, result);

        result = instance.equals(new Path(a1, address1, 1.0, 90, 12));
        assertEquals(false, result);

        result = instance.equals(new Path(a1, a2, 2.0, 90, 12));
        assertEquals(true, result);
    }
}
