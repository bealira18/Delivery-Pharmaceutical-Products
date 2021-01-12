/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.SettingsHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Ricardo
 */
public class UpdateDeliveryFeeControllerTest {
    
    public static UpdateDeliveryFeeController uDFC;
    
    public UpdateDeliveryFeeControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        SettingsHandler sh = mock(SettingsHandler.class);
        doNothing().when(sh).saveSettings(SettingsHandler.SETTINGS_FILE);
        
        uDFC = new UpdateDeliveryFeeController();
        uDFC = new UpdateDeliveryFeeController(sh);
        
    }

    /**
     * Test of updateDeliveryFee method, of class UpdateDeliveryFeeController.
     */
    @Test
    public void testUpdateDeliveryFee() {
        System.out.println("updateDeliveryFee");
        double fee = 5.0;
        uDFC.updateDeliveryFee(fee);
        double result = Double.parseDouble(System.getProperty("purchase.order.delivery.fee"));
        assertEquals(fee, result);
    }
    
}
