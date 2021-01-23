package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.model.PurchaseOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NotifyClientControllerTest {

    private static NotifyClientController controller;

    public NotifyClientControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        PurchaseOrder order1 = new PurchaseOrder(1, 1, "test@email.com", LocalDate.now());
        CreditCard creditCard = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        Address address = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        Client client = new Client("test@email.com", "qwerty", "user", 123456789, creditCard, address, 1);

        StockDB stockDB = mock(StockDB.class);
        DeliveryDB deliveryDB = mock(DeliveryDB.class);
        DeliveryStatusDB deliveryStatusDB = mock(DeliveryStatusDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        EmailService emailService = mock(EmailService.class);

        when(stockDB.checkIfIsEnoughStock(order1.getId())).thenReturn(Boolean.TRUE);
        when(stockDB.checkIfIsEnoughStockInOtherPharmacy(order1.getId())).thenReturn(Boolean.TRUE);

        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn(client.getEmail());
        when(clientDB.getClientByEmail(client.getEmail())).thenReturn(client);
        when(deliveryStatusDB.updateDeliveryStatusInDelivery(order1.getId())).thenReturn(Boolean.TRUE);
        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn(order1.getClientEmail());
        when(emailService.sendEmail("clientemen0652@gmail.com","Delivery Run Starts", "Dear "+client.getName()+". This email is just to let you know that the delivery is on the way")).thenReturn(Boolean.TRUE);

        controller = new NotifyClientController();
        controller = new NotifyClientController(stockDB, deliveryDB, deliveryStatusDB, clientDB, emailService);
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
        ClientDB clientDB = mock(ClientDB.class);

        when(stockDB.checkIfIsEnoughStock(order1.getId())).thenReturn(Boolean.TRUE);

        NotifyClientController controller1 = new NotifyClientController(stockDB, deliveryDB, deliveryStatusDB, clientDB, emailService);

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

        PurchaseOrder order1 = new PurchaseOrder(1, 1, "test@email.com", LocalDate.now());
        CreditCard creditCard = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        Address address = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        Client client = new Client("test@email.com", "qwerty", "user", 123456789, creditCard, address, 1);

        boolean result = controller.notifyClientDeliveryRunStarts(order1);
        assertEquals(true, result);

        StockDB stockDB = mock(StockDB.class);
        DeliveryDB deliveryDB = mock(DeliveryDB.class);
        DeliveryStatusDB deliveryStatusDB = mock(DeliveryStatusDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        EmailService emailService = mock(EmailService.class);

        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn(client.getEmail());
        when(clientDB.getClientByEmail(client.getEmail())).thenReturn(client);
        when(deliveryStatusDB.updateDeliveryStatusInDelivery(order1.getId())).thenReturn(Boolean.FALSE);
        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn(order1.getClientEmail());
        when(emailService.sendEmail("clientemen0652@gmail.com","Delivery Run Starts", "Dear "+client.getName()+". This email is just to let you know that the delivery is on the way")).thenReturn(Boolean.TRUE);


        NotifyClientController controller1 = new NotifyClientController(stockDB, deliveryDB, deliveryStatusDB, clientDB, emailService);

        result = controller1.notifyClientDeliveryRunStarts(order1);
        assertEquals(false, result);

        when(deliveryStatusDB.updateDeliveryStatusInDelivery(order1.getId())).thenReturn(Boolean.TRUE);
        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn("test@email.com");
        when(emailService.sendEmail("clientemen0652@gmail.com","Delivery Run Starts", "Dear "+client.getName()+". This email is just to let you know that the delivery is on the way")).thenReturn(Boolean.FALSE);

        result = controller1.notifyClientDeliveryRunStarts(order1);
        assertEquals(false, result);
    }
}