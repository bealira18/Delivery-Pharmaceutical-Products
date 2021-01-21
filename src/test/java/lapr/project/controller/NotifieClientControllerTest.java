package lapr.project.controller;

import lapr.project.data.DeliveryDB;
import lapr.project.data.DeliveryStatusDB;
import lapr.project.data.EmailService;
import lapr.project.data.StockDB;
import lapr.project.model.PurchaseOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NotifieClientControllerTest {

    private static NotifyClientController controller;

    public NotifieClientControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        PurchaseOrder order1 = new PurchaseOrder(1, 1, "TestName", LocalDate.now());

        StockDB stockDB = mock(StockDB.class);
        DeliveryDB deliveryDB = mock(DeliveryDB.class);
        DeliveryStatusDB deliveryStatusDB = mock(DeliveryStatusDB.class);
        EmailService emailService = mock(EmailService.class);

        when(stockDB.checkIfIsEnoughStock(order1.getId())).thenReturn(Boolean.TRUE);
        when(stockDB.checkIfIsEnoughStockInOtherPharmacy(order1.getId())).thenReturn(Boolean.TRUE);

        when(deliveryStatusDB.updateDeliveryStatusInDelivery(order1.getId())).thenReturn(Boolean.TRUE);
        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn(order1.getClientEmail());
        when(emailService.sendEmail(order1.getClientEmail(),"Delivery Run Starts", "This email is just to let you know that the delivery is on the way")).thenReturn(Boolean.TRUE);

        controller = new NotifyClientController();
        controller = new NotifyClientController(stockDB, deliveryDB, deliveryStatusDB, emailService);
    }

    /**
     * Test of checkIfIsEnoughStock method, of class NotifieClientController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCheckIfIsEnoughStock() throws Exception {

        System.out.println("checkIfIsEnoughtStock");

        PurchaseOrder order1 = new PurchaseOrder(1, 1, "TestName", LocalDate.now());

        boolean result = controller.checkIfIsEnoughStock(order1);
        assertEquals(true, result);

        StockDB stockDB = mock(StockDB.class);
        DeliveryDB deliveryDB = mock(DeliveryDB.class);
        DeliveryStatusDB deliveryStatusDB = mock(DeliveryStatusDB.class);
        EmailService emailService = mock(EmailService.class);

        when(stockDB.checkIfIsEnoughStock(order1.getId())).thenReturn(Boolean.TRUE);

        NotifyClientController controller1 = new NotifyClientController(stockDB, deliveryDB, deliveryStatusDB, emailService);

        result = controller1.checkIfIsEnoughStock(order1);
        assertEquals(true, result);

        when(stockDB.checkIfIsEnoughStock(order1.getId())).thenReturn(Boolean.FALSE);
        when(stockDB.checkIfIsEnoughStockInOtherPharmacy(order1.getId())).thenReturn(Boolean.FALSE);

        result = controller1.checkIfIsEnoughStock(order1);
        assertEquals(false, result);

        when(stockDB.checkIfIsEnoughStock(order1.getId())).thenReturn(Boolean.FALSE);
        when(stockDB.checkIfIsEnoughStockInOtherPharmacy(order1.getId())).thenReturn(Boolean.TRUE);

        result = controller1.checkIfIsEnoughStock(order1);
        assertEquals(true, result);
    }

    /**
     * Test of notifyClientDeliveryRunStarts method, of class NotifieClientController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testNotifyClientDeliveryRunStarts() throws Exception {
        System.out.println("notifyClientDeliveryRunStarts");

        PurchaseOrder order1 = new PurchaseOrder(1, 1, "teste@gmail.com", LocalDate.now());

        boolean result = controller.notifyClientDeliveryRunStarts(order1);
        assertEquals(true, result);

        StockDB stockDB = mock(StockDB.class);
        DeliveryDB deliveryDB = mock(DeliveryDB.class);
        DeliveryStatusDB deliveryStatusDB = mock(DeliveryStatusDB.class);
        EmailService emailService = mock(EmailService.class);

        when(deliveryStatusDB.updateDeliveryStatusInDelivery(order1.getId())).thenReturn(Boolean.FALSE);

        NotifyClientController controller1 = new NotifyClientController(stockDB, deliveryDB, deliveryStatusDB, emailService);

        result = controller1.notifyClientDeliveryRunStarts(order1);
        assertEquals(false, result);

        when(deliveryStatusDB.updateDeliveryStatusInDelivery(order1.getId())).thenReturn(Boolean.TRUE);
        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn("teste@gmail.com");
        when(emailService.sendEmail(order1.getClientEmail(),"Delivery Run Starts", "This email is just to let you know that the delivery is on the way")).thenReturn(Boolean.FALSE);

        result = controller1.notifyClientDeliveryRunStarts(order1);
        assertEquals(false, result);
    }
}