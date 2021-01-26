package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;

import java.util.List;
import java.util.Locale;
import lapr.project.utils.Constants;

public class CreateInvoiceController {

    private final InvoiceDB invoiceDB;
    private final ProductLineDB productLineDB;
    private final ProductDB productDB;
    private final PharmacyDB pharmacyDB;
    private final ClientDB clientDB;
    private final EmailService emailService;
    private final ManageCreditsController manageCreditsController;
    private final SettingsHandler sH;
    private List<ProductLine> productLineList;
    private double totalPrice;

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

    public void getProductLinesFromOrder(PurchaseOrder po) {

        this.productLineList = productLineDB.getProductLinesFromOrder(po.getId());
    }

    public double getTotalPriceFromOrder() {

        totalPrice = 0;
        for (ProductLine productLine : productLineList) {
            totalPrice = totalPrice + productLine.getPrice();
        }
        return totalPrice;
    }

    public boolean sendInvoiceByEmail(Invoice invoice) {

        Pharmacy pharmacy = pharmacyDB.getPhamacyByID(invoice.getPharmacyId());
        Client client = clientDB.getClientByEmail(invoice.getClientEmail());

        String subjectLine = "Receipt";

        StringBuilder emailBody = makeEmailBody(invoice, pharmacy, client);

        return emailService.sendEmail("clientemen0652@gmail.com", subjectLine, emailBody.toString());
    }

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

    public double getIVA() {

        return Invoice.getIVA();
    }

    public boolean updateIVA(double iva) {

        Invoice.setIVA(iva);
        return sH.saveSettings(SettingsHandler.SETTINGS_FILE);
    }
}
