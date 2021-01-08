package lapr.project.controller;

import lapr.project.data.PurchaseOrderDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PurchaseOrderControllerTest {

    private static PurchaseOrderController pOrdCont;

    public PurchaseOrderControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        PurchaseOrderDB pOrdDB = mock(PurchaseOrderDB.class);

        pOrdCont = new PurchaseOrderController();
        pOrdCont = new PurchaseOrderController(pOrdDB);
    }

    /**
     * Test of setDeliveryFee method, of class PurchaseOrderController.
     */
    @Test
    public void testSetDeliveryFee() {

        System.out.println("setDeliveryFee");

        double deliveryFee = 20.0;
        pOrdCont = new PurchaseOrderController();

        boolean expResult = true;
        boolean result = pOrdCont.setDeliveryFee(deliveryFee);
        assertEquals(expResult, result);

        deliveryFee = -2.0;

        expResult = false;
        result = pOrdCont.setDeliveryFee(deliveryFee);
        assertEquals(expResult, result);
    }
}
