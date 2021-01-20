package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class UpdateScooterControllerTest {

    private static UpdateScooterController sPC;

    public UpdateScooterControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        ScooterDB pDB = mock(ScooterDB.class);

        sPC = new UpdateScooterController();
        sPC = new UpdateScooterController(pDB);
    }

    @Test
    void testUpdateScooter1() throws SQLException {
        //int idVehicle, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea, double motor, double currentBattery, double maxBattery)
        Scooter s=new Scooter(0,0,2.0,2.5,1,2.0,35.0,40.0, 8.9,0);
        boolean actual=sPC.updateScooter(0,s);
        assertFalse(actual); //porque não existem scooters
    }

    @Test
    void testUpdateScooter2() throws SQLException {
        boolean actual=sPC.updateScooter(0,null);
        assertFalse(actual);
    }

}
