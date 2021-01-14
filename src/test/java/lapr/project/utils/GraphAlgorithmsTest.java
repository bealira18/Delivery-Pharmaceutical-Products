package lapr.project.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DEI-ISEP
 */
public class GraphAlgorithmsTest {

    Graph<String, String> completeMap;
    Graph<String, String> incompleteMap;

    public GraphAlgorithmsTest() {
    }

    @BeforeEach
    public void setUp() {

        completeMap = new Graph<>(false);
        incompleteMap = new Graph<>(false);

        completeMap.insertVertex("Porto");
        completeMap.insertVertex("Braga");
        completeMap.insertVertex("Vila Real");
        completeMap.insertVertex("Aveiro");
        completeMap.insertVertex("Coimbra");
        completeMap.insertVertex("Leiria");

        completeMap.insertVertex("Viseu");
        completeMap.insertVertex("Guarda");
        completeMap.insertVertex("Castelo Branco");
        completeMap.insertVertex("Lisboa");
        completeMap.insertVertex("Faro");

        completeMap.insertEdge("Porto", "Aveiro", "A1", 75);
        completeMap.insertEdge("Porto", "Braga", "A3", 60);
        completeMap.insertEdge("Porto", "Vila Real", "A4", 100);
        completeMap.insertEdge("Viseu", "Guarda", "A25", 75);
        completeMap.insertEdge("Guarda", "Castelo Branco", "A23", 100);
        completeMap.insertEdge("Aveiro", "Coimbra", "A1", 60);
        completeMap.insertEdge("Coimbra", "Lisboa", "A1", 200);
        completeMap.insertEdge("Coimbra", "Leiria", "A34", 80);
        completeMap.insertEdge("Aveiro", "Leiria", "A17", 120);
        completeMap.insertEdge("Leiria", "Lisboa", "A8", 150);

        completeMap.insertEdge("Aveiro", "Viseu", "A25", 85);
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        completeMap.insertEdge("Lisboa", "Faro", "A2", 280);

        incompleteMap = completeMap.clone();

        incompleteMap.removeEdge("Aveiro", "Viseu");
        incompleteMap.removeEdge("Leiria", "Castelo Branco");
        incompleteMap.removeEdge("Lisboa", "Faro");
    }

    /**
     * Test of allPaths method, of class GraphAlgorithms.
     */
    @Test
    public void testAllPaths() {
        System.out.println("Test of all paths");

        setUp();

        ArrayList<LinkedList<String>> paths = new ArrayList<LinkedList<String>>();

        paths = GraphAlgorithms.allPaths(completeMap, "Porto", "LX");
        assertTrue(paths.size() == 0, "There should not be paths if vertex does not exist");

        paths = GraphAlgorithms.allPaths(incompleteMap, "Porto", "Lisboa");
        assertTrue(paths.size() == 4, "There should be 4 paths");

        paths = GraphAlgorithms.allPaths(incompleteMap, "Porto", "Faro");
        assertTrue(paths.size() == 0, "There should not be paths between Porto and Faro in the incomplete map");
    }

    /**
     * Test of shortestPath method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPath() {
        System.out.println("Test of shortest path");

        setUp();

        LinkedList<String> shortPath = new LinkedList<String>();
        double lenpath = 0;
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "LX", shortPath);
        assertTrue(lenpath == 0, "Length path should be 0 if vertex does not exist");

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Porto", "Faro", shortPath);
        assertTrue(lenpath == 0, "Length path should be 0 if there is no path");

        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Porto", shortPath);
        assertTrue(shortPath.size() == 1, "Number of nodes should be 1 if source and vertex are the same");

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Porto", "Lisboa", shortPath);
        assertTrue(lenpath == 335, "Path between Porto and Lisboa should be 335 Km");

        Iterator<String> it = shortPath.iterator();

        assertTrue(it.next().compareTo("Porto") == 0, "First in path should be Porto");
        assertTrue(it.next().compareTo("Aveiro") == 0, "then Aveiro");
        assertTrue(it.next().compareTo("Coimbra") == 0, "then Coimbra");
        assertTrue(it.next().compareTo("Lisboa") == 0, "then Lisboa");

        lenpath = GraphAlgorithms.shortestPath(incompleteMap, "Braga", "Leiria", shortPath);
        assertTrue(lenpath == 255, "Path between Braga and Leiria should be 255 Km");

        it = shortPath.iterator();

        assertTrue(it.next().compareTo("Braga") == 0, "First in path should be Braga");
        assertTrue(it.next().compareTo("Porto") == 0, "then Porto");
        assertTrue(it.next().compareTo("Aveiro") == 0, "then Aveiro");
        assertTrue(it.next().compareTo("Leiria") == 0, "then Leiria");

        shortPath.clear();
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        assertTrue(lenpath == 335, "Path between Porto and Castelo Branco should be 335 Km");
        assertTrue(shortPath.size() == 5, "N. cities between Porto and Castelo Branco should be 5 ");

        it = shortPath.iterator();

        assertTrue(it.next().compareTo("Porto") == 0, "First in path should be Porto");
        assertTrue(it.next().compareTo("Aveiro") == 0, "then Aveiro");
        assertTrue(it.next().compareTo("Viseu") == 0, "then Viseu");
        assertTrue(it.next().compareTo("Guarda") == 0, "then Guarda");
        assertTrue(it.next().compareTo("Castelo Branco") == 0, "then Castelo Branco");

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco 
        //should change shortest path between Porto and Castelo Branco
        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        shortPath.clear();
        lenpath = GraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        assertTrue(lenpath == 365, "Path between Porto and Castelo Branco should now be 365 Km");
        assertTrue(shortPath.size() == 4, "Path between Porto and Castelo Branco should be 4 cities");

        it = shortPath.iterator();

        assertTrue(it.next().compareTo("Porto") == 0, "First in path should be Porto");
        assertTrue(it.next().compareTo("Aveiro") == 0, "then Aveiro");
        assertTrue(it.next().compareTo("Leiria") == 0, "then Leiria");
        assertTrue(it.next().compareTo("Castelo Branco") == 0, "then Castelo Branco");

    }

    /**
     * Test of fillGraph method, of class GraphAlgorithms.
     */
    @Test
    public void testFillGraph() {

        System.out.println("fillGraph");
        Graph<Address, Path> g = new Graph<>(true);

        Address a1 = new Address("Test", 0, 0, 0);
        Address a2 = new Address("Test2", 0, 0, 0);
        Path p = new Path(a1, a2, 0);

        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();

        la.add(a1);
        la.add(a2);
        lp.add(p);
        GraphAlgorithms.fillGraph(g, la, lp);

        Address a3 = new Address("Test3", 0, 0, 0);

        Path p2 = new Path(a1, a3, 0);
        List<Path> lp2 = new ArrayList<>();
        lp2.add(p2);
        GraphAlgorithms.fillGraph(g, la, lp2);

        g = new Graph<>(true);
        Path p3 = new Path(a3, a2, 0);
        List<Path> lp3 = new ArrayList<>();
        lp3.add(p3);
        GraphAlgorithms.fillGraph(g, la, lp3);

        g = new Graph<>(true);
        Address a4 = new Address("Test4", 0, 0, 0);

        Path p4 = new Path(a3, a4, 0);
        List<Path> lp4 = new ArrayList<>();
        lp4.add(p4);
        GraphAlgorithms.fillGraph(g, la, lp4);

        lp = new ArrayList<>();
        GraphAlgorithms.fillGraph(g, la, lp);

        la = new ArrayList<>();
        GraphAlgorithms.fillGraph(g, la, lp);

        lp = null;
        GraphAlgorithms.fillGraph(g, la, lp);

        la = null;
        GraphAlgorithms.fillGraph(g, la, lp);
    }

    /**
     * Test of writePathsToFile method, of class GraphAlgorithms.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testWritePathsToFile() throws Exception {

        System.out.println("writePathsToFile");
        String fileName = "pathTest.csv";
        int nrPaths = 3;

        Graph<Address, Path> g = new Graph<>(true);
        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);
        la.add(a1);
        la.add(a2);
        la.add(a3);
        lp.add(new Path(a1, a2, 0));
        lp.add(new Path(a2, a3, 0));
        lp.add(new Path(a2, a1, 0));
        GraphAlgorithms.fillGraph(g, la, lp);

        LinkedList<Address> lla = new LinkedList<>();
        lla.add(a1);
        lla.add(a2);
        lla.add(a3);

        LinkedList<Address> lla2 = new LinkedList<>();
        lla2.add(a1);
        lla2.add(a2);
        lla2.add(a1);

        List<LinkedList<Address>> llla = new ArrayList<>();
        llla.add(lla);
        llla.add(lla2);

        Courier c = new Courier("TestMail", "TestPass", "Name", 0, 0, 0, 80.0);
        Vehicle v = new Vehicle(0, 0, 130.0, 1.1, 1, 250, 500, 500);
        List<Product> lpro = new ArrayList<>();

        int expResult = 2;
        int result = GraphAlgorithms.writePathsToFile(fileName, nrPaths, llla, g, c, v, lpro);
        assertEquals(expResult, result);

        List<String> expResultString = Utils.readFile(fileName);
        GraphAlgorithms.writePathsToFile(fileName, nrPaths, llla, g, c, v, lpro);
        List<String> resultString = Utils.readFile(fileName);
        assertEquals(expResultString, resultString);

        nrPaths = 0;
        expResult = 0;
        llla = new LinkedList<>();
        result = GraphAlgorithms.writePathsToFile(fileName, nrPaths, llla, g, c, v, lpro);
        assertEquals(expResult, result);

        llla.add(lla);
        result = GraphAlgorithms.writePathsToFile(fileName, nrPaths, llla, g, c, v, lpro);
        assertEquals(expResult, result);

    }
}
