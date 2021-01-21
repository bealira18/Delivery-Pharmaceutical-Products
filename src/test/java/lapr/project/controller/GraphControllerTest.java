package lapr.project.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.Path;
import lapr.project.model.Vehicle;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphControllerTest {

    private static GraphController gCont;

    public GraphControllerTest() {
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

        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();
        Graph<Address, Path> g = new Graph<>(true);

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
        gCont.fillGraphScooter(la, lp);

        assertEquals(g, gCont.getGraphScooter());
    }

    /**
     * Test of getGraphDrone method, of class GraphController.
     */
    @Test
    public void testGetGraphDrone() {

        System.out.println("getGraphDrone");
        gCont = new GraphController();
        Graph<Address, Path> expResult = new Graph<>(true);
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

        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();
        Graph<Address, Path> g = new Graph<>(true);

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
        gCont.fillGraphDrone(la, lp);

        assertEquals(g, gCont.getGraphDrone());
    }

    /**
     * Test of getNearestPharmacy method, of class GraphController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetNearestPharmacy() throws Exception {

        System.out.println("getNearestPharmacy");

        gCont = new GraphController();
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

        gCont.fillGraphDrone(la, lp);

        List<Address> lad = new ArrayList<>();

        lad.add(a4);
        lad.add(a8);
        lad.add(a9);

        Address aOrig = a2;

        Address expResult = a9;
        Address result = gCont.getNearestPharmacy(aOrig, lad);
        assertEquals(expResult, result);
    }

    /**
     * Test of getShortestPath method, of class GraphController.
     */
    @Test
    public void testGetShortestPath() {

        System.out.println("getShortestPath");
        boolean scooterOrDrone = true;
        gCont = new GraphController();
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

        gCont.fillGraphScooter(la, lp);

        LinkedList<Address> shortPath = new LinkedList<>();
        double expResult = 1165.76;
        double result = gCont.getShortestPath(scooterOrDrone, a1, a11, shortPath);
        assertEquals(expResult, result, 1);

        gCont.fillGraphDrone(la, lp);

        shortPath = new LinkedList<>();
        scooterOrDrone = false;
        expResult = 1165.76;
        result = gCont.getShortestPath(scooterOrDrone, a1, a11, shortPath);
        assertEquals(expResult, result, 1);
    }

    /**
     * Test of getShortestPathThroughNodes method, of class GraphController.
     */
    @Test
    public void testGetShortestPathThroughNodes() {

        System.out.println("getShortestPathThroughNodes");
        boolean scooterOrDrone = true;
        gCont = new GraphController();
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

        gCont.fillGraphScooter(la, lp);

        List<Address> lNodes = new ArrayList<>();

        lNodes.add(a3);
        lNodes.add(a7);

        LinkedList<Address> path = new LinkedList<>();

        Address aOrig = a1;
        Address aDest = a2;

        double expResult = 13256.04;
        double result = gCont.getShortestPathThroughNodes(scooterOrDrone, aOrig, aDest, lNodes, path);
        assertEquals(expResult, result, 1);

        scooterOrDrone = false;
        gCont.fillGraphDrone(la, lp);
        expResult = 13256.04;
        result = gCont.getShortestPathThroughNodes(scooterOrDrone, aOrig, aDest, lNodes, path);
        assertEquals(expResult, result, 1);
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
        GraphController instance = new GraphController();
        boolean expResult = true;
        boolean result = instance.writePathToFile(fileName, la, distance, energy, "Vehicle");
        assertEquals(expResult, result);

        fileName = "";
        expResult = false;
        result = instance.writePathToFile(fileName, la, distance, energy, "Vehicle");
        assertEquals(expResult, result);
    }

    /**
     * Test of getGraphScooterEnergy method, of class GraphController.
     */
    @Test
    public void testGetGraphScooterEnergy() {

        System.out.println("getGraphScooterEnergy");
        gCont = new GraphController();
        Graph<Address, Path> expResult = new Graph<>(true);
        Graph<Address, Path> result = gCont.getGraphScooterEnergy();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGraphDroneEnergy method, of class GraphController.
     */
    @Test
    public void testGetGraphDroneEnergy() {

        System.out.println("getGraphDroneEnergy");
        gCont = new GraphController();
        Graph<Address, Path> expResult = new Graph<>(true);
        Graph<Address, Path> result = gCont.getGraphDroneEnergy();
        assertEquals(expResult, result);
    }

    /**
     * Test of fillGraphScooterEnergy method, of class GraphController.
     */
    @Test
    public void testFillGraphScooterEnergy() {

        System.out.println("fillGraphScooterEnergy");
        gCont = new GraphController();

        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();
        Graph<Address, Path> g = new Graph<>(true);

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);
        la.add(a1);
        la.add(a2);
        la.add(a3);
        lp.add(new Path(a1, a2, 0, 90, 12));
        lp.add(new Path(a2, a3, 0, 90, 12));
        lp.add(new Path(a2, a1, 0, 90, 12));

        GraphAlgorithms.fillGraphEnergy(true, g, la, lp);
        gCont.fillGraphScooterEnergy(la, lp);

        assertEquals(g, gCont.getGraphScooterEnergy());
    }

    /**
     * Test of fillGraphDroneEnergy method, of class GraphController.
     */
    @Test
    public void testFillGraphDroneEnergy() {

        System.out.println("fillGraphDroneEnergy");
        gCont = new GraphController();

        List<Address> la = new ArrayList<>();
        List<Path> lp = new ArrayList<>();
        Graph<Address, Path> g = new Graph<>(true);

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);
        la.add(a1);
        la.add(a2);
        la.add(a3);
        lp.add(new Path(a1, a2, 0, 90, 12));
        lp.add(new Path(a2, a3, 0, 90, 12));
        lp.add(new Path(a2, a1, 0, 90, 12));

        GraphAlgorithms.fillGraphEnergy(false, g, la, lp);
        gCont.fillGraphDroneEnergy(la, lp);

        assertEquals(g, gCont.getGraphDroneEnergy());
    }

    /**
     * Test of getShortestPathEnergy method, of class GraphController.
     */
    @Test
    public void testGetShortestPathEnergy() {

        System.out.println("getShortestPathEnergy");
        boolean scooterOrDrone = true;
        gCont = new GraphController();
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

        gCont.fillGraphScooterEnergy(la, lp);

        LinkedList<Address> shortPath = new LinkedList<>();
        double expResult = 4.52594775871151;
        double result = gCont.getShortestPathEnergy(scooterOrDrone, a1, a11, shortPath);
        assertEquals(expResult, result, 0);

        gCont.fillGraphDroneEnergy(la, lp);

        shortPath = new LinkedList<>();
        scooterOrDrone = false;
        expResult = 23.38608343497005;
        result = gCont.getShortestPathEnergy(scooterOrDrone, a1, a11, shortPath);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getShortestPathThroughNodesEnergy method, of class
     * GraphController.
     */
    @Test
    public void testGetShortestPathThroughNodesEnergy() {

        System.out.println("getShortestPathThroughNodesEnergy");
        boolean scooterOrDrone = true;
        gCont = new GraphController();
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

        gCont.fillGraphScooterEnergy(la, lp);

        List<Address> lNodes = new ArrayList<>();

        lNodes.add(a3);
        lNodes.add(a7);

        LinkedList<Address> path = new LinkedList<>();

        Address aOrig = a1;
        Address aDest = a2;

        double expResult = 95.29854083854472;
        double result = gCont.getShortestPathThroughNodesEnergy(scooterOrDrone, aOrig, aDest, lNodes, path);
        assertEquals(expResult, result, 0);

        scooterOrDrone = false;
        path = new LinkedList<>();
        gCont.fillGraphDroneEnergy(la, lp);
        expResult = 264.89236813841075;
        result = gCont.getShortestPathThroughNodesEnergy(scooterOrDrone, aOrig, aDest, lNodes, path);
        assertEquals(expResult, result, 0);
    }
}
