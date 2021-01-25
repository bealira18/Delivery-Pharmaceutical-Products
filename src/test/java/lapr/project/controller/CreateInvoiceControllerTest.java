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

        Invoice invoice = new Invoice(1,1,1, "clientEmail@gmail.com", 2.90, 10.00);

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
        ManageCreditsController manageCreditsController = mock(ManageCreditsController.class);

        when(invoiceDB.addInvoice(invoice)).thenReturn(Boolean.TRUE);
        when(productLineDB.getProductLinesFromOrder(1)).thenReturn(auxProductLineList);
        when(productDB.getProduct(productLine1.getProductId())).thenReturn(product1);
        when(productDB.getProduct(productLine2.getProductId())).thenReturn(product2);
        when(pharmacyDB.getPhamacyByID(invoice.getPharmacyId())).thenReturn(pharmacy);
        when(clientDB.getClientByEmail(invoice.getClientEmail())).thenReturn(client);
        when(emailService.sendEmail("clientemen0652@gmail.com", "Receipt", "Receipt #1" + System.getProperty("line.separator") +
                System.getProperty("line.separator") +
                "Pharmacy: testPharmacy\tid: 1"+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "Order:"+ System.getProperty("line.separator") +
                "Item                                    Number    Price     "+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "testProduct1                            1         €5.50      "+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "testProduct2                            1         €4.50      "+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "                                                  €10.00"+ System.getProperty("line.separator") +
                "Delivery fee: 2.90€" +
                System.getProperty("line.separator") +
                "Total: 12.90€" +
                System.getProperty("line.separator") +
                "NIF: 1")).thenReturn(Boolean.TRUE);
        when(manageCreditsController.payDeliveryFee(purchaseOrder.getClientEmail())).thenReturn(Boolean.TRUE);
        
        SettingsHandler sh = mock(SettingsHandler.class);
        
        when(sh.saveSettings(SettingsHandler.SETTINGS_FILE)).thenReturn(true);

        controller = new CreateInvoiceController();
        controller = new CreateInvoiceController(invoiceDB, productLineDB, productDB, pharmacyDB, clientDB, emailService, manageCreditsController, sh);
        controller.getProductLinesFromOrder(purchaseOrder);

    }

    @Test
    void testCreateInvoice() throws SQLException {
        PurchaseOrder purchaseOrder = new PurchaseOrder(1,1, "clientEmail@gmail.com", LocalDate.now());
        Invoice invoice = new Invoice(1,1,1, "clientEmail@gmail.com", 2.90, 10.00);
        UpdateDeliveryFeeController updateDeliveryFeeController = new UpdateDeliveryFeeController();
        updateDeliveryFeeController.updateDeliveryFee(2.90);

        Invoice expResult = new Invoice(1,1,1, "clientEmail@gmail.com", 2.90, 10.00);
        Invoice result = controller.createInvoice(1, purchaseOrder);
        assertEquals(expResult, result);


        InvoiceDB invoiceDB = mock(InvoiceDB.class);
        ProductLineDB productLineDB = mock(ProductLineDB.class);
        ProductDB productDB = mock(ProductDB.class);
        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        EmailService emailService = mock(EmailService.class);
        ManageCreditsController manageCreditsController = mock(ManageCreditsController.class);
        SettingsHandler sh = mock(SettingsHandler.class);

        when(productLineDB.getProductLinesFromOrder(1)).thenReturn(auxProductLineList);
        when(invoiceDB.addInvoice(invoice)).thenReturn(Boolean.FALSE);
        when(manageCreditsController.payDeliveryFee(purchaseOrder.getClientEmail())).thenReturn(Boolean.FALSE);

        CreateInvoiceController controller2 = new CreateInvoiceController(invoiceDB, productLineDB, productDB, pharmacyDB, clientDB, emailService, manageCreditsController, sh);

        result = controller2.createInvoice(1, purchaseOrder);
        assertNull(result);
    }

    @Test
    void TestgetTotalPriceFromOrder() {
        double result = controller.getTotalPriceFromOrder();
        double expResult = 10.00;
        assertEquals(expResult, result);
    }

    @Test
    void TestSendInvoiceByEmail() throws SQLException {
        Invoice invoice = new Invoice(1,1,1, "clientEmail@gmail.com", 2.90, 10.00);
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
        ManageCreditsController manageCreditsController = mock(ManageCreditsController.class);
        SettingsHandler sh = mock(SettingsHandler.class);
        UpdateDeliveryFeeController updateDeliveryFeeController = new UpdateDeliveryFeeController();

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
        when(emailService.sendEmail("clientemen0652@gmail.com", "Receipt", "Receipt #1" + System.getProperty("line.separator") +
                System.getProperty("line.separator") +
                "Pharmacy: testPharmacy\tid: 1"+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "Order:"+ System.getProperty("line.separator") +
                "Item                                    Number    Price     "+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "testProduct1                            1         €5.50      "+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "testProduct2                            1         €4.50      "+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "                                                  €10.00"+ System.getProperty("line.separator") +
                "Delivery fee: 2.90€" +
                System.getProperty("line.separator") +
                "Total: 12.90€" +
                System.getProperty("line.separator") +
                "NIF: 1")).thenReturn(Boolean.FALSE);

        CreateInvoiceController controller2 = new CreateInvoiceController(invoiceDB, productLineDB, productDB, pharmacyDB, clientDB, emailService, manageCreditsController, sh);
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

        Invoice invoice = new Invoice(1,1,1, "clientEmail@gmail.com", 2.90, 10.00);
        Address address = new Address("testAddress",1,1,1);
        Pharmacy pharmacy = new Pharmacy(1, "testPharmacy", address);

        CreditCard creditCard = new CreditCard(1, LocalDate.now(), (short) 1);
        Client client = new Client("clientEmail@gmail.com", "qwerty", "testName", 1, creditCard, address, 1);

        String expResult = "Receipt #1" + System.getProperty("line.separator") +
                System.getProperty("line.separator") +
                "Pharmacy: testPharmacy\tid: 1"+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "Order:"+ System.getProperty("line.separator") +
                "Item                                    Number    Price     "+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "testProduct1                            1         €5.50      "+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "testProduct2                            1         €4.50      "+ System.getProperty("line.separator") +
                "------------------------------------------------------------"+ System.getProperty("line.separator") +
                "                                                  €10.00"+ System.getProperty("line.separator") +
                "Delivery fee: 2.90€" +
                System.getProperty("line.separator") +
                "Total: 12.90€" +
                System.getProperty("line.separator") +
                "NIF: 1";

        String result = controller.makeEmailBody(invoice, pharmacy, client).toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIVA method, of class CreateInvoiceController.
     */
    @Test
    public void testGetIVA() {
        System.out.println("getIVA");
        System.setProperty("invoice.iva", "2.0");
        double expResult = 2.0;
        double result = controller.getIVA();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of updateDeliveryFee method, of class CreateInvoiceController.
     */
    @Test
    public void testUpdateIVA() {
        System.out.println("updateDeliveryFee");
        double iva = 5.0;
        boolean bResult = controller.updateIVA(iva);
        double result = Double.parseDouble(System.getProperty("invoice.iva"));
        assertEquals(iva, result);
        assertEquals(true, bResult);
        
        SettingsHandler sh = mock(SettingsHandler.class);
        
        when(sh.saveSettings(SettingsHandler.SETTINGS_FILE)).thenReturn(false);
        
        
        InvoiceDB invoiceDB = mock(InvoiceDB.class);
        ProductLineDB productLineDB = mock(ProductLineDB.class);
        ProductDB productDB = mock(ProductDB.class);
        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        ClientDB clientDB = mock(ClientDB.class);
        EmailService emailService = mock(EmailService.class);
        ManageCreditsController manageCreditsController = mock(ManageCreditsController.class);
        CreateInvoiceController controller2 = new CreateInvoiceController(invoiceDB, productLineDB, productDB, pharmacyDB, clientDB, emailService, manageCreditsController, sh);
        bResult = controller2.updateIVA(iva);
        assertEquals(false, bResult);
        
        final double iva2 = -1.0;
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            controller.updateIVA(iva2);
        });
        assertEquals("Invalid Numeric Value (Negative IVA)", ex.getMessage());
    }

}