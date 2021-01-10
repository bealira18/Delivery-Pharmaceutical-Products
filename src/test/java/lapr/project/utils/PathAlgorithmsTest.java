package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Path;
import lapr.project.model.Product;
import lapr.project.model.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathAlgorithmsTest {

    public PathAlgorithmsTest() {
    }

    @BeforeAll
    public static void setUpClass() {
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

        a1.setLatitude(69.203);
        a1.setLongitude(67.201);

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

        a2.setLatitude(45);
        a2.setLongitude(45);

        expResult = 0;
        result = PathAlgorithms.calcEnergy(p, c, v, lp);
        assertEquals(expResult, result, 0);
    }
}
