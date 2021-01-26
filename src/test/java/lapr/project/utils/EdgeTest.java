package lapr.project.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author frodrigues
 */
public class EdgeTest {

    public EdgeTest() {
    }

    /**
     * Test of getElement method, of class Edge.
     */
    @Test
    public void testGetElement() {
        System.out.println("getElement");

        Edge<String, String> instance = new Edge<>();

        String expResult = null;
        assertEquals(expResult, instance.getElement());

        Edge<String, String> instance1 = new Edge<>("edge1", 1.0, null, null);
        expResult = "edge1";
        assertEquals(expResult, instance1.getElement());
    }

    /**
     * Test of setElement method, of class Edge.
     */
    @Test
    public void testSetElement() {
        System.out.println("setElement");

        Edge<String, String> instance = new Edge<>();

        String eInf = "edge1";
        instance.setElement(eInf);

        assertEquals("edge1", instance.getElement());
    }

    /**
     * Test of getWeight method, of class Edge.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");

        Edge<String, String> instance = new Edge<>();

        double expResult = 0.0;
        assertEquals(expResult, instance.getWeight());
    }

    /**
     * Test of setWeight method, of class Edge.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");

        Edge<String, String> instance = new Edge<>();

        double ew = 2.0;
        instance.setWeight(ew);

        double expResult = 2.0;
        assertEquals(expResult, instance.getWeight(), 2.0);
    }

    /**
     * Test of getVOrig method, of class Edge.
     */
    @Test
    public void testGetVOrig() {
        System.out.println("getVOrig");

        Edge<String, String> instance = new Edge<>();

        Object expResult = null;
        assertEquals(expResult, instance.getVOrig());

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);
        assertEquals(vertex1.getElement(), otherEdge.getVOrig());
    }

    /**
     * Test of setVOrig method, of class Edge.
     */
    @Test
    public void testSetVOrig() {
        System.out.println("setVOrig");

        Edge<String, String> instance = new Edge<>();

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        assertEquals(vertex1.getElement(), instance.getVOrig());
    }

    /**
     * Test of getVDest method, of class Edge.
     */
    @Test
    public void testGetVDest() {
        System.out.println("getVDest");

        Edge<String, String> instance = new Edge<>();

        Object expResult = null;
        assertEquals(expResult, instance.getVDest());

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);
        assertEquals(vertex1.getElement(), otherEdge.getVDest());
    }

    /**
     * Test of setVDest method, of class Edge.
     */
    @Test
    public void testSetVDest() {
        System.out.println("setVDest");

        Edge<String, String> instance = new Edge<>();

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVDest(vertex1);
        assertEquals(vertex1.getElement(), instance.getVDest());
    }

    /**
     * Test of getEndpoints method, of class Edge.
     */
    @Test
    public void testGetEndpoints() {
        System.out.println("getEndpoints");

        Edge<String, String> instance = new Edge<>();

        String[] expResult = null;
        String[] result = instance.getEndpoints();
        assertArrayEquals(expResult, result);

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        instance.setVDest(vertex1);

        String[] expResult1 = {"Vertex1", "Vertex1"};
        assertArrayEquals(expResult1, instance.getEndpoints());
    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Edge<String, String> instance = new Edge<>();      
        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");    
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);
        assertFalse(instance.equals(null), "should not be equal to null");
        assertFalse(instance.equals(new String()), "should not be equal to dif type");
        assertTrue(instance.equals(instance), "should be equal to itself");
        assertTrue(instance.equals(instance.clone()), "should be equal to a clone");
        assertFalse(instance.equals(otherEdge), "should not be equal to otherEdge");
        
        Vertex<Integer, Integer> v1 = new Vertex<>(1, 1);
        Vertex<Integer, Integer> v2 = new Vertex<>(1, 1);
        Edge<Integer, Integer> e1 = new Edge<>(1, 1, v1, v2);
        Edge<Integer, Integer> e2 = new Edge<>(1, 1, null, v2);
        Edge<Integer, Integer> e3 = new Edge<>(1, 1, v1, null);
        
        assertFalse(e1.equals(new Edge<>(1, 1, null, v2)));
        assertFalse(e1.equals(new Edge<>(1, 1, v1, null)));
        assertFalse(e1.equals(new Edge<>(1, 0, v1, v2)));
        assertFalse(e1.equals(new Edge<>(0, 1, v1, v2)));
        
        assertFalse(e2.equals(new Edge<>(1, 1, v1, v2)));
        assertFalse(e2.equals(new Edge<>(1, 1, v1, null)));
        assertFalse(e2.equals(new Edge<>(1, 0, v1, v2)));
        assertFalse(e2.equals(new Edge<>(0, 1, v1, v2)));
        
        assertFalse(e3.equals(new Edge<>(1, 1, null, v2)));
        assertFalse(e3.equals(new Edge<>(1, 1, v1, v2)));
        assertFalse(e3.equals(new Edge<>(1, 0, v1, v2)));
        assertFalse(e3.equals(new Edge<>(0, 1, v1, v2)));
        
        assertTrue(e1.equals(new Edge<>(1, 1, v1, v2)));
        assertTrue(e2.equals(new Edge<>(1, 1, null, v2)));
        assertTrue(e3.equals(new Edge<>(1, 1, v1, null)));
    }

    /**
     * Test of compareTo method, of class Edge.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");

        Edge<String, String> instance = new Edge<>();

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);

        int expResult = -1;
        int result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);

        otherEdge.setWeight(0.0);
        expResult = 0;
        result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);

        instance.setWeight(2.0);
        expResult = 1;
        result = instance.compareTo(otherEdge);
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class Edge.
     */
    @Test
    public void testClone() {
        System.out.println("clone");

        Edge<String, String> instance = new Edge<>();

        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        Edge<String, String> otherEdge = new Edge<>("edge1", 1.0, vertex1, vertex1);

        Edge instClone = otherEdge.clone();

        assertTrue(otherEdge.getElement() == instClone.getElement(), "element should be equal");
        assertTrue(otherEdge.getWeight() == instClone.getWeight(), "weight should be equal");

        String[] expResult = otherEdge.getEndpoints();
        assertArrayEquals(expResult, instClone.getEndpoints());
    }

    /**
     * Test of toString method, of class Edge.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        Edge<String, String> instance = new Edge<>();

        instance.setElement("edge1");
        instance.setWeight(1.0);
        Vertex<String, String> vertex1 = new Vertex<>(1, "Vertex1");
        instance.setVOrig(vertex1);
        instance.setVDest(vertex1);

        String expResult = "(edge1) - 1.0 - Vertex1";
        String result = instance.toString().trim();
        assertEquals(expResult, result);

        System.out.println(instance);
    }
}
