package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.StockDB;
import lapr.project.model.PurchaseOrder;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NotifieClientControllerTest {

    private static NotifieClientController controller;

    public NotifieClientControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        PurchaseOrder order1 = new PurchaseOrder(1, 1, "TestName", LocalDate.now());

        StockDB stockDB = mock(StockDB.class);
        
        when(stockDB.checkIfIsEnoughStock(order1.getId())).thenReturn(Boolean.TRUE);
        when(stockDB.checkIfIsEnoughStockInOtherPharmacy(order1.getId())).thenReturn(Boolean.TRUE);

        controller = new NotifieClientController();
        controller = new NotifieClientController(stockDB);
    }

    /**
     * Test of checkIfIsEnoughStock method, of class NotifieClientController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testcheckIfIsEnoughStock() throws Exception {

        System.out.println("checkIfIsEnoughtStock");

        PurchaseOrder order1 = new PurchaseOrder(1, 1, "TestName", LocalDate.now());

        boolean result = controller.checkIfIsEnoughStock(order1);
        assertEquals(true, result);

        StockDB stockDB = mock(StockDB.class);

        when(stockDB.checkIfIsEnoughStock(order1.getId())).thenReturn(Boolean.TRUE);

        NotifieClientController controller1 = new NotifieClientController(stockDB);

        result = controller1.checkIfIsEnoughStock(order1);
        assertEquals(true, result);

        when(stockDB.checkIfIsEnoughStock(order1.getId())).thenReturn(Boolean.TRUE);
        when(stockDB.checkIfIsEnoughStockInOtherPharmacy(order1.getId())).thenReturn(Boolean.TRUE);

        result = controller1.checkIfIsEnoughStock(order1);
        assertEquals(true, result);
    }

}