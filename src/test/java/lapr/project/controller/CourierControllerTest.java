package lapr.project.controller;

import lapr.project.data.CourierDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CourierControllerTest {

    private static CourierController cCont;

    public CourierControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        CourierDB cDB = mock(CourierDB.class);

        cCont = new CourierController();
        cCont = new CourierController(cDB);
    }

    /**
     * Test of setMaximumPayload method, of class CourierController.
     */
    @Test
    public void testSetMaximumPayload() {

        System.out.println("setMaximumPayload");
        int maximumPayload = 5000;
        cCont = new CourierController();

        boolean expResult = true;
        boolean result = cCont.setMaximumPayload(maximumPayload);
        assertEquals(expResult, result);

        maximumPayload = -20;

        expResult = false;
        result = cCont.setMaximumPayload(maximumPayload);
        assertEquals(expResult, result);
    }
}
