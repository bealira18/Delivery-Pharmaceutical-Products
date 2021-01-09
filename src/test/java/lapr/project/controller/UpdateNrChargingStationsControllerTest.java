package lapr.project.controller;

import lapr.project.data.PurchaseOrderDB;
import lapr.project.data.ScooterParkDB;
import lapr.project.model.ScooterPark;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;

public class UpdateNrChargingStationsControllerTest {

    private static UpdateNrChargingStationsController UNrCSCont;

    public UpdateNrChargingStationsControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        ScooterParkDB spDB = mock(ScooterParkDB.class);

        UNrCSCont = new UpdateNrChargingStationsController();
        UNrCSCont = new UpdateNrChargingStationsController(spDB);
    }

    /**
     * Test of UpdateNrChargingStations method, of class ScooterParkController.
     */
    @Test
    public void testUpdateNrChargingStations() throws SQLException {

        System.out.println("UpdateNrChargingStations");
        int nrCS=2;
        UNrCSCont=new UpdateNrChargingStationsController();

        boolean expResult=true;
        boolean result=UNrCSCont.updateNrChargingStations(nrCS);
        assertEquals(expResult, result);

        nrCS=-2;
        expResult=false;
        result=UNrCSCont.updateNrChargingStations(nrCS);
        assertEquals(expResult, result);
    }

}
