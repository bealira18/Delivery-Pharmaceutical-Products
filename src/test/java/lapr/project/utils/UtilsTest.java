package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    public UtilsTest() {
    }

    /**
     * Test of readFile method, of class Utils.
     */
    @Test
    public void testReadFile() {

        System.out.println("readFile");

        assertEquals(new ArrayList<>(), Utils.readFile(""));

        assertEquals(new ArrayList<>(), Utils.readFile(null));

        String fileName = "testFile.csv";
        List<String> expResult = new ArrayList<>();
        List<String> result = Utils.readFile(fileName);
        expResult.add("Path #1");
        expResult.add("Total Distance = 0.0km.");
        expResult.add("Total Energy = 0.0W.");
        expResult.add("aqui;ali;");
        assertEquals(expResult, result);
    }

    @Test
    public void testReadFile2() {

        System.out.println("readFile2");

        String fileName = "testFail.csv";

        List<String> expResult = new ArrayList<>();
        List<String> result = Utils.readFile(fileName);
        assertEquals(expResult, result);
    }
}