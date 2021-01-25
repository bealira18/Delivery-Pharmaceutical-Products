package lapr.project.controller;

import lapr.project.data.ParkDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class UpdateNrChargingStationsControllerTest {

    private static UpdateParkController csC;

    public UpdateNrChargingStationsControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        ParkDB pDB = mock(ParkDB.class);

        csC = new UpdateParkController();
        csC = new UpdateParkController(pDB);
    }

    @Test
    void testUpdateNrChargingStations() throws SQLException {
        boolean actual=csC.updateNrChargingStations(0,2);
        assertFalse(actual); //porque não existem parks
    }

}
