package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Drone;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class UpdateDroneControllerTest {

    private static UpdateDroneController dPC;

    public UpdateDroneControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        DroneDB dDB = mock(DroneDB.class);

        dPC = new UpdateDroneController();
        dPC = new UpdateDroneController(dDB);
    }

    @Test
    void testUpdateDrone1() throws SQLException {
        //int idVehicle, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea, double motor, double currentBattery, double maxBattery)
        Drone s=new Drone(0,0,2.0,2.5,1,2.0,35.0,40.0, 22.36,0,1,1);
        boolean actual=dPC.updateDrone(0,s);
        assertFalse(actual); //porque não existem scooters
    }

    @Test
    void testUpdateDrone2() throws SQLException {
        boolean actual=dPC.updateDrone(0,null);
        assertFalse(actual);
    }

}
