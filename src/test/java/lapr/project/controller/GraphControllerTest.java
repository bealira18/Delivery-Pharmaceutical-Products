package lapr.project.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.AddressDB;
import lapr.project.data.PathDB;
import lapr.project.model.Address;
import lapr.project.model.Path;
import lapr.project.model.Vehicle;
import lapr.project.utils.Graph;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import static org.mockito.Mockito.mock;

public class GraphControllerTest {

    private static GraphController gCont;

    public GraphControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        AddressDB aDB = mock(AddressDB.class);
        PathDB pDB = mock(PathDB.class);

        gCont = new GraphController();
        gCont = new GraphController(aDB, pDB);
    }

    /**
     * Test of getGraph method, of class GraphController.
     */
    @Test
    public void testGetGraphScooter() {

        System.out.println("getGraphScooter");
        gCont = new GraphController();
        Graph<Address, Path> expResult = new Graph<>(true);
        Graph<Address, Path> result = gCont.getGraphScooter();
        assertEquals(expResult, result);
    }

    @Test
    public void testFillGraphScooter() throws Exception {

        System.out.println("fillGraphScooter");
        gCont = new GraphController();
        gCont.fillGraphScooter();
    }

    /**
     * Test of getGraphDrone method, of class GraphController.
     */
    @Test
    public void testGetGraphDrone() {

        System.out.println("getGraphDrone");
        gCont = new GraphController();
        Graph<Address, Path> expResult = new Graph<>(false);
        Graph<Address, Path> result = gCont.getGraphDrone();
        assertEquals(expResult, result);
    }

    /**
     * Test of fillGraphDrone method, of class GraphController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFillGraphDrone() throws Exception {

        System.out.println("fillGraphDrone");
        gCont = new GraphController();
        gCont.fillGraphDrone();
    }

    /**
     * Test of getNearestPharmacy method, of class GraphController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetNearestPharmacy() throws Exception {

        System.out.println("getNearestPharmacy");

        GraphController instance = new GraphController();
        Address a = new Address("Test", 0, 0, 0);

        Address expResult = null;
        Address result = instance.getNearestPharmacy(a);
        assertEquals(expResult, result);
    }

    /**
     * Test of getShortestPath method, of class GraphController.
     */
    @Test
    public void testGetShortestPath() {
        System.out.println("getShortestPath");
        boolean scooterOrDrone = true;
        Address aOrig = new Address("Test", 0, 0, 0);
        Address aDest = new Address("Test2", 0, 0, 0);
        LinkedList<Address> shortPath = new LinkedList<>();
        GraphController instance = new GraphController();

        double expResult = 0.0;
        double result = instance.getShortestPath(scooterOrDrone, aOrig, aDest, shortPath);
        assertEquals(expResult, result, 0.0);

        scooterOrDrone = false;
        expResult = 0.0;
        result = instance.getShortestPath(scooterOrDrone, aOrig, aDest, shortPath);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getShortestPathThroughNodes method, of class GraphController.
     */
    @Test
    public void testGetShortestPathThroughNodes() {

        System.out.println("getShortestPathThroughNodes");
        boolean scooterOrDrone = true;
        Address aOrig = new Address("Test", 0, 0, 0);
        Address aDest = new Address("Test2", 0, 0, 0);
        List<Address> nodes = new ArrayList<>();
        LinkedList<Address> shortPath = new LinkedList<>();
        GraphController instance = new GraphController();
        double expResult = 0.0;
        double result = instance.getShortestPathThroughNodes(scooterOrDrone, aOrig, aDest, nodes, shortPath);
        assertEquals(expResult, result, 0.0);

        scooterOrDrone = false;
        expResult = 0.0;
        result = instance.getShortestPathThroughNodes(scooterOrDrone, aOrig, aDest, nodes, shortPath);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of writePathToFile method, of class GraphController.
     */
    @Test
    public void testWritePathToFile() {

        System.out.println("writePathToFile");
        String fileName = "pathTest.csv";
        LinkedList<Address> la = new LinkedList<>();
        Address aOrig = new Address("Test", 0, 0, 0);
        Address aDest = new Address("Test2", 0, 0, 0);
        la.add(aOrig);
        la.add(aDest);
        double distance = 200;
        double energy = 300;
        Vehicle v = new Vehicle(1, 1, 1, 1, 1, 1, 1, 1, 1);
        GraphController instance = new GraphController();
        boolean expResult = true;
        boolean result = instance.writePathToFile(fileName, la, distance, energy, v);
        assertEquals(expResult, result);

        fileName = "";
        expResult = false;
        result = instance.writePathToFile(fileName, la, distance, energy, v);
        assertEquals(expResult, result);
    }

    /**
     * Test of allPaths method, of class GraphController.
     */
    @Test
    public void testAllPaths() {

        System.out.println("allPaths");
        boolean scooterOrDrone = true;
        Address aOrig = new Address("Test", 0, 0, 0);
        Address aDest = new Address("Test2", 0, 0, 0);
        GraphController instance = new GraphController();

        List<LinkedList<Address>> expResult = new ArrayList<>();
        List<LinkedList<Address>> result = instance.allPaths(scooterOrDrone, aOrig, aDest);
        assertEquals(expResult, result);

        scooterOrDrone = false;
        result = instance.allPaths(scooterOrDrone, aOrig, aDest);
        assertEquals(expResult, result);
    }
}
