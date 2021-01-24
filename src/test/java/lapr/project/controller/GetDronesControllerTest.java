package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Drone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        assertEquals(Collections.emptyList(), dc.getDrones(0));

        List<Drone> droneList = new ArrayList<>();
        Drone drone1 = new Drone(1, 1, 20, 40, 50, 60, 10, 100, 22.36, 1,1,1);
        droneList.add(drone1);

        DroneDB dd = mock(DroneDB.class);
        when(dd.getAllAvailableDrones(0)).thenReturn(droneList);

        GetDronesController controller2 = new GetDronesController(dd);
        List<Drone> result = controller2.getDrones(0);

        assertEquals(droneList, result);
    }

}
