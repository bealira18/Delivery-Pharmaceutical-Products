package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.model.Drone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import lapr.project.data.SettingsHandler;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateDroneControllerTest {

    private static UpdateDroneController dPC;

    public UpdateDroneControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        DroneDB dDB = mock(DroneDB.class);
        SettingsHandler sH = mock(SettingsHandler.class);
        
        when(sH.saveSettings(SettingsHandler.SETTINGS_FILE)).thenReturn(true);

        dPC = new UpdateDroneController();
        dPC = new UpdateDroneController(dDB, sH);
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

    /**
     * Test of getDroneMaxPayload method, of class UpdateDroneController.
     */
    @Test
    public void testGetDroneMaxPayload() {
        System.out.println("getDroneMaxPayload");
        System.setProperty("drone.max.payload", "2.0");
        double expResult = 2.0;
        double result = dPC.getDroneMaxPayload();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of updateDroneMaxPayload method, of class UpdateDroneController.
     */
    @Test
    public void testUpdateDroneMaxPayload() {
        System.out.println("updateDroneMaxPayload");
        double droneMaxPayload = 5.0;
        boolean bResult = dPC.updateDroneMaxPayload(droneMaxPayload);
        double result = Double.parseDouble(System.getProperty("drone.max.payload"));
        assertEquals(droneMaxPayload, result);
        assertEquals(true, bResult);
        
        DroneDB dDB = mock(DroneDB.class);
        SettingsHandler sh = mock(SettingsHandler.class);
        
        when(sh.saveSettings(SettingsHandler.SETTINGS_FILE)).thenReturn(false);

        UpdateDroneController instance = new UpdateDroneController(dDB, sh);
        bResult = instance.updateDroneMaxPayload(droneMaxPayload);
        assertEquals(false, bResult);
        
        final double deliveryFee2 = -1.0;
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            dPC.updateDroneMaxPayload(deliveryFee2);
        });
        assertEquals("Invalid Numeric Value (Negative or 0 Drone Max Payload)", ex.getMessage());
    }

}
