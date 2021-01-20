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

        assertNull(Utils.readFile(""));

        assertNull(Utils.readFile(null));

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

        List<String> expResult = null;
        List<String> result = Utils.readFile(fileName);
        assertEquals(expResult, result);
    }

    /**
     * Test of writeFile method, of class Utils.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testWriteFile() throws Exception {

        System.out.println("writeFile");
        String s = "Hello.\nThis is a writing Test.\n";
        String fileName = "writeTest.txt";

        boolean expResult = true;
        boolean result = Utils.writeFile(s, fileName);
        assertEquals(expResult, result);

        List<String> expList = new ArrayList<>();
        expList.add("Hello.");
        expList.add("This is a writing Test.");
        List<String> resultList = Utils.readFile(fileName);
        assertEquals(expList, resultList);
    }

    @Test
    public void testWriteFile2() throws Exception {

        System.out.println("writeFile2");
        String s = "";
        String fileName = null;

        boolean expResult = false;
        boolean result = Utils.writeFile(s, fileName);
        assertEquals(expResult, result);

        fileName = "";
        result = Utils.writeFile(s, fileName);
        assertEquals(expResult, result);

        fileName = "writeTest.txt";
        result = Utils.writeFile(s, fileName);
        assertEquals(expResult, result);

        s = null;
        result = Utils.writeFile(s, fileName);
        assertEquals(expResult, result);

        fileName = "";
        result = Utils.writeFile(s, fileName);
        assertEquals(expResult, result);

        s = "Hello";
        fileName = "    ||    []*  +/8)as0         as:_   asas\\asd\\as.acc<.aa.s.d,,sd";
        result = Utils.writeFile(s, fileName);
        assertEquals(expResult, result);
    }
}
