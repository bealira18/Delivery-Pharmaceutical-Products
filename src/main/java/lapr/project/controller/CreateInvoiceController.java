package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;

import java.sql.SQLException;
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
    private final UpdateDeliveryFeeController updateDeliveryFeeController;
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
        updateDeliveryFeeController = new UpdateDeliveryFeeController();
    }

    public CreateInvoiceController(InvoiceDB invoiceDB, ProductLineDB productLineDB, ProductDB productDB, PharmacyDB pharmacyDB,
                                   ClientDB clientDB, EmailService emailService, ManageCreditsController manageCreditsController,
                                   UpdateDeliveryFeeController updateDeliveryFeeController) {
        this.invoiceDB = invoiceDB;
        this.productLineDB = productLineDB;
        this.productDB = productDB;
        this.pharmacyDB = pharmacyDB;
        this.clientDB = clientDB;
        this.emailService = emailService;
        this.manageCreditsController = manageCreditsController;
        this.updateDeliveryFeeController = updateDeliveryFeeController;
    }

    public boolean createInvoice(int idInvoice, PurchaseOrder po) throws SQLException {
        double deliveryFee = 0;
        getProductLinesFromOrder(po);
        totalPrice = getTotalPriceFromOrder();

        if(manageCreditsController.payDeliveryFee(po.getClientEmail())) {
            deliveryFee = updateDeliveryFeeController.getDeliveryFee();
        }

        manageCreditsController.addCreditsAfterPurchase(po.getClientEmail(), totalPrice);

        Invoice invoice = new Invoice(idInvoice, po.getId(), po.getPharmacyId(), po.getClientEmail(), totalPrice);
        return invoiceDB.addInvoice(invoice, deliveryFee);
    }

    public void getProductLinesFromOrder (PurchaseOrder po) throws SQLException {
        this.productLineList = productLineDB.getProductLinesFromOrder(po.getId());
    }

    public double getTotalPriceFromOrder() {
        totalPrice = 0;
        for (ProductLine productLine : productLineList) {
            totalPrice = totalPrice + productLine.getPrice();
        }
        return totalPrice;
    }

    public boolean sendInvoiceByEmail(Invoice invoice) throws SQLException {
        Pharmacy pharmacy = pharmacyDB.getPhamacyByID(invoice.getPharmacyId());
        Client client = clientDB.getClientByEmail(invoice.getClientEmail());

        String subjectLine = "Receipt";

        StringBuilder emailBody = makeEmailBody(invoice, pharmacy, client);

        return emailService.sendEmail("clientemen0652@gmail.com", subjectLine, emailBody.toString());
    }

    public StringBuilder makeEmailBody(Invoice invoice, Pharmacy pharmacy, Client client) throws SQLException {
        StringBuilder emailBody = new StringBuilder("Receipt #"+invoice.getId());
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

        for(ProductLine productLine : productLineList) {
            Product p = productDB.getProduct(productLine.getProductId());
            emailBody.append(String.format(Locale.ROOT, "%-40s%-10d€%-10.2f", p.getName(), productLine.getProductQuantity(), productLine.getPrice()));
            emailBody.append(System.getProperty(Constants.LINE_BREAK));
            emailBody.append(slash);
            emailBody.append(System.getProperty(Constants.LINE_BREAK));
        }
        emailBody.append(String.format(Locale.ROOT, "%51s%.2f", "€", totalPrice));
        emailBody.append(System.getProperty(Constants.LINE_BREAK));
        emailBody.append(System.getProperty(Constants.LINE_BREAK));
        emailBody.append("NIF: ").append(client.getNif());

        return emailBody;
    }

}
