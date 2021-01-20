package lapr.project.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Drone;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Scooter;
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
        Path p = new Path(a1, a2, 0, 90, 12);

        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();

        la.add(a1);
        la.add(a2);
        lp.add(p);
        GraphAlgorithms.fillGraph(g, la, lp);

        Address a3 = new Address("Test3", 0, 0, 0);

        Path p2 = new Path(a1, a3, 0, 90, 12);
        List<Path> lp2 = new ArrayList<>();
        lp2.add(p2);
        GraphAlgorithms.fillGraph(g, la, lp2);

        g = new Graph<>(true);
        Path p3 = new Path(a3, a2, 0, 90, 12);
        List<Path> lp3 = new ArrayList<>();
        lp3.add(p3);
        GraphAlgorithms.fillGraph(g, la, lp3);

        g = new Graph<>(true);
        Address a4 = new Address("Test4", 0, 0, 0);

        Path p4 = new Path(a3, a4, 0, 90, 12);
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
    public void testWritePathToFile() throws Exception {

        System.out.println("writePathToFile");
        String fileName = "pathTest.csv";

        Graph<Address, Path> g = new Graph<>(true);
        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);
        la.add(a1);
        la.add(a2);
        la.add(a3);
        lp.add(new Path(a1, a2, 0, 90, 12));
        lp.add(new Path(a2, a3, 0, 90, 12));
        lp.add(new Path(a2, a1, 0, 90, 12));
        GraphAlgorithms.fillGraph(g, la, lp);

        LinkedList<Address> lla = new LinkedList<>();
        lla.add(a1);
        lla.add(a2);
        lla.add(a3);

        LinkedList<Address> lla2 = new LinkedList<>();
        lla2.add(a1);
        lla2.add(a2);
        lla2.add(a1);

        Courier c = new Courier("TestMail", "TestPass", "Name", 0, 0, 0, 80.0);
        Scooter s = new Scooter(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1);
        Drone d = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1);
        List<Product> lpro = new ArrayList<>();

        double distance = PathAlgorithms.calcTotalDistance(lla) / 1000;
        double energy = PathAlgorithms.calcScooterTotalEnergy(g, lla, c, s, lpro);

        boolean expResult = true;
        boolean result = GraphAlgorithms.writePathToFile(fileName, lla, distance, energy, s);
        assertEquals(expResult, result);

        expResult = false;
        lla = new LinkedList<>();
        result = GraphAlgorithms.writePathToFile(fileName, lla, distance, energy, s);
        assertEquals(expResult, result);

        fileName = "    [] !!!!!|| ***...";
        result = GraphAlgorithms.writePathToFile(fileName, lla2, distance, energy, s);
        assertEquals(expResult, result);
    }

    @Test
    public void testWritePathToFile2() throws Exception {

        System.out.println("writePathToFile2");
        String fileName = "pathTest.csv";

        Graph<Address, Path> g = new Graph<>(false);
        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);
        la.add(a1);
        la.add(a2);
        la.add(a3);
        lp.add(new Path(a1, a2, 0, 90, 12));
        lp.add(new Path(a2, a3, 0, 90, 12));
        GraphAlgorithms.fillGraph(g, la, lp);

        LinkedList<Address> lla = new LinkedList<>();
        lla.add(a1);
        lla.add(a2);
        lla.add(a3);

        Drone d = new Drone(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9, 1);
        List<Product> lpro = new ArrayList<>();

        double distance = PathAlgorithms.calcTotalDistance(lla) / 1000;
        double energy = PathAlgorithms.calcDroneTotalEnergy(g, lla, d, lpro);

        List<String> expResult = new ArrayList<>();
        expResult.add("Vehicle of ID 1");
        expResult.add("Total Distance = 1.91km.");
        expResult.add("Total Energy Consumption = 42.27W.h.");
        expResult.add("Path Structure");
        expResult.add("casa da musica;conservatorio;trindade;");

        GraphAlgorithms.writePathToFile(fileName, lla, distance, energy, d);
        List<String> result = Utils.readFile(fileName);

        assertEquals(expResult, result);
    }

    /**
     * Test of getShortestPathThroughNodes method, of class GraphAlgorithms.
     */
    @Test
    public void testGetShortestPathThroughNodes() {

        System.out.println("getShortestPathThroughNodes");

        Graph<Address, Path> g = new Graph<>(true);
        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("isep", 41.178333, 8.606389, 103);
        Address a4 = new Address("feup", 41.1775, 8.598056, 111);
        Address a5 = new Address("trindade", 41.151667, 8.609444, 86);
        Address a6 = new Address("torre dos clerigos", 41.145833, 8.613889, 74);
        Address a7 = new Address("se do porto", 41.143056, 8.611111, 72);
        Address a8 = new Address("el corte ingles", 41.125556, 8.605278, 99);
        Address a9 = new Address("parque de serralves", 41.159722, 8.659722, 60);
        Address a10 = new Address("pavilhao rosa mota", 41.148333, 8.625278, 72);
        Address a11 = new Address("estadio do bessa", 41.162222, 8.643333, 66);

        la.add(a1);
        la.add(a2);
        la.add(a3);
        la.add(a4);
        la.add(a5);
        la.add(a6);
        la.add(a7);
        la.add(a8);
        la.add(a9);
        la.add(a10);
        la.add(a11);

        Path p1 = new Path(a1, a2, 0, 90, 12);
        Path p2 = new Path(a2, a1, 0, 90, 12);
        Path p3 = new Path(a1, a11, 0, 90, 12);
        Path p4 = new Path(a11, a9, 0, 90, 12);
        Path p5 = new Path(a9, a1, 0, 90, 12);
        Path p6 = new Path(a1, a10, 0, 90, 12);
        Path p7 = new Path(a10, a1, 0, 90, 12);
        Path p8 = new Path(a2, a5, 0, 90, 12);
        Path p9 = new Path(a5, a2, 0, 90, 12);
        Path p10 = new Path(a10, a6, 0, 90, 12);
        Path p11 = new Path(a6, a10, 0, 90, 12);
        Path p12 = new Path(a2, a6, 0, 90, 12);
        Path p13 = new Path(a6, a2, 0, 90, 12);
        Path p14 = new Path(a6, a7, 0, 90, 12);
        Path p15 = new Path(a7, a6, 0, 90, 12);
        Path p16 = new Path(a7, a8, 0, 90, 12);
        Path p17 = new Path(a8, a7, 0, 90, 12);
        Path p18 = new Path(a5, a3, 0, 90, 12);
        Path p19 = new Path(a3, a4, 0, 90, 12);
        Path p20 = new Path(a4, a5, 0, 90, 12);

        lp.add(p1);
        lp.add(p2);
        lp.add(p3);
        lp.add(p4);
        lp.add(p5);
        lp.add(p6);
        lp.add(p7);
        lp.add(p8);
        lp.add(p9);
        lp.add(p10);
        lp.add(p11);
        lp.add(p12);
        lp.add(p13);
        lp.add(p14);
        lp.add(p15);
        lp.add(p16);
        lp.add(p17);
        lp.add(p18);
        lp.add(p19);
        lp.add(p20);

        GraphAlgorithms.fillGraph(g, la, lp);

        List<Address> lNodes = new ArrayList<>();

        lNodes.add(a3);
        lNodes.add(a5);
        lNodes.add(a7);
        lNodes.add(a9);

        LinkedList<Address> path = new LinkedList<>();

        Address aOrig = a1;
        Address aDest = a11;

        double expResult = 20122.97;
        double result = GraphAlgorithms.getShortestPathThroughNodes(g, lNodes, path, aOrig, aDest);
        assertEquals(expResult, result, 1);

        LinkedList<Address> expPath = new LinkedList<>();
        expPath.add(a1);
        expPath.add(a2);
        expPath.add(a5);
        expPath.add(a3);
        expPath.add(a4);
        expPath.add(a5);
        expPath.add(a2);
        expPath.add(a6);
        expPath.add(a7);
        expPath.add(a6);
        expPath.add(a2);
        expPath.add(a1);
        expPath.add(a11);
        expPath.add(a9);
        expPath.add(a1);
        expPath.add(a11);
        assertEquals(expPath, path);
    }

    @Test
    public void testGetShortestPathThroughNodes2() {

        System.out.println("getShortestPathThroughNodes2");

        Graph<Address, Path> g = new Graph<>(true);
        assertEquals(0.0d, GraphAlgorithms.getShortestPathThroughNodes(g, new ArrayList<>(), new LinkedList<>(), new Address("", 0, 0, 0), new Address("", 0, 0, 0)));
    }

    @Test
    public void testGetShortestPathThroughNodes3() {

        System.out.println("getShortestPathThroughNodes3");

        List<Address> lNodes = new ArrayList<>();
        lNodes.add(new Address("", 0, 0, 0));
        assertEquals(0.0d, GraphAlgorithms.getShortestPathThroughNodes(new Graph<>(true), lNodes, new LinkedList<>(), new Address("", 0, 0, 0), new Address("", 0, 0, 0)));
    }

    @Test
    public void testGetShortestPathThroughNodes4() {

        System.out.println("getShortestPathThroughNodes4");

        assertEquals(0.0d, GraphAlgorithms.getShortestPathThroughNodes(null, new ArrayList<>(), new LinkedList<>(), new Address("", 0, 0, 0), new Address("", 0, 0, 0)));
    }

    @Test
    public void testGenerateCombinations() {

        System.out.println("generateCombinations");

        Address a1 = new Address("ali", 0, 0, 0);
        Address a2 = new Address("aqui", 0, 0, 0);
        Address a3 = new Address("aculoutro", 0, 0, 0);
        Address a4 = new Address("adiante", 0, 0, 0);
        Address a5 = new Address("hmm", 0, 0, 0);
        Address a6 = new Address("hhmmmmmmm", 0, 0, 0);

        List<Address> lNodes = new ArrayList<>();
        lNodes.add(a2);
        lNodes.add(a3);
        lNodes.add(a4);
        lNodes.add(a5);

        List<LinkedList<Address>> combos = new ArrayList<>();

        GraphAlgorithms.generateCombinations(lNodes.size(), lNodes, combos, a1, a6);

        lNodes = new ArrayList<>();
        lNodes.add(a2);
        lNodes.add(a3);
        lNodes.add(a4);
        lNodes.add(a5);

        List<LinkedList<Address>> expCombos = new ArrayList<>();

        GraphAlgorithms.generateCombinations(lNodes.size(), lNodes, expCombos, a1, a6);

        assertEquals(expCombos, combos);
        assertEquals(24, combos.size());
    }

    @Test
    public void testGenerateCombinations2() {

        System.out.println("generateCombinations2");

        List<Address> lNodes = new ArrayList<>();
        List<LinkedList<Address>> combos = new ArrayList<>();

        Address a1 = new Address("ali", 0, 0, 0);
        Address a2 = new Address("aqui", 0, 0, 0);

        GraphAlgorithms.generateCombinations(lNodes.size(), lNodes, combos, a1, a2);

        assertEquals(new ArrayList<>(), combos);
        assertEquals(0, combos.size());
    }

    @Test
    public void testGenerateCombinations3() {

        System.out.println("generateCombinations3");

        Address a1 = new Address("ali", 0, 0, 0);
        Address a2 = new Address("aqui", 0, 0, 0);
        Address a3 = new Address("aculoutro", 0, 0, 0);
        Address a4 = new Address("adiante", 0, 0, 0);

        List<Address> lNodes = new ArrayList<>();
        lNodes.add(a2);
        lNodes.add(a3);

        List<LinkedList<Address>> combos = new ArrayList<>();

        GraphAlgorithms.generateCombinations(lNodes.size(), lNodes, combos, a1, a4);

        List<LinkedList<Address>> expCombos = new ArrayList<>();

        LinkedList<Address> theCombo1 = new LinkedList<>();
        theCombo1.add(a1);
        theCombo1.add(a2);
        theCombo1.add(a3);
        theCombo1.add(a4);
        LinkedList<Address> theCombo2 = new LinkedList<>();
        theCombo2.add(a1);
        theCombo2.add(a3);
        theCombo2.add(a2);
        theCombo2.add(a4);

        expCombos.add(theCombo1);
        expCombos.add(theCombo2);

        assertEquals(expCombos, combos);
        assertEquals(2, combos.size());
    }

    @Test
    public void testMergeLinkedLists() {

        System.out.println("mergeLinkedLists");

        Address a1 = new Address("ali", 0, 0, 0);
        Address a2 = new Address("aqui", 0, 0, 0);
        Address a3 = new Address("aculoutro", 0, 0, 0);

        LinkedList<Address> lOrig = new LinkedList<>();
        LinkedList<Address> lAddon = new LinkedList<>();

        lOrig.add(a1);
        lOrig.add(a2);

        lAddon.add(a3);
        lAddon.add(a2);

        ArrayStoreException ex = assertThrows(ArrayStoreException.class, () -> GraphAlgorithms.mergeLinkedLists(lOrig, lAddon));
        assertTrue(ex.getMessage().contains("Error merging Linked Lists : Head doesn't match Tail"));
    }

    /**
     * Test of getNearestPharmacy method, of class GraphAlgorithms.
     */
    @Test
    public void testGetNearestPharmacy() {

        System.out.println("testGetNearestPharmacy");

        Graph<Address, Path> g = new Graph<>(true);
        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("isep", 41.178333, 8.606389, 103);
        Address a4 = new Address("feup", 41.1775, 8.598056, 111);
        Address a5 = new Address("trindade", 41.151667, 8.609444, 86);
        Address a6 = new Address("torre dos clerigos", 41.145833, 8.613889, 74);
        Address a7 = new Address("se do porto", 41.143056, 8.611111, 72);
        Address a8 = new Address("el corte ingles", 41.125556, 8.605278, 99);
        Address a9 = new Address("parque de serralves", 41.159722, 8.659722, 60);
        Address a10 = new Address("pavilhao rosa mota", 41.148333, 8.625278, 72);
        Address a11 = new Address("estadio do bessa", 41.162222, 8.643333, 66);

        la.add(a1);
        la.add(a2);
        la.add(a3);
        la.add(a4);
        la.add(a5);
        la.add(a6);
        la.add(a7);
        la.add(a8);
        la.add(a9);
        la.add(a10);
        la.add(a11);

        Path p1 = new Path(a1, a2, 0, 90, 12);
        Path p2 = new Path(a2, a1, 0, 90, 12);
        Path p3 = new Path(a1, a11, 0, 90, 12);
        Path p4 = new Path(a11, a9, 0, 90, 12);
        Path p5 = new Path(a9, a1, 0, 90, 12);
        Path p6 = new Path(a1, a10, 0, 90, 12);
        Path p7 = new Path(a10, a1, 0, 90, 12);
        Path p8 = new Path(a2, a5, 0, 90, 12);
        Path p9 = new Path(a5, a2, 0, 90, 12);
        Path p10 = new Path(a10, a6, 0, 90, 12);
        Path p11 = new Path(a6, a10, 0, 90, 12);
        Path p12 = new Path(a2, a6, 0, 90, 12);
        Path p13 = new Path(a6, a2, 0, 90, 12);
        Path p14 = new Path(a6, a7, 0, 90, 12);
        Path p15 = new Path(a7, a6, 0, 90, 12);
        Path p16 = new Path(a7, a8, 0, 90, 12);
        Path p17 = new Path(a8, a7, 0, 90, 12);
        Path p18 = new Path(a5, a3, 0, 90, 12);
        Path p19 = new Path(a3, a4, 0, 90, 12);
        Path p20 = new Path(a4, a5, 0, 90, 12);

        lp.add(p1);
        lp.add(p2);
        lp.add(p3);
        lp.add(p4);
        lp.add(p5);
        lp.add(p6);
        lp.add(p7);
        lp.add(p8);
        lp.add(p9);
        lp.add(p10);
        lp.add(p11);
        lp.add(p12);
        lp.add(p13);
        lp.add(p14);
        lp.add(p15);
        lp.add(p16);
        lp.add(p17);
        lp.add(p18);
        lp.add(p19);
        lp.add(p20);

        GraphAlgorithms.fillGraph(g, la, lp);

        List<Address> lad = new ArrayList<>();

        lad.add(a4);
        lad.add(a8);
        lad.add(a9);

        Address aOrig = a2;

        Address expResult = a9;
        Address result = GraphAlgorithms.getNearestPharmacy(g, aOrig, lad);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNearestPharmacy2() {

        System.out.println("testGetNearestPharmacy2");

        Address aOrig = new Address("Test", 0.0d, 0.0d, 0.0d);
        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);

        List<Address> lad = new ArrayList<>();
        lad.add(a1);
        lad.add(a2);

        Address expResult = null;
        Address result = GraphAlgorithms.getNearestPharmacy(null, aOrig, lad);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNearestPharmacy3() {

        System.out.println("testGetNearestPharmacy3");

        Address aOrig = new Address("Test", 0.0d, 0.0d, 0.0d);

        List<Address> lad = new ArrayList<>();

        Address expResult = null;
        Address result = GraphAlgorithms.getNearestPharmacy(null, aOrig, lad);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNearestPharmacy4() {

        System.out.println("testGetNearestPharmacy4");

        Address aOrig = new Address("Test", 0.0d, 0.0d, 0.0d);

        Address expResult = null;
        Address result = GraphAlgorithms.getNearestPharmacy(null, aOrig, new ArrayList<>());
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNearestPharmacy5() {

        System.out.println("testGetNearestPharmacy5");
        Address aOrig = new Address("Test", 0.0d, 0.0d, 0.0d);

        Graph<Address, Path> g = new Graph<>(true);
        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("isep", 41.178333, 8.606389, 103);
        Address a4 = new Address("feup", 41.1775, 8.598056, 111);
        Address a5 = new Address("trindade", 41.151667, 8.609444, 86);
        Address a6 = new Address("torre dos clerigos", 41.145833, 8.613889, 74);
        Address a7 = new Address("se do porto", 41.143056, 8.611111, 72);
        Address a8 = new Address("el corte ingles", 41.125556, 8.605278, 99);
        Address a9 = new Address("parque de serralves", 41.159722, 8.659722, 60);
        Address a10 = new Address("pavilhao rosa mota", 41.148333, 8.625278, 72);
        Address a11 = new Address("estadio do bessa", 41.162222, 8.643333, 66);

        la.add(a1);
        la.add(a2);
        la.add(a3);
        la.add(a4);
        la.add(a5);
        la.add(a6);
        la.add(a7);
        la.add(a8);
        la.add(a9);
        la.add(a10);
        la.add(a11);

        Path p1 = new Path(a1, a2, 0, 90, 12);
        Path p2 = new Path(a2, a1, 0, 90, 12);
        Path p3 = new Path(a1, a11, 0, 90, 12);
        Path p4 = new Path(a11, a9, 0, 90, 12);
        Path p5 = new Path(a9, a1, 0, 90, 12);
        Path p6 = new Path(a1, a10, 0, 90, 12);
        Path p7 = new Path(a10, a1, 0, 90, 12);
        Path p8 = new Path(a2, a5, 0, 90, 12);
        Path p9 = new Path(a5, a2, 0, 90, 12);
        Path p10 = new Path(a10, a6, 0, 90, 12);
        Path p11 = new Path(a6, a10, 0, 90, 12);
        Path p12 = new Path(a2, a6, 0, 90, 12);
        Path p13 = new Path(a6, a2, 0, 90, 12);
        Path p14 = new Path(a6, a7, 0, 90, 12);
        Path p15 = new Path(a7, a6, 0, 90, 12);
        Path p16 = new Path(a7, a8, 0, 90, 12);
        Path p17 = new Path(a8, a7, 0, 90, 12);
        Path p18 = new Path(a5, a3, 0, 90, 12);
        Path p19 = new Path(a3, a4, 0, 90, 12);
        Path p20 = new Path(a4, a5, 0, 90, 12);

        lp.add(p1);
        lp.add(p2);
        lp.add(p3);
        lp.add(p4);
        lp.add(p5);
        lp.add(p6);
        lp.add(p7);
        lp.add(p8);
        lp.add(p9);
        lp.add(p10);
        lp.add(p11);
        lp.add(p12);
        lp.add(p13);
        lp.add(p14);
        lp.add(p15);
        lp.add(p16);
        lp.add(p17);
        lp.add(p18);
        lp.add(p19);
        lp.add(p20);

        GraphAlgorithms.fillGraph(g, la, lp);

        List<Address> lad = new ArrayList<>();

        lad.add(a4);
        lad.add(a9);

        Address expResult = null;
        Address result = GraphAlgorithms.getNearestPharmacy(g, aOrig, lad);
        assertEquals(expResult, result);
    }
}
