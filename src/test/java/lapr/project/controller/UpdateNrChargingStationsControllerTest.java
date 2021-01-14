package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class UpdateNrChargingStationsControllerTest {

    private static UpdateNrChargingStationsController csC;

    public UpdateNrChargingStationsControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        ParkDB pDB = mock(ParkDB.class);

        csC = new UpdateNrChargingStationsController();
        csC = new UpdateNrChargingStationsController(pDB);
    }

    @Test
    void testUpdateNrChargingStations() throws SQLException {
        boolean actual=csC.updateNrChargingStations(0,2);
        assertFalse(actual); //porque não existem parks
    }

}
