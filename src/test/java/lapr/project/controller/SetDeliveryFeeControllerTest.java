package lapr.project.controller;

import lapr.project.data.PurchaseOrderDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class SetDeliveryFeeControllerTest {

    private static SetDeliveryFeeController sDelFCont;

    public SetDeliveryFeeControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        PurchaseOrderDB pOrdDB = mock(PurchaseOrderDB.class);

        sDelFCont = new SetDeliveryFeeController();
        sDelFCont = new SetDeliveryFeeController(pOrdDB);
    }

    /**
     * Test of setDeliveryFee method, of class PurchaseOrderController.
     */
    @Test
    public void testSetDeliveryFee() {

        System.out.println("setDeliveryFee");

        double deliveryFee = 20.0;
        sDelFCont = new SetDeliveryFeeController();

        boolean expResult = true;
        boolean result = sDelFCont.setDeliveryFee(deliveryFee);
        assertEquals(expResult, result);

        deliveryFee = -2.0;

        expResult = false;
        result = sDelFCont.setDeliveryFee(deliveryFee);
        assertEquals(expResult, result);
    }
}
