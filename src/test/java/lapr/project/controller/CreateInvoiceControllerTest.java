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

        PurchaseOrder purchaseOrder = new PurchaseOrder(1,1, "clientEmail@gmail.com", LocalDate.now());

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

        controller = new CreateInvoiceController();
        controller = new CreateInvoiceController(invoiceDB, productLineDB, productDB, pharmacyDB, clientDB, emailService);
        controller.getProductLinesFromOrder(purchaseOrder);

        StringBuilder stringBuilder = controller.makeEmailBody(invoice, pharmacy, client);

        when(emailService.sendEmail(client.getEmail(), "Receipt", stringBuilder.toString())).thenReturn(Boolean.TRUE);

    }

    @Test
    void createInvoice() throws SQLException {
        PurchaseOrder purchaseOrder = new PurchaseOrder(1,1, "clientEmail@gmail.com", LocalDate.now());

        boolean result = true;
        boolean expResult = controller.createInvoice(1, purchaseOrder, 2.90);
        assertEquals(result, expResult);
    }

    @Test
    void getProductLinesFromOrder() {
    }

    @Test
    void getTotalPriceFromOrder() {
    }

    @Test
    void sendInvoiceByEmail() {
    }
}