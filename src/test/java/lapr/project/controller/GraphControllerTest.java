package lapr.project.controller;

import java.util.ArrayList;
import lapr.project.data.AddressDB;
import lapr.project.data.PathDB;
import lapr.project.model.Address;
import lapr.project.model.Path;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import static org.mockito.Mockito.doThrow;
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
     * Test of fillScooterGraph method, of class GraphController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFillGraphScooter2() throws Exception {

        System.out.println("fillGraphScooter2");

        GraphAlgorithms g = mock(GraphAlgorithms.class);
        gCont = mock(GraphController.class);
        doThrow(new Exception()).when(g).fillGraph(new Graph<>(true), new ArrayList<>(), new ArrayList<>());
        Exception ex = assertThrows(Exception.class, () -> gCont.fillGraphScooter());

        gCont.fillGraphScooter();

        assertEquals(ex.getMessage(), ex.getMessage());
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
     */
    @Test
    public void testFillGraphDrone() throws Exception {

        System.out.println("fillGraphDrone");
        gCont = new GraphController();
        gCont.fillGraphScooter();
    }

    @Test
    public void testFillGraphDrone2() throws Exception {

        System.out.println("fillGraphScooter2");

        GraphAlgorithms g = mock(GraphAlgorithms.class);
        gCont = mock(GraphController.class);
        doThrow(new Exception()).when(g).fillGraph(new Graph<>(false), new ArrayList<>(), new ArrayList<>());
        Exception ex = assertThrows(Exception.class, () -> gCont.fillGraphDrone());

        gCont.fillGraphDrone();

        assertEquals(ex.getMessage(), ex.getMessage());
    }
}
