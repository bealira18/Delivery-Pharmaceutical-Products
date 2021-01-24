package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Drone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddDroneControllerTest {

    private static AddDroneController controller;

    public AddDroneControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        Drone drone1 = new Drone(1, 1, 20, 40, 50, 60, 10, 100, 22.36, 1,1,1);

        DroneDB droneDB = mock(DroneDB.class);
        ParkDB parkDB = mock(ParkDB.class);

        when(droneDB.addDrone(drone1)).thenReturn(Boolean.TRUE);
        when(parkDB.getNumberOfDronesInPharmacy(drone1.getIdPharmacy())).thenReturn(0);
        when(parkDB.getLimitVehiclesPark(drone1.getIdPharmacy(),"drone")).thenReturn(1);

        controller = new AddDroneController();
        controller = new AddDroneController(droneDB, parkDB);
    }

    /**
     * Test of addDrone method, of class AddDroneController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddDrone() throws Exception {

        System.out.println("addDrone");

        Drone drone1 = new Drone(1, 1, 20, 40, 50, 60, 10, 100, 22.36, 1,1,1);

        boolean result = controller.addDrone(drone1);
        assertEquals(true, result);

        DroneDB droneDB = mock(DroneDB.class);
        ParkDB parkDB = mock(ParkDB.class);

        when(parkDB.getNumberOfDronesInPharmacy(drone1.getIdPharmacy())).thenReturn(1);
        when(droneDB.addDrone(drone1)).thenReturn(Boolean.FALSE);

        AddDroneController controller1 = new AddDroneController(droneDB, parkDB);

        result = controller1.addDrone(drone1);
        assertEquals(false, result);

        when(parkDB.getNumberOfDronesInPharmacy(drone1.getIdPharmacy())).thenReturn(0);

        result = controller1.addDrone(drone1);
        assertEquals(false, result);
    }

}