package lapr.project.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Drone;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Scooter;
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

        double expResult = 1005122.21;
        double result = PathAlgorithms.calcDistance(a1, a2);
        assertEquals(expResult, result, 4);

        a1 = new Address("Test1", 69.203, 69.201, 3);
        a2 = new Address("Test2", 69.203, 67.201, 3);

        expResult = 78957.79;
        result = PathAlgorithms.calcDistance(a1, a2);
        assertEquals(expResult, result, 4);

        a1 = new Address("Test1", 60.203, 69.201, 3);
        a2 = new Address("Test2", 69.203, 69.201, 3);

        expResult = 1000754.33;
        result = PathAlgorithms.calcDistance(a1, a2);
        assertEquals(expResult, result, 4);

        a1 = new Address("Test1", 69.203, 67.203, 3);
        a2 = new Address("Test2", 69.203, 69.201, 3);

        expResult = 78878.84;
        result = PathAlgorithms.calcDistance(a1, a2);
        assertEquals(expResult, result, 4);

        a1 = new Address("Test1", 60.203, 69.201, -3);
        a2 = new Address("Test2", 69.203, 67.201, -2);

        expResult = 1005122.21;
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
     * Test of calcScooterEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcScooterEnergy() {

        System.out.println("calcScooterEnergy");
        Address a1 = new Address("Test1", 45, 45, 23);
        Address a2 = new Address("Test2", 46, 48.7749, 890);
        Path p = new Path(a1, a2, 0.7, 90, 12);
        Courier c = new Courier("TestMail", "TestPass", "TestName", 0, 0, 1, 80);
        List<Product> lp = new ArrayList<>();

        double expResult = 62192.8232407537;
        double result = PathAlgorithms.calcScooterEnergy(p, c, lp);
        assertEquals(expResult, result, 0);

        p = new Path(a1, a2, 0.7, 90, 12);
        c = new Courier("TestMail", "TestPass", "TestName", 0, 0, 1, 100);

        expResult = 74224.87996414013;
        result = PathAlgorithms.calcScooterEnergy(p, c, lp);
        assertEquals(expResult, result, 0);

        lp.add(new Product(0, "Test", 0.0, -10, 0));

        expResult = 68208.85160244691;
        result = PathAlgorithms.calcScooterEnergy(p, c, lp);
        assertEquals(expResult, result, 0);

        a1 = new Address("Test1", 45, 45, 0);
        a2 = new Address("Test2", 45, 45, 0);
        p = new Path(a1, a2, 0.7, 90, 12);

        expResult = 0;
        result = PathAlgorithms.calcScooterEnergy(p, c, lp);
        assertEquals(expResult, result, 0);

        a1 = new Address("Test1", 45, 45, 80);
        a2 = new Address("Test2", 45, 45.01, -120);
        p = new Path(a1, a2, 0, 90, 12);

        expResult = 0;
        result = PathAlgorithms.calcScooterEnergy(p, c, lp);
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
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);

        la.add(a1);
        la.add(a2);
        la.add(a3);

        double expResult = 1905.6519783285971;
        double result = PathAlgorithms.calcTotalDistance(la);
        assertEquals(expResult, result, 0);

        la = new LinkedList<>();

        expResult = 0;
        result = PathAlgorithms.calcTotalDistance(la);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcScooterTotalEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcScooterTotalEnergy() {

        System.out.println("calcScooterTotalEnergy");

        Graph<Address, Path> g = new Graph<>(true);

        List<Address> la = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);

        la.add(a1);
        la.add(a2);
        la.add(a3);

        List<Path> lp = new ArrayList<>();

        lp.add(new Path(a1, a2, 0, 90, 12));
        lp.add(new Path(a2, a3, 0, 90, 12));

        GraphAlgorithms.fillGraph(g, la, lp);

        LinkedList<Address> lla = new LinkedList<>();

        lla.add(a1);
        lla.add(a2);
        lla.add(a3);

        Courier c = new Courier("TestMail", "TestPass", "Name", 0, 0, 0, 80.0);
        List<Product> lpro = new ArrayList<>();
        double expResult = 31.150622467562194;
        double result = PathAlgorithms.calcScooterTotalEnergy(g, lla, c, lpro);
        assertEquals(expResult, result, 0);

        lla = new LinkedList<>();

        expResult = 0.0d;
        result = PathAlgorithms.calcScooterTotalEnergy(g, lla, c, lpro);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcDroneEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcDroneEnergy() {

        System.out.println("calcDroneEnergy");
        Address a1 = new Address("Test1", 45, 45, 0);
        Address a2 = new Address("Test2", 46, 48.7749, 0);
        Path p = new Path(a1, a2, 0.7, 90, 12);
        List<Product> lp = new ArrayList<>();

        double expResult = 6325.03;
        double result = PathAlgorithms.calcDroneEnergy(p, lp);
        assertEquals(expResult, result, 1);

        lp.add(new Product(0, "Test", 0.0, -10, 0));

        expResult = 6325.0361044119;
        result = PathAlgorithms.calcDroneEnergy(p, lp);
        assertEquals(expResult, result, 0);

        a1 = new Address("Test1", 45, 45, 0);
        a2 = new Address("Test2", 45, 45, 0);
        p = new Path(a1, a2, 0.7, 90, 12);

        expResult = 0;
        result = PathAlgorithms.calcDroneEnergy(p, lp);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcTime method, of class PathAlgorithms.
     */
    @Test
    public void testCalcTime() {

        System.out.println("calcTime");
        double distance = 10000;
        double speed = 10;
        int expResult = 1000;
        int result = PathAlgorithms.calcTime(distance, speed);
        assertEquals(expResult, result);

        distance = 0;
        speed = 0;
        result = PathAlgorithms.calcTime(distance, speed);
        assertEquals(0, result);
    }

    /**
     * Test of calcDroneTotalEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcDroneTotalEnergy() {

        System.out.println("calcDroneTotalEnergy");

        Graph<Address, Path> g = new Graph<>(true);

        List<Address> la = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);

        la.add(a1);
        la.add(a2);
        la.add(a3);

        List<Path> lp = new ArrayList<>();

        lp.add(new Path(a1, a2, 0, 90, 12));
        lp.add(new Path(a2, a3, 0, 90, 12));

        GraphAlgorithms.fillGraph(g, la, lp);

        LinkedList<Address> lla = new LinkedList<>();

        lla.add(a1);
        lla.add(a2);
        lla.add(a3);

        List<Product> lpro = new ArrayList<>();
        double expResult = 38.207430834455025;
        double result = PathAlgorithms.calcDroneTotalEnergy(g, lla, lpro);
        assertEquals(expResult, result, 0);

        lpro.add(new Product(1, "Test", 0, 180, 1));

        expResult = 172.24160458590814;
        result = PathAlgorithms.calcDroneTotalEnergy(g, lla, lpro);
        assertEquals(expResult, result, 0);

        lla = new LinkedList<>();

        expResult = 0.0d;
        result = PathAlgorithms.calcDroneTotalEnergy(g, lla, lpro);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testCalcTotalDroneEnergy2() {

        System.out.println("calcDroneTotalEnergy2");

        Graph<Address, Path> g = new Graph<>(true);

        List<Address> la = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 91892);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 2);
        Address a3 = new Address("trindade", 41.151667, 8.609444, -28237);

        la.add(a1);
        la.add(a2);
        la.add(a3);

        List<Path> lp = new ArrayList<>();

        lp.add(new Path(a1, a2, 0, 90, 12));
        lp.add(new Path(a2, a3, 0, 90, 12));

        GraphAlgorithms.fillGraph(g, la, lp);

        LinkedList<Address> lla = new LinkedList<>();

        lla.add(a1);
        lla.add(a2);
        lla.add(a3);

        List<Product> lpro = new ArrayList<>();
        lpro.add(new Product(1, "Test", 0, 999, 1));
        double expResult = 226012.56916245815;
        double result = PathAlgorithms.calcDroneTotalEnergy(g, lla, lpro);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcScooterEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcScooterEnergy_4args() {

        System.out.println("calcScooterEnergy");
        Address a1 = new Address("Test1", 45, 45, 23);
        Address a2 = new Address("Test2", 46, 48.7749, 890);
        Path p = new Path(a1, a2, 0.7, 90, 12);
        Courier c = new Courier("TestMail", "TestPass", "TestName", 0, 0, 1, 80);
        Scooter s = new Scooter(1, 1, 15, 1.1, 0.5, 200, 200, 200, 8.9, 1);
        List<Product> lp = new ArrayList<>();

        double expResult = 59443.430256389234;
        double result = PathAlgorithms.calcScooterEnergy(p, c, s, lp);
        assertEquals(expResult, result, 0);

        p = new Path(a1, a2, 0.7, 90, 12);
        c = new Courier("TestMail", "TestPass", "TestName", 0, 0, 1, 100);

        expResult = 71475.48697977564;
        result = PathAlgorithms.calcScooterEnergy(p, c, s, lp);
        assertEquals(expResult, result, 0);

        lp.add(new Product(0, "Test", 0.0, -10, 0));

        expResult = 65459.45861808243;
        result = PathAlgorithms.calcScooterEnergy(p, c, s, lp);
        assertEquals(expResult, result, 0);

        a1 = new Address("Test1", 45, 45, 0);
        a2 = new Address("Test2", 45, 45, 0);
        p = new Path(a1, a2, 0.7, 90, 12);

        expResult = 0;
        result = PathAlgorithms.calcScooterEnergy(p, c, s, lp);
        assertEquals(expResult, result, 0);

        a1 = new Address("Test1", 45, 45, 80);
        a2 = new Address("Test2", 45, 45.01, -120);
        p = new Path(a1, a2, 0, 90, 12);

        expResult = 0;
        result = PathAlgorithms.calcScooterEnergy(p, c, s, lp);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcDroneEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcDroneEnergy_3args() {

        System.out.println("calcDroneEnergy");
        Address a1 = new Address("Test1", 45, 45, 0);
        Address a2 = new Address("Test2", 46, 48.7749, 0);
        Path p = new Path(a1, a2, 0.7, 90, 12);
        Drone d = new Drone(1, 1, 5, 0.6, 0.4, 200, 200, 200, 22.36, 0.5, 9, 1);
        List<Product> lp = new ArrayList<>();

        double expResult = 6325.0361044119;
        double result = PathAlgorithms.calcDroneEnergy(p, d, lp);
        assertEquals(expResult, result, 0);

        lp.add(new Product(0, "Test", 0.0, -10, 0));

        expResult = 6325.0361044119;
        result = PathAlgorithms.calcDroneEnergy(p, d, lp);
        assertEquals(expResult, result, 0);

        a1 = new Address("Test1", 45, 45, 0);
        a2 = new Address("Test2", 45, 45, 0);
        p = new Path(a1, a2, 0.7, 90, 12);

        expResult = 0;
        result = PathAlgorithms.calcDroneEnergy(p, d, lp);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcScooterTotalEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcScooterTotalEnergy_5args() {

        System.out.println("calcScooterTotalEnergy");

        Graph<Address, Path> g = new Graph<>(true);

        List<Address> la = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);

        la.add(a1);
        la.add(a2);
        la.add(a3);

        List<Path> lp = new ArrayList<>();

        lp.add(new Path(a1, a2, 0, 90, 12));
        lp.add(new Path(a2, a3, 0, 90, 12));

        GraphAlgorithms.fillGraph(g, la, lp);

        LinkedList<Address> lla = new LinkedList<>();

        lla.add(a1);
        lla.add(a2);
        lla.add(a3);

        Courier c = new Courier("TestMail", "TestPass", "Name", 0, 0, 0, 80.0);
        Scooter s = new Scooter(1, 1, 15, 1.1, 0.5, 200, 200, 200, 8.9, 1);
        List<Product> lpro = new ArrayList<>();
        double expResult = 14.576764266689551;
        double result = PathAlgorithms.calcScooterTotalEnergy(g, lla, c, s, lpro);
        assertEquals(expResult, result, 0);

        lla = new LinkedList<>();

        expResult = 0.0d;
        result = PathAlgorithms.calcScooterTotalEnergy(g, lla, c, s, lpro);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcDroneTotalEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcDroneTotalEnergy_4args() {

        System.out.println("calcDroneTotalEnergy");

        Graph<Address, Path> g = new Graph<>(true);

        List<Address> la = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 83);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 79);
        Address a3 = new Address("trindade", 41.151667, 8.609444, 86);

        la.add(a1);
        la.add(a2);
        la.add(a3);

        List<Path> lp = new ArrayList<>();

        lp.add(new Path(a1, a2, 0, 90, 12));
        lp.add(new Path(a2, a3, 0, 90, 12));

        GraphAlgorithms.fillGraph(g, la, lp);

        LinkedList<Address> lla = new LinkedList<>();

        lla.add(a1);
        lla.add(a2);
        lla.add(a3);

        Drone d = new Drone(1, 1, 5, 0.6, 0.4, 200, 200, 200, 22.36, 0.5, 9, 1);
        List<Product> lpro = new ArrayList<>();
        double expResult = 38.207430834455025;
        double result = PathAlgorithms.calcDroneTotalEnergy(g, lla, d, lpro);
        assertEquals(expResult, result, 0);

        lpro.add(new Product(1, "Test", 0, 180, 1));

        expResult = 172.24160458590814;
        result = PathAlgorithms.calcDroneTotalEnergy(g, lla, d, lpro);
        assertEquals(expResult, result, 0);

        lla = new LinkedList<>();

        expResult = 0.0d;
        result = PathAlgorithms.calcDroneTotalEnergy(g, lla, d, lpro);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcDroneTotalEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcDroneTotalEnergy_4args2() {

        System.out.println("calcDroneTotalEnergy2");

        Graph<Address, Path> g = new Graph<>(true);

        List<Address> la = new ArrayList<>();

        Address a1 = new Address("casa da musica", 41.158056, 8.630556, 91892);
        Address a2 = new Address("conservatorio", 41.155556, 8.623056, 2);
        Address a3 = new Address("trindade", 41.151667, 8.609444, -28237);

        la.add(a1);
        la.add(a2);
        la.add(a3);

        List<Path> lp = new ArrayList<>();

        lp.add(new Path(a1, a2, 0, 90, 12));
        lp.add(new Path(a2, a3, 0, 90, 12));

        GraphAlgorithms.fillGraph(g, la, lp);

        LinkedList<Address> lla = new LinkedList<>();

        lla.add(a1);
        lla.add(a2);
        lla.add(a3);

        Drone d = new Drone(1, 1, 5, 0.6, 0.4, 200, 200, 200, 22.36, 0.5, 9, 1);
        List<Product> lpro = new ArrayList<>();
        double expResult = 2416.8419555951587;
        double result = PathAlgorithms.calcDroneTotalEnergy(g, lla, d, lpro);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getRelativeSpeed method, of class PathAlgorithms.
     */
    @Test
    public void testGetRelativeSpeed() {

        System.out.println("getRelativeSpeed");
        double windSpeed = 30;
        double windDegrees = 175;
        double speed = 90;

        double expResult = 119.88584094275237;
        double result = PathAlgorithms.getRelativeSpeed(windSpeed, windDegrees, speed);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcScooterEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcScooterEnergy_Path() {

        System.out.println("calcScooterEnergy");
        Address a1 = new Address("Test1", 45, 45, 57);
        Address a2 = new Address("Test2", 46, 48.7749, 678);
        Path p = new Path(a1, a2, 0.7, 90, 12);

        double expResult = 56119.96953065921;
        double result = PathAlgorithms.calcScooterEnergy(p);
        assertEquals(expResult, result, 0);

        a1 = new Address("Test1", 45, 45, 0);
        a2 = new Address("Test2", 45, 45, 0);
        p = new Path(a1, a2, 0.7, 90, 12);

        expResult = 0;
        result = PathAlgorithms.calcScooterEnergy(p);
        assertEquals(expResult, result, 0);

        a1 = new Address("Test1", 45, 45, 80);
        a2 = new Address("Test2", 45, 45.01, -120);
        p = new Path(a1, a2, 0, 90, 12);

        expResult = 0;
        result = PathAlgorithms.calcScooterEnergy(p);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of calcDroneEnergy method, of class PathAlgorithms.
     */
    @Test
    public void testCalcDroneEnergy_Path() {

        System.out.println("calcDroneEnergy");
        Address a1 = new Address("Test1", 45, 45, 0);
        Address a2 = new Address("Test2", 46, 48.7749, 0);
        Path p = new Path(a1, a2, 0.7, 90, 12);

        double expResult = 6325.0361044119;
        double result = PathAlgorithms.calcDroneEnergy(p);
        assertEquals(expResult, result, 0);

        a1 = new Address("Test1", 45, 45, 0);
        a2 = new Address("Test2", 45, 45, 0);
        p = new Path(a1, a2, 0.7, 90, 12);

        expResult = 0;
        result = PathAlgorithms.calcDroneEnergy(p);
        assertEquals(expResult, result, 0);
    }
}
