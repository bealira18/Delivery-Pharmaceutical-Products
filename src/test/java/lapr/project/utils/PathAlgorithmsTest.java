package lapr.project.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Vehicle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathAlgorithmsTest {

    public PathAlgorithmsTest() {
    }

    /**
     * Test of calcDistance method, of class PathAlgorithms.
     */
    @Test
    public void testCalcDistance() {

        System.out.println("calcDistance");
        Address a1 = new Address("Test1", 60.203, 69.201, 3);
        Address a2 = new Address("Test2", 69.203, 67.201, 3);

        double expResult = 1004;
        double result = PathAlgorithms.calcDistance(a1, a2);
        assertEquals(expResult, result, 4);

        a1 = new Address("Test1", 69.203, 69.201, 3);
        a2 = new Address("Test2", 69.203, 67.201, 3);

        expResult = 78.95;
        result = PathAlgorithms.calcDistance(a1, a2);
        assertEquals(expResult, result, 4);

        a1 = new Address("Test1", 60.203, 69.201, 3);
        a2 = new Address("Test2", 69.203, 69.201, 3);

        expResult = 1000.75;
        result = PathAlgorithms.calcDistance(a1, a2);
        assertEquals(expResult, result, 4);

        a1 = new Address("Test1", 69.203, 67.203, 3);
        a2 = new Address("Test2", 69.203, 69.201, 3);

        expResult = 78.87;
        result = PathAlgorithms.calcDistance(a1, a2);
        assertEquals(expResult, result, 4);

        a1 = new Address("Test1", 60.203, 69.201, -3);
        a2 = new Address("Test2", 69.203, 67.201, -2);

        expResult = 1005.12;
        result = PathAlgorithms.calcDistance(a1, a2);
        assertEquals(expResult, result, 4);

        a1 = new Address("Test1", 69.203, 69.201, 2);
        a2 = new Address("Test2", 69.203, 69.201, 3);

        expResult = 0;
        result = PathAlgorithms.calcDistance(a1, a2);
        assertEquals(expResult, result, 4);

        a1 = new Address("Test1", 69.203, 69.201, 3);
        a2 = new Address("Test2", 69.203, 69.201, 3);

        expResult = 0;
        result = PathAlgorithms.calcDistance(a1, a2);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcEnergy() {

        System.out.println("calcEnergy");
        Address a1 = new Address("Test1", 45, 45, 0);
        Address a2 = new Address("Test2", 46, 48.7749, 0);
        Path p = new Path(a1, a2, 0.7);
        Courier c = new Courier("TestMail", "TestPass", "TestName", 0, 0, 1, 100);
        Vehicle v = new Vehicle(1, 1, 80, 1.5, 1, 250, 20, 30);
        List<Product> lp = new ArrayList<>();

        double expResult = 134.04;
        double result = PathAlgorithms.calcEnergy(p, c, v, lp);
        assertEquals(expResult, result, 1);

        p = new Path(a1, a2, 0.7);
        c = new Courier("TestMail", "TestPass", "TestName", 0, 0, 1, 100);
        v = new Vehicle(1, 1, -80, 1.5, 1, 250, 20, 30);

        expResult = 38.16;
        result = PathAlgorithms.calcEnergy(p, c, v, lp);
        assertEquals(expResult, result, 1);

        lp.add(new Product(0, "Test", 0.0, -10, 0));

        expResult = 32.17;
        result = PathAlgorithms.calcEnergy(p, c, v, lp);
        assertEquals(expResult, result, 1);

        v.setAerodynamicCoeficient(0.25);
        v.setFrontalArea(0.15);

        expResult = 6.64;
        result = PathAlgorithms.calcEnergy(p, c, v, lp);
        assertEquals(expResult, result, 1);

        a1 = new Address("Test1", 45, 45, 0);
        a2 = new Address("Test2", 45, 45, 0);
        p = new Path(a1, a2, 0.7);

        expResult = 0;
        result = PathAlgorithms.calcEnergy(p, c, v, lp);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcTotalDistance method, of class PathAlgorithms.
     */
    @Test
    public void testCalcTotalDistance() {

        System.out.println("calcTotalDistance");

        LinkedList<Address> la = new LinkedList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.158056, 8.630556, 83);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);

        la.add(a1);
        la.add(a2);
        la.add(a3);

        double expResult = 1.90;
        double result = PathAlgorithms.calcTotalDistance(la);
        assertEquals(expResult, result, 2);
        
        la = new LinkedList<>();
        
        expResult = 0;
        result = PathAlgorithms.calcTotalDistance(la);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcTotalEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcTotalEnergy() {

        System.out.println("calcTotalEnergy");

        Graph<Address, Path> g = new Graph<>(true);

        List<Address> la = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.158056, 8.630556, 83);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);

        la.add(a1);
        la.add(a2);
        la.add(a3);

        List<Path> lp = new ArrayList<>();

        lp.add(new Path(a1, a2, 0));
        lp.add(new Path(a2, a3, 0));

        GraphAlgorithms.fillGraph(g, la, lp);

        LinkedList<Address> lla = new LinkedList<>();

        lla.add(a1);
        lla.add(a2);
        lla.add(a3);

        Courier c = new Courier("TestMail", "TestPass", "Name", 0, 0, 0, 80.0);
        Vehicle v = new Vehicle(0, 0, 130.0, 1.1, 1, 250, 500, 500);
        List<Product> lpro = new ArrayList<>();
        double expResult = 0.116;
        double result = PathAlgorithms.calcTotalEnergy(g, lla, c, v, lpro);
        assertEquals(expResult, result, 1);
        
        lla = new LinkedList<>();
        
        expResult = 0;
        result = PathAlgorithms.calcTotalEnergy(g, lla, c, v, lpro);
        assertEquals(expResult, result, 0);
    }
}
