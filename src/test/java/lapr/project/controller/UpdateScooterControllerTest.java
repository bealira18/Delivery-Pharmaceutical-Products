package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import lapr.project.data.SettingsHandler;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateScooterControllerTest {

    private static UpdateScooterController sPC;

    public UpdateScooterControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        ScooterDB pDB = mock(ScooterDB.class);
        
        SettingsHandler sh = mock(SettingsHandler.class);
        
        when(sh.saveSettings(SettingsHandler.SETTINGS_FILE)).thenReturn(true);

        sPC = new UpdateScooterController();
        sPC = new UpdateScooterController(pDB, sh);
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

    /**
     * Test of getScooterMaxPayload method, of class UpdateScooterController.
     */
    @Test
    public void testGetScooterMaxPayload() {
        System.out.println("getScooterMaxPayload");
        System.setProperty("scooter.max.payload", "2.0");
        double expResult = 2.0;
        double result = sPC.getScooterMaxPayload();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of updateScooterMaxPayload method, of class UpdateScooterController.
     */
    @Test
    public void testUpdateScooterMaxPayload() {
        System.out.println("updateScooterMaxPayload");
        double scooterMaxPayload = 5.0;
        boolean bResult = sPC.updateScooterMaxPayload(scooterMaxPayload);
        double result = Double.parseDouble(System.getProperty("scooter.max.payload"));
        assertEquals(scooterMaxPayload, result);
        assertEquals(true, bResult);
        
        ScooterDB pDB = mock(ScooterDB.class);
        SettingsHandler sh = mock(SettingsHandler.class);
        
        when(sh.saveSettings(SettingsHandler.SETTINGS_FILE)).thenReturn(false);

        UpdateScooterController instance = new UpdateScooterController(pDB, sh);
        bResult = instance.updateScooterMaxPayload(scooterMaxPayload);
        assertEquals(false, bResult);
        
        final double deliveryFee2 = -1.0;
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            sPC.updateScooterMaxPayload(deliveryFee2);
        });
        assertEquals("Invalid Numeric Value (Negative or 0 Scooter Max Payload)", ex.getMessage());
    }

}
