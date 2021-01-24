package lapr.project.controller;

import lapr.project.data.SettingsHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateDeliveryFeeControllerTest {

    public static UpdateDeliveryFeeController uDFC;

    public UpdateDeliveryFeeControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        SettingsHandler sh = mock(SettingsHandler.class);
        
        when(sh.saveSettings(SettingsHandler.SETTINGS_FILE)).thenReturn(true);

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
        boolean bResult = uDFC.updateDeliveryFee(fee);
        double result = Double.parseDouble(System.getProperty("purchase.order.delivery.fee"));
        assertEquals(fee, result);
        assertEquals(true, bResult);
        
        SettingsHandler sh = mock(SettingsHandler.class);
        
        when(sh.saveSettings(SettingsHandler.SETTINGS_FILE)).thenReturn(false);

        UpdateDeliveryFeeController instance = new UpdateDeliveryFeeController(sh);
        bResult = instance.updateDeliveryFee(fee);
        assertEquals(false, bResult);
        
        final double deliveryFee2 = -1.0;
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            uDFC.updateDeliveryFee(deliveryFee2);
        });
        assertEquals("Invalid Numeric Value (Negative Delivery Fee)", ex.getMessage());
    }

    /**
     * Test of getDeliveryFee method, of class UpdateDeliveryFeeController.
     */
    @Test
    public void testGetDeliveryFee() {

        System.out.println("getDeliveryFee");
        System.setProperty("purchase.order.delivery.fee", "2.0");
        double expResult = 2.0;
        double result = uDFC.getDeliveryFee();
        assertEquals(expResult, result, 0.0);
    }
}
