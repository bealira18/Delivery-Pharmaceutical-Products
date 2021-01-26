package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.model.Address;
import lapr.project.model.Park;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateParkControllerTest {

    private static UpdateParkController controller;

    public UpdateParkControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        Address a = new Address("TestAddress", 0, 0, 0);
        Park park = new Park(1, 1, 1,1, "TestCategory", a, 500);

        ParkDB pDB = mock(ParkDB.class);

        when(pDB.getParkById(1)).thenReturn(park);
        when(pDB.updateChargingStations(park)).thenReturn(Boolean.TRUE);
        when(pDB.updateParkChargingPotency(1, 50)).thenReturn(Boolean.TRUE);

        controller = new UpdateParkController();
        controller = new UpdateParkController(pDB);
    }

    @Test
    void testUpdateNrChargingStations() {

        Address a = new Address("TestAddress", 0, 0, 0);
        Park park = new Park(1, 1, 1,1, "TestCategory", a, 500);

        boolean expResult = true;
        boolean result = controller.updateNrChargingStations(1, 1);
        assertEquals(expResult, result);

        ParkDB pDB = mock(ParkDB.class);

        when(pDB.getParkById(1)).thenReturn(park);
        when(pDB.updateChargingStations(park)).thenReturn(Boolean.FALSE);

        UpdateParkController controller2 = new UpdateParkController(pDB);

        expResult = false;
        result = controller2.updateNrChargingStations(1, 1);
        assertEquals(expResult, result);

        when(pDB.updateChargingStations(park)).thenReturn(Boolean.TRUE);

        expResult = false;
        result = controller2.updateNrChargingStations(1, 2);
        assertEquals(expResult, result);
    }

    @Test
    void testUpdateParkChargingPotency() {
        boolean expResult = true;
        boolean result = controller.updateParkChargingPotency(1, 50);
        assertEquals(expResult, result);

        ParkDB pDB = mock(ParkDB.class);

        when(pDB.updateParkChargingPotency(1, 50)).thenReturn(Boolean.FALSE);

        UpdateParkController controller2 = new UpdateParkController(pDB);

        expResult = false;
        result = controller2.updateParkChargingPotency(1, 50);
        assertEquals(expResult, result);
    }

}
