package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.utils.Graph;
import lapr.project.utils.GraphAlgorithms;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NotifyClientControllerTest {

    private static NotifyClientController controller;

    public NotifyClientControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        List<Address> addressList = new LinkedList<>();
        int productQuantity1 = 1;
        Address a = new Address("Test", 0, 0, 0);
        Address b = new Address("Test", 1, 1, 1);
        addressList.add(a);
        addressList.add(b);
        Pharmacy pharmacy1 = new Pharmacy(1, "TestName", a);
        Pharmacy pharmacy2 = new Pharmacy(2, "TestName", b);
        Product product1 = new Product(1, "TestName", 30, 3, 1);
        Graph<Address, Path> graph = new Graph<>(true);
        PurchaseOrder order1 = new PurchaseOrder(1, 1, "test@email.com", LocalDate.now());
        CreditCard creditCard = new CreditCard(5295360011327825L, LocalDate.of(2077, Month.MARCH, 1), (short) 454);
        Address address = new Address("Rua Joaquim, 542", 41.15796537787468, -8.62910514603121, 5.200514144411);
        Client client = new Client("test@email.com", "qwerty", "user", 123456789, creditCard, address, 1);

        StockDB stockDB = mock(StockDB.class);
        DeliveryDB deliveryDB = mock(DeliveryDB.class);
        DeliveryStatusDB deliveryStatusDB = mock(DeliveryStatusDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        EmailService emailService = mock(EmailService.class);
        AddressDB addressDB = mock(AddressDB.class);

        when(stockDB.checkIfIsEnoughStock(pharmacy1.getId(), product1.getId(), productQuantity1)).thenReturn(0);
        when(stockDB.getOthersPharmacyAddressWithProductStock(pharmacy1.getId(), product1.getId(), productQuantity1)).thenReturn(addressList);
        when(addressDB.getAddressPharmacyById(pharmacy1.getId())).thenReturn(a);
        //when(GraphAlgorithms.getNearestPharmacy(true, graph, a, addressList)).thenReturn(b);
        when(pharmacyDB.getPhamacyByAddress(b)).thenReturn(pharmacy2);

        when(stockDB.backOrder(pharmacy1.getId(), pharmacy2.getId(), product1.getId(), productQuantity1)).thenReturn(Boolean.TRUE);

        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn(client.getEmail());
        when(clientDB.getClientByEmail(client.getEmail())).thenReturn(client);
        when(deliveryStatusDB.updateDeliveryStatusInDelivery(order1.getId())).thenReturn(Boolean.TRUE);
        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn(order1.getClientEmail());
        when(emailService.sendEmail("clientemen0652@gmail.com","Delivery Run Starts", "Dear "+client.getName()+". This email is just to let you know that the delivery is on the way")).thenReturn(Boolean.TRUE);

        controller = new NotifyClientController();
        controller = new NotifyClientController(stockDB, deliveryDB, deliveryStatusDB, clientDB, pharmacyDB, emailService, addressDB);
    }

    /**
     * Test of backOrder method, of class NotifieClientController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCheckForStock() throws Exception {

        System.out.println("checkForStock");

        List<Address> addressList = new LinkedList<>();
        int productQuantity1 = 1;
        Address a = new Address("Test", 0, 0, 0);
        Address b = new Address("Test", 1, 1, 1);
        addressList.add(a);
        addressList.add(b);
        Pharmacy pharmacy1 = new Pharmacy(1, "TestName", a);
        Pharmacy pharmacy2 = new Pharmacy(2, "TestName", b);
        Product product1 = new Product(1, "TestName", 30, 3, 1);
        Graph<Address, Path> graph = new Graph<>(true);

        boolean result = controller.checkForStock(pharmacy1, product1, productQuantity1, graph);
        assertEquals(true, result);

        StockDB stockDB = mock(StockDB.class);
        DeliveryDB deliveryDB = mock(DeliveryDB.class);
        DeliveryStatusDB deliveryStatusDB = mock(DeliveryStatusDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        EmailService emailService = mock(EmailService.class);
        AddressDB addressDB = mock(AddressDB.class);

        when(stockDB.checkIfIsEnoughStock(pharmacy1.getId(), product1.getId(), productQuantity1)).thenReturn(1);
        when(stockDB.getOthersPharmacyAddressWithProductStock(pharmacy1.getId(), product1.getId(), productQuantity1)).thenReturn(addressList);

       NotifyClientController controller1 = new NotifyClientController(stockDB, deliveryDB, deliveryStatusDB, clientDB, pharmacyDB, emailService, addressDB);

        result = controller1.checkForStock(pharmacy1, product1, productQuantity1, graph);
        assertEquals(false, result);
    }

    /**
     * Test of checkIfIsEnoughStock method, of class NotifieClientController.
     *
     * @throws java.lang.Exception
     */
   @Test
    public void testCheckIfIsEnoughStock() throws Exception {

        System.out.println("checkIfIsEnoughtStock");

        int productQuantity1 = 1;
        Address a = new Address("Test", 0, 0, 0);
        Pharmacy pharmacy1 = new Pharmacy(1, "TestName", a);
        Product product1 = new Product(1, "TestName", 30, 3, 1);

        int result = controller.checkIfIsEnoughStock(pharmacy1, product1, productQuantity1);
        assertEquals(0, result);

        StockDB stockDB = mock(StockDB.class);
        DeliveryDB deliveryDB = mock(DeliveryDB.class);
        DeliveryStatusDB deliveryStatusDB = mock(DeliveryStatusDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        EmailService emailService = mock(EmailService.class);
        AddressDB addressDB = mock(AddressDB.class);

        when(stockDB.checkIfIsEnoughStock(pharmacy1.getId(), product1.getId(), productQuantity1)).thenReturn(0);

        NotifyClientController controller1 = new NotifyClientController(stockDB, deliveryDB, deliveryStatusDB, clientDB, pharmacyDB, emailService, addressDB);

        result = controller1.checkIfIsEnoughStock(pharmacy1, product1, productQuantity1);
        assertEquals(0, result);

        when(stockDB.checkIfIsEnoughStock(pharmacy1.getId(), product1.getId(), productQuantity1)).thenReturn(-1);

        result = controller1.checkIfIsEnoughStock(pharmacy1, product1, productQuantity1);
        assertEquals(-1, result);

        when(stockDB.checkIfIsEnoughStock(pharmacy1.getId(), product1.getId(), productQuantity1)).thenReturn(2);

       result = controller1.checkIfIsEnoughStock(pharmacy1, product1, productQuantity1);
       assertEquals(2, result);
   }

    /**
     * Test of checkIfIsEnoughStockOthersPharmacy method, of class NotifieClientController.
     *
     * @throws java.lang.Exception
     */
   /* @Test
    public void testcheckIfIsEnoughStockOthersPharmacy() throws Exception {

        System.out.println("checkIfIsEnoughStockOthersPharmacy");

        List<Address> addressList = new LinkedList<>();
        int productQuantity1 = 1;
        Address a = new Address("Test", 0, 0, 0);
        Address b = new Address("Test", 1, 1, 1);
        addressList.add(a);
        addressList.add(b);
        Pharmacy pharmacy1 = new Pharmacy(1, "TestName", a);
        Pharmacy pharmacy2 = new Pharmacy(2, "TestName", b);
        Product product1 = new Product(1, "TestName", 30, 3, 1);
        Graph<Address, Path> graph = new Graph<>(true);

        Boolean result = controller.checkIfIsEnoughStockOthersPharmacy(pharmacy1, product1, productQuantity1, graph);
        assertEquals(true, result);

        StockDB stockDB = mock(StockDB.class);
        DeliveryDB deliveryDB = mock(DeliveryDB.class);
        DeliveryStatusDB deliveryStatusDB = mock(DeliveryStatusDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        EmailService emailService = mock(EmailService.class);
        AddressDB addressDB = mock(AddressDB.class);


    }*/


    /**
     * Test of backOrder method, of class NotifieClientController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testBackOrder() throws Exception {

        System.out.println("backOrder");

        int productQuantity1 = 1;
        Address a = new Address("Test", 0, 0, 0);
        Address b = new Address("Test", 1, 1, 1);
        Pharmacy pharmacy1 = new Pharmacy(1, "TestName", a);
        Pharmacy pharmacy2 = new Pharmacy(2, "TestName", b);
        Product product1 = new Product(1, "TestName", 30, 3, 1);

        boolean result = controller.backOrder(pharmacy1, pharmacy2, product1, productQuantity1);
        assertEquals(true, result);

        StockDB stockDB = mock(StockDB.class);
        DeliveryDB deliveryDB = mock(DeliveryDB.class);
        DeliveryStatusDB deliveryStatusDB = mock(DeliveryStatusDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        EmailService emailService = mock(EmailService.class);
        AddressDB addressDB = mock(AddressDB.class);

        when(stockDB.backOrder(pharmacy1.getId(), pharmacy2.getId(), product1.getId(), productQuantity1)).thenReturn(Boolean.FALSE);

        NotifyClientController controller1 = new NotifyClientController(stockDB, deliveryDB, deliveryStatusDB, clientDB, pharmacyDB, emailService, addressDB);

        result = controller1.backOrder(pharmacy1, pharmacy2, product1, productQuantity1);
        assertEquals(false, result);
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
        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        EmailService emailService = mock(EmailService.class);
        AddressDB addressDB = mock(AddressDB.class);

        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn(client.getEmail());
        when(clientDB.getClientByEmail(client.getEmail())).thenReturn(client);
        when(deliveryStatusDB.updateDeliveryStatusInDelivery(order1.getId())).thenReturn(Boolean.FALSE);
        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn(order1.getClientEmail());
        when(emailService.sendEmail("clientemen0652@gmail.com","Delivery Run Starts", "Dear "+client.getName()+". This email is just to let you know that the delivery is on the way")).thenReturn(Boolean.TRUE);

        NotifyClientController controller1 = new NotifyClientController(stockDB, deliveryDB, deliveryStatusDB, clientDB, pharmacyDB, emailService, addressDB);

        result = controller1.notifyClientDeliveryRunStarts(order1);
        assertEquals(false, result);

        when(deliveryStatusDB.updateDeliveryStatusInDelivery(order1.getId())).thenReturn(Boolean.TRUE);
        when(deliveryDB.getClientEmailFromOrder(order1.getId())).thenReturn("test@email.com");
        when(emailService.sendEmail("clientemen0652@gmail.com","Delivery Run Starts", "Dear "+client.getName()+". This email is just to let you know that the delivery is on the way")).thenReturn(Boolean.FALSE);

        result = controller1.notifyClientDeliveryRunStarts(order1);
        assertEquals(false, result);
    }
}