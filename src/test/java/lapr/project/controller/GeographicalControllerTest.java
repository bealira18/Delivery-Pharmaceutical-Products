package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.data.AddressDB;
import lapr.project.data.PathDB;
import lapr.project.model.Address;
import lapr.project.model.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeographicalControllerTest {

    private static GeographicalController geoCont;

    public GeographicalControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        AddressDB aDB = mock(AddressDB.class);
        PathDB pDB = mock(PathDB.class);

        geoCont = new GeographicalController();
        geoCont = new GeographicalController(aDB, pDB);
    }

    /**
     * Test of addAddresses method, of class GeographicalController.
     */
    @Test
    public void testAddAddresses() {

        System.out.println("addAddresses");
        List<Address> la = new ArrayList<>();
        la.add(new Address("Test", 0, 0, 0));
        la.add(new Address("Test2", 0, 0, 0));
        GeographicalController instance = new GeographicalController();
        instance.addAddresses(la);
    }

    /**
     * Test of addAddress method, of class GeographicalController.
     */
    @Test
    public void testAddAddress() {

        System.out.println("addAddress");
        Address a = new Address("Test", 0, 0, 0);
        GeographicalController instance = new GeographicalController();
        instance.addAddress(a);
    }

    /**
     * Test of getAddresses method, of class GeographicalController.
     */
    @Test
    public void testGetAddresses() throws Exception {

        System.out.println("getAddresses");
        AddressDB aDB = mock(AddressDB.class);
        PathDB pDB = mock(PathDB.class);
        List<Address> expResult = new ArrayList<>();
        expResult.add(new Address("Test", 0, 0, 0));
        expResult.add(new Address("Test", 0, 0, 0));

        when(aDB.getAddresses()).thenReturn(expResult);
        GeographicalController instance = new GeographicalController(aDB, pDB);
        List<Address> result = instance.getAddresses();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPharmacyAddresses method, of class GeographicalController.
     */
    @Test
    public void testGetPharmacyAddresses() throws Exception {

        System.out.println("getPharmacyAddresses");
        AddressDB aDB = mock(AddressDB.class);
        PathDB pDB = mock(PathDB.class);
        List<Address> expResult = new ArrayList<>();
        expResult.add(new Address("Test", 0, 0, 0));
        expResult.add(new Address("Test", 0, 0, 0));

        when(aDB.getPharmacyAddresses()).thenReturn(expResult);
        GeographicalController instance = new GeographicalController(aDB, pDB);
        List<Address> result = instance.getPharmacyAddresses();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPaths method, of class GeographicalController.
     */
    @Test
    public void testGetPaths() throws Exception {

        System.out.println("getPaths");
        AddressDB aDB = mock(AddressDB.class);
        PathDB pDB = mock(PathDB.class);

        List<Address> la = new ArrayList<>();
        la.add(new Address("Test", 0, 0, 0));
        la.add(new Address("Test2", 0, 0, 0));

        List<Path> expResult = new ArrayList<>();
        expResult.add(new Path(new Address("Test", 0, 0, 0),
                new Address("Test2", 0, 0, 0), 0, 0, 0));
        expResult.add(new Path(new Address("Test2", 0, 0, 0),
                new Address("Test", 0, 0, 0), 0, 0, 0));

        when(pDB.getPaths(la)).thenReturn(expResult);
        GeographicalController instance = new GeographicalController(aDB, pDB);
        List<Path> result = instance.getPaths(la);
        assertEquals(expResult, result);
    }

    /**
     * Test of addPaths method, of class GeographicalController.
     */
    @Test
    public void testAddPaths() {

        System.out.println("addPaths");
        List<Path> lp = new ArrayList<>();
        lp.add(new Path(new Address("Test", 0, 0, 0), new Address("Test2", 0, 0, 0), 0, 0, 0));
        lp.add(new Path(new Address("Test3", 0, 0, 0), new Address("Test4", 0, 0, 0), 0, 0, 0));
        GeographicalController instance = new GeographicalController();
        instance.addPaths(lp);
    }

    /**
     * Test of addPath method, of class GeographicalController.
     */
    @Test
    public void testAddPath() {

        System.out.println("addPath");
        Path p = new Path(new Address("Test", 0, 0, 0), new Address("Test2", 0, 0, 0), 0, 0, 0);
        Path p2 = new Path(new Address("Test2", 0, 0, 0), new Address("Test1", 0, 0, 0), 0, 0, 0);

        AddressDB aDB = mock(AddressDB.class);
        PathDB pDB = mock(PathDB.class);

        when(pDB.addPath(p)).thenReturn(Boolean.TRUE);
        when(pDB.addPath(p2)).thenReturn(Boolean.FALSE);

        GeographicalController instance = new GeographicalController(aDB, pDB);

        boolean expResult = true;
        boolean result = instance.addPath(p);
        assertEquals(expResult, result);

        expResult = false;
        result = instance.addPath(p2);
        assertEquals(expResult, result);
    }

    /**
     * Test of updatePath method, of class GeographicalController.
     */
    @Test
    public void testUpdatePath() {

        System.out.println("updatePath");
        Path p = new Path(new Address("Test", 0, 0, 0), new Address("Test2", 0, 0, 0), 0, 0, 0);
        Path p2 = new Path(new Address("Test2", 0, 0, 0), new Address("Test1", 0, 0, 0), 0, 0, 0);

        AddressDB aDB = mock(AddressDB.class);
        PathDB pDB = mock(PathDB.class);

        when(pDB.updatePath(p)).thenReturn(Boolean.TRUE);
        when(pDB.updatePath(p2)).thenReturn(Boolean.FALSE);

        GeographicalController instance = new GeographicalController(aDB, pDB);

        boolean expResult = true;
        boolean result = instance.updatePath(p);
        assertEquals(expResult, result);

        expResult = false;
        result = instance.updatePath(p2);
        assertEquals(expResult, result);
    }

    /**
     * Test of removePath method, of class GeographicalController.
     */
    @Test
    public void testRemovePath() {

        System.out.println("removePath");

        Address a1 = new Address("Test", 0, 0, 0);
        Address a2 = new Address("Test2", 0, 0, 0);

        AddressDB aDB = mock(AddressDB.class);
        PathDB pDB = mock(PathDB.class);

        when(pDB.removePath(a1, a2)).thenReturn(Boolean.TRUE);
        when(pDB.removePath(a2, a1)).thenReturn(Boolean.FALSE);

        GeographicalController instance = new GeographicalController(aDB, pDB);
        boolean result = instance.removePath(a1, a2);
        boolean expResult = true;
        assertEquals(result, expResult);

        result = instance.removePath(a2, a1);
        expResult = false;
        assertEquals(expResult, result);
    }
}
