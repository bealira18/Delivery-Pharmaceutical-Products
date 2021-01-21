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
     * Test of addPaths method, of class GeographicalController.
     */
    @Test
    public void testAddPaths() {

        System.out.println("addPaths");
        List<Path> lp = new ArrayList<>();
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
        GeographicalController instance = new GeographicalController();
        instance.addPath(p);
    }

    /**
     * Test of updatePath method, of class GeographicalController.
     */
    @Test
    public void testUpdatePath() {

        System.out.println("updatePath");
        Path p = new Path(new Address("Test", 0, 0, 0), new Address("Test2", 0, 0, 0), 0, 0, 0);
        GeographicalController instance = new GeographicalController();
        instance.updatePath(p);
    }

    /**
     * Test of removePath method, of class GeographicalController.
     */
    @Test
    public void testRemovePath() {

        System.out.println("removePath");
        Address a1 = new Address("Test", 0, 0, 0);
        Address a2 = new Address("Test2", 0, 0, 0);
        GeographicalController instance = new GeographicalController();
        instance.removePath(a1, a2);
    }
}
