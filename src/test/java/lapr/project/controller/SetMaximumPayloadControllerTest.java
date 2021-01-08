package lapr.project.controller;

import lapr.project.data.CourierDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class SetMaximumPayloadControllerTest {

    private static SetMaximumPayloadController cCont;

    public SetMaximumPayloadControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        CourierDB cDB = mock(CourierDB.class);

        cCont = new SetMaximumPayloadController();
        cCont = new SetMaximumPayloadController(cDB);
    }

    /**
     * Test of setMaximumPayload method, of class CourierController.
     */
    @Test
    public void testSetMaximumPayload() {

        System.out.println("setMaximumPayload");
        int maximumPayload = 5000;
        cCont = new SetMaximumPayloadController();

        boolean expResult = true;
        boolean result = cCont.setMaximumPayload(maximumPayload);
        assertEquals(expResult, result);

        maximumPayload = -20;

        expResult = false;
        result = cCont.setMaximumPayload(maximumPayload);
        assertEquals(expResult, result);
    }
}
