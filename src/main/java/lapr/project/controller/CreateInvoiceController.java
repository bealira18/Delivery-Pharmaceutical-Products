package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;

import java.util.List;
import java.util.Locale;
import lapr.project.utils.Constants;

public class CreateInvoiceController {

    /**
     * InvoiceDB instance to add invoice
     */
    private final InvoiceDB invoiceDB;

    /**
     * ProductLineDB instance to get product lines from an order
     */
    private final ProductLineDB productLineDB;

    /**
     * ProductDB to get products from an order
     */
    private final ProductDB productDB;

    /**
     * PharmacyDB to get pharmacy purchased from
     */
    private final PharmacyDB pharmacyDB;

    /**
     * ClientDB to get the client
     */
    private final ClientDB clientDB;

    /**
     * EmailService instance to send emails
     */
    private final EmailService emailService;

    /**
     * ManageCreditsController instance to mange a client's credits
     */
    private final ManageCreditsController manageCreditsController;

    /**
     * SettingsHandler instance to to save settings
     */
    private final SettingsHandler sH;

    /**
     * List of product lines to save the products from a purchase
     */
    private List<ProductLine> productLineList;

    /**
     * Total price form a purchase order
     */
    private double totalPrice;

    /**
     * Creates a instance of CreateInvoiceController, creating the required instances.
     */
    public CreateInvoiceController() {

        invoiceDB = new InvoiceDB();
        productLineDB = new ProductLineDB();
        productDB = new ProductDB();
        pharmacyDB = new PharmacyDB();
        clientDB = new ClientDB();
        emailService = new EmailService();
        manageCreditsController = new ManageCreditsController();
        sH = new SettingsHandler();
    }

    /**
     * Creates a instance of CreateInvoiceController with the given instances.
     * @param invoiceDB InvoiceDB instance
     * @param productLineDB ProductLineDB instance
     * @param productDB ProductDB instance
     * @param pharmacyDB PharmacyDB instance
     * @param clientDB ClientDB instance
     * @param emailService EmailService instance
     * @param manageCreditsController ManageCreditsController instance
     * @param sH SettingHandler instance
     */
    public CreateInvoiceController(InvoiceDB invoiceDB, ProductLineDB productLineDB, ProductDB productDB,
            PharmacyDB pharmacyDB, ClientDB clientDB, EmailService emailService,
            ManageCreditsController manageCreditsController, SettingsHandler sH) {

        this.invoiceDB = invoiceDB;
        this.productLineDB = productLineDB;
        this.productDB = productDB;
        this.pharmacyDB = pharmacyDB;
        this.clientDB = clientDB;
        this.emailService = emailService;
        this.manageCreditsController = manageCreditsController;
        this.sH = sH;
    }

    /**
     * Creates an invoice and adds it to the system
     * @param idInvoice invoice id
     * @param po purchase order
     * @return Invoice object added, null if Invoice object is invalid or database failure
     */
    public Invoice createInvoice(int idInvoice, PurchaseOrder po) {

        double deliveryFee = 0;
        getProductLinesFromOrder(po);
        totalPrice = getTotalPriceFromOrder();
        double iva = getIVA()+1;

        UpdateDeliveryFeeController updateDeliveryFeeController = new UpdateDeliveryFeeController();

        if (!manageCreditsController.payDeliveryFee(po.getClientEmail())) {
            deliveryFee = updateDeliveryFeeController.getDeliveryFee();
        }

        manageCreditsController.addCreditsAfterPurchase(po.getClientEmail(), totalPrice);

        double priceWithoutIVA = (totalPrice + deliveryFee) / iva;

        Invoice invoice = new Invoice(idInvoice, po.getId(), po.getPharmacyId(), po.getClientEmail(), deliveryFee, totalPrice, priceWithoutIVA);
        if (!invoiceDB.addInvoice(invoice)) {
            return null;
        }
        return invoice;
    }

    /**
     * Gets product lines from order and saves it on productLineList
     * @param po purchase order
     */
    public void getProductLinesFromOrder(PurchaseOrder po) {

        this.productLineList = productLineDB.getProductLinesFromOrder(po.getId());
    }

    /**
     * Gets total price from a purchase order
     * @return total price
     */
    public double getTotalPriceFromOrder() {

        totalPrice = 0;
        for (ProductLine productLine : productLineList) {
            totalPrice = totalPrice + productLine.getPrice();
        }
        return totalPrice;
    }

    /**
     * Sends invoice by email
     * @param invoice invoice
     * @return true if email sent, false otherwise
     */
    public boolean sendInvoiceByEmail(Invoice invoice) {

        Pharmacy pharmacy = pharmacyDB.getPhamacyByID(invoice.getPharmacyId());
        Client client = clientDB.getClientByEmail(invoice.getClientEmail());

        String subjectLine = "Receipt";

        StringBuilder emailBody = makeEmailBody(invoice, pharmacy, client);

        return emailService.sendEmail("clientemen0652@gmail.com", subjectLine, emailBody.toString());
    }

    /**
     * Builds the email body based on invoice information
     * @param invoice invoice
     * @param pharmacy pharmacy bought from
     * @param client client
     * @return StringBuilder with email body
     */
    public StringBuilder makeEmailBody(Invoice invoice, Pharmacy pharmacy, Client client) {

        StringBuilder emailBody = new StringBuilder("Receipt #" + invoice.getId());
        String slash = "------------------------------------------------------------";
        emailBody.append(System.getProperty(Constants.LINE_BREAK));
        emailBody.append(System.getProperty(Constants.LINE_BREAK));
        emailBody.append("Pharmacy: ").append(pharmacy.getName()).append("\tid: ").append(pharmacy.getId());
        emailBody.append(System.getProperty(Constants.LINE_BREAK));
        emailBody.append(slash);
        emailBody.append(System.getProperty(Constants.LINE_BREAK));

        emailBody.append("Order:");
        emailBody.append(System.getProperty(Constants.LINE_BREAK));
        emailBody.append(String.format("%-40s%-10s%-10s", "Item", "Number", "Price"));
        emailBody.append(System.getProperty(Constants.LINE_BREAK));
        emailBody.append(slash);
        emailBody.append(System.getProperty(Constants.LINE_BREAK));

        for (ProductLine productLine : productLineList) {
            Product p = productDB.getProduct(productLine.getProductId());
            emailBody.append(String.format(Locale.ROOT, "%-40s%-10d€%-10.2f", p.getName(), productLine.getProductQuantity(), productLine.getPrice()));
            emailBody.append(System.getProperty(Constants.LINE_BREAK));
            emailBody.append(slash);
            emailBody.append(System.getProperty(Constants.LINE_BREAK));
        }
        emailBody.append(String.format(Locale.ROOT, "%51s%.2f", "€", totalPrice));
        emailBody.append(System.getProperty(Constants.LINE_BREAK));
        emailBody.append("Delivery fee: ").append(String.format(Locale.ROOT, "%.2f", invoice.getDeliveryFee())).append("€");
        emailBody.append(System.getProperty(Constants.LINE_BREAK));
        emailBody.append("Total: ").append(String.format(Locale.ROOT, "%.2f", invoice.getDeliveryFee() + totalPrice)).append("€");
        emailBody.append(System.getProperty(Constants.LINE_BREAK));
        emailBody.append(String.format(Locale.ROOT, "Total w/o IVA: €%-10.2fIVA: %.0f%%", invoice.getNoVATprice(), getIVA()*100));
        emailBody.append(System.getProperty(Constants.LINE_BREAK));
        emailBody.append("NIF: ").append(client.getNif());

        return emailBody;
    }

    /**
     * Gets the VAT defined in properties
     * @return
     */
    public double getIVA() {

        return Invoice.getIVA();
    }

    /**
     * Updates the VAT in properties and saves it
     * @param iva new VAT
     * @return true if saved, false otherwise
     */
    public boolean updateIVA(double iva) {

        Invoice.setIVA(iva);
        return sH.saveSettings(SettingsHandler.SETTINGS_FILE);
    }
}
