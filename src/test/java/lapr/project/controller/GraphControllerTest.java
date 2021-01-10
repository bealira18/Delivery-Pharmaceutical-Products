package lapr.project.controller;

import lapr.project.data.AddressDB;
import lapr.project.data.PathDB;
import lapr.project.model.Address;
import lapr.project.model.Path;
import lapr.project.utils.Graph;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GraphControllerTest {

    private static GraphController gCont;

    public GraphControllerTest() {
    }

    @BeforeClass
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
    public void testGetGraph() {

        System.out.println("getGraph");
        gCont = new GraphController();
        Graph<Address, Path> expResult = new Graph<>(true);
        Graph<Address, Path> result = gCont.getGraph();
        assertEquals(expResult, result);
    }

    /**
     * Test of fillGraph method, of class GraphController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFillGraph() throws Exception {

        System.out.println("fillGraph");
        gCont = new GraphController();
        gCont.fillGraph();
    }
}
