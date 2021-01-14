package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.data.ProductDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetDronesControllerTest {

    private static GetDronesController dc;

    public GetDronesControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        DroneDB dd = mock(DroneDB.class);
        when(dd.getAllAvailableDrones(0)).thenReturn(new ArrayList<>());


        dc = new GetDronesController();
        dc = new GetDronesController(dd);
    }

    /**
     * Test of getProducts method, of class GetProductsController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetDrones() throws Exception {

        System.out.println("getDrones");

        assertEquals(new ArrayList<>(), dc.getDrones(0));
    }

}
