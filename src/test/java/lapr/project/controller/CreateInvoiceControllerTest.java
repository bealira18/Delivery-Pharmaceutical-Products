package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateInvoiceControllerTest {

    private static CreateInvoiceController controller;
    private static List<ProductLine> auxProductLineList;

    public CreateInvoiceControllerTest() {
    }

    @BeforeAll
    public static void setUp() throws SQLException {

        PurchaseOrder purchaseOrder = new PurchaseOrder(1,1, "clientEmail@gmail.com", LocalDate.now());

        Invoice invoice = new Invoice(1,1,1, "clientEmail@gmail.com", 10.00);

        ProductLine productLine1 = new ProductLine(1,1,1,5.50);
        ProductLine productLine2 = new ProductLine(1,2,1,4.50);
        auxProductLineList = new ArrayList<>();
        auxProductLineList.add(productLine1);
        auxProductLineList.add(productLine2);

        Product product1 = new Product(1, "testProduct1", 1,1,1);
        Product product2 = new Product(2, "testProduct2", 1,1,1);

        Address address = new Address("testAddress",1,1,1);
        Pharmacy pharmacy = new Pharmacy(1, "testPharmacy", address);

        CreditCard creditCard = new CreditCard(1, LocalDate.now(), (short) 1);
        Client client = new Client("clientEmail@gmail.com", "qwerty", "testName", 1, creditCard, address, 1);

        InvoiceDB invoiceDB = mock(InvoiceDB.class);
        ProductLineDB productLineDB = mock(ProductLineDB.class);
        ProductDB productDB = mock(ProductDB.class);
        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        EmailService emailService = mock(EmailService.class);

        when(invoiceDB.addInvoice(invoice, 2.90)).thenReturn(Boolean.TRUE);
        when(productLineDB.getProductLinesFromOrder(1)).thenReturn(auxProductLineList);
        when(productDB.getProduct(productLine1.getProductId())).thenReturn(product1);
        when(productDB.getProduct(productLine2.getProductId())).thenReturn(product2);
        when(pharmacyDB.getPhamacyByID(invoice.getPharmacyId())).thenReturn(pharmacy);
        when(clientDB.getClientByEmail(invoice.getClientEmail())).thenReturn(client);
        when(emailService.sendEmail(client.getEmail(), "Receipt", "Receipt #1\r\n" +
                "\r\n" +
                "Pharmacy: testPharmacy\tid: 1\r\n" +
                "------------------------------------------------------------\r\n" +
                "Order:\r\n" +
                "Item                                    Number    Price     \r\n" +
                "------------------------------------------------------------\r\n" +
                "testProduct1                            1         €5,50      \r\n" +
                "------------------------------------------------------------\r\n" +
                "testProduct2                            1         €4,50      \r\n" +
                "------------------------------------------------------------\r\n" +
                "                                                  €10,00\r\n" +
                "\r\n" +
                "NIF: 1")).thenReturn(Boolean.TRUE);

        controller = new CreateInvoiceController();
        controller = new CreateInvoiceController(invoiceDB, productLineDB, productDB, pharmacyDB, clientDB, emailService);
        controller.getProductLinesFromOrder(purchaseOrder);

    }

    @Test
    void testCreateInvoice() throws SQLException {
        PurchaseOrder purchaseOrder = new PurchaseOrder(1,1, "clientEmail@gmail.com", LocalDate.now());
        Invoice invoice = new Invoice(1,1,1, "clientEmail@gmail.com", 10.00);

        boolean expResult = true;
        boolean result = controller.createInvoice(1, purchaseOrder, 2.90);
        assertEquals(expResult, result);


        InvoiceDB invoiceDB = mock(InvoiceDB.class);
        ProductLineDB productLineDB = mock(ProductLineDB.class);
        ProductDB productDB = mock(ProductDB.class);
        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        EmailService emailService = mock(EmailService.class);

        when(productLineDB.getProductLinesFromOrder(1)).thenReturn(auxProductLineList);
        when(invoiceDB.addInvoice(invoice, 2.90)).thenReturn(Boolean.FALSE);

        CreateInvoiceController controller2 = new CreateInvoiceController(invoiceDB, productLineDB, productDB, pharmacyDB, clientDB, emailService);

        result = controller2.createInvoice(1, purchaseOrder, 2.90);
        expResult = false;
        assertEquals(expResult, result);
    }

    @Test
    void TestgetTotalPriceFromOrder() {
        double result = controller.getTotalPriceFromOrder();
        double expResult = 10.00;
        assertEquals(expResult, result);
    }

    @Test
    void TestSendInvoiceByEmail() throws SQLException {
        Invoice invoice = new Invoice(1,1,1, "clientEmail@gmail.com", 10.00);
        PurchaseOrder purchaseOrder = new PurchaseOrder(1,1, "clientEmail@gmail.com", LocalDate.now());
        controller.getTotalPriceFromOrder();
        controller.getProductLinesFromOrder(purchaseOrder);

        boolean result = controller.sendInvoiceByEmail(invoice);
        boolean expResult = true;
        assertEquals(expResult, result);

        InvoiceDB invoiceDB = mock(InvoiceDB.class);
        ProductLineDB productLineDB = mock(ProductLineDB.class);
        ProductDB productDB = mock(ProductDB.class);
        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        EmailService emailService = mock(EmailService.class);

        Address address = new Address("testAddress",1,1,1);
        Pharmacy pharmacy = new Pharmacy(1, "testPharmacy", address);
        CreditCard creditCard = new CreditCard(1, LocalDate.now(), (short) 1);
        Client client = new Client("clientEmail@gmail.com", "qwerty", "testName", 1, creditCard, address, 1);

        ProductLine productLine1 = new ProductLine(1,1,1,5.50);
        ProductLine productLine2 = new ProductLine(1,2,1,4.50);
        Product product1 = new Product(1, "testProduct1", 1,1,1);
        Product product2 = new Product(2, "testProduct2", 1,1,1);

        when(pharmacyDB.getPhamacyByID(invoice.getPharmacyId())).thenReturn(pharmacy);
        when(clientDB.getClientByEmail(invoice.getClientEmail())).thenReturn(client);
        when(productLineDB.getProductLinesFromOrder(1)).thenReturn(auxProductLineList);
        when(productDB.getProduct(productLine1.getProductId())).thenReturn(product1);
        when(productDB.getProduct(productLine2.getProductId())).thenReturn(product2);
        when(emailService.sendEmail(client.getEmail(), "Receipt", "Receipt #1\r\n" +
                "\r\n" +
                "Pharmacy: testPharmacy\tid: 1\r\n" +
                "------------------------------------------------------------\r\n" +
                "Order:\r\n" +
                "Item                                    Number    Price     \r\n" +
                "------------------------------------------------------------\r\n" +
                "testProduct1                            1         €5,50      \r\n" +
                "------------------------------------------------------------\r\n" +
                "testProduct2                            1         €4,50      \r\n" +
                "------------------------------------------------------------\r\n" +
                "                                                  €10,00\r\n" +
                "\r\n" +
                "NIF: 1")).thenReturn(Boolean.FALSE);

        CreateInvoiceController controller2 = new CreateInvoiceController(invoiceDB, productLineDB, productDB, pharmacyDB, clientDB, emailService);
        controller2.getProductLinesFromOrder(purchaseOrder);

        expResult = false;
        result = controller2.sendInvoiceByEmail(invoice);
        assertEquals(expResult, result);
    }

    @Test
    void TestMakeEmailBody() throws SQLException {
        controller.getTotalPriceFromOrder();
        PurchaseOrder purchaseOrder = new PurchaseOrder(1,1, "clientEmail@gmail.com", LocalDate.now());
        controller.getProductLinesFromOrder(purchaseOrder);

        Invoice invoice = new Invoice(1,1,1, "clientEmail@gmail.com", 10.00);
        Address address = new Address("testAddress",1,1,1);
        Pharmacy pharmacy = new Pharmacy(1, "testPharmacy", address);

        CreditCard creditCard = new CreditCard(1, LocalDate.now(), (short) 1);
        Client client = new Client("clientEmail@gmail.com", "qwerty", "testName", 1, creditCard, address, 1);

        String expResult = "Receipt #1\r\n" +
                "\r\n" +
                "Pharmacy: testPharmacy\tid: 1\r\n" +
                "------------------------------------------------------------\r\n" +
                "Order:\r\n" +
                "Item                                    Number    Price     \r\n" +
                "------------------------------------------------------------\r\n" +
                "testProduct1                            1         €5,50      \r\n" +
                "------------------------------------------------------------\r\n" +
                "testProduct2                            1         €4,50      \r\n" +
                "------------------------------------------------------------\r\n" +
                "                                                  €10,00\r\n" +
                "\r\n" +
                "NIF: 1";

        String result = controller.makeEmailBody(invoice, pharmacy, client).toString();
        assertEquals(expResult, result);
    }

}