package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;

import java.sql.SQLException;
import java.util.List;

public class CreateInvoiceController {

    private final InvoiceDB invoiceDB;
    private final ProductLineDB productLineDB;
    private final ProductDB productDB;
    private final PharmacyDB pharmacyDB;
    private final EmailService emailService;
    private List<ProductLine> productLineList;
    private double totalPrice;

    public CreateInvoiceController() {
        invoiceDB = new InvoiceDB();
        productLineDB = new ProductLineDB();
        productDB = new ProductDB();
        pharmacyDB = new PharmacyDB();
        emailService = new EmailService();
    }

    public CreateInvoiceController(InvoiceDB invoiceDB, ProductLineDB productLineDB, ProductDB productDB, PharmacyDB pharmacyDB, EmailService emailService) {
        this.invoiceDB = invoiceDB;
        this.productLineDB = productLineDB;
        this.productDB = productDB;
        this.pharmacyDB = pharmacyDB;
        this.emailService = emailService;
    }

    public boolean createInvoice(int idInvoice, PurchaseOrder po, double deliveryFee) throws SQLException {
        getProductLinesFromOrder(po);
        totalPrice = getTotalPriceFromOrder();

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

    public boolean sendInvoiceByEmail(Invoice invoice, Client client) throws SQLException {
        Pharmacy pharmacy = pharmacyDB.getPhamacyByID(invoice.getPharmacyId());

        String subjectLine = "Receipt";

        StringBuilder emailBody = new StringBuilder("Receipt #"+invoice.getId());
        String slash = "------------------------------------------------------------";
        emailBody.append(System.getProperty("line.separator"));
        emailBody.append(System.getProperty("line.separator"));
        emailBody.append("Pharmacy: "+pharmacy.getName()+"\tid: "+pharmacy.getId());
        emailBody.append(System.getProperty("line.separator"));
        emailBody.append(slash);
        emailBody.append(System.getProperty("line.separator"));

        emailBody.append("Order:");
        emailBody.append(System.getProperty("line.separator"));
        emailBody.append(String.format("%-40s%-10s%-10s", "Item", "Number", "Price"));
        emailBody.append(slash);
        emailBody.append(System.getProperty("line.separator"));

        for(ProductLine productLine : productLineList) {
            Product p = productDB.getProduct(productLine.getProductId());
            emailBody.append(String.format("%-40s%-10d€%-10f", p.getName(), productLine.getProductQuantity(), productLine.getPrice()));
            emailBody.append(slash);
            emailBody.append(System.getProperty("line.separator"));
        }
        emailBody.append(String.format("%50s%f", "€", totalPrice));
        emailBody.append(System.getProperty("line.separator"));
        emailBody.append(System.getProperty("line.separator"));
        emailBody.append("NIF: "+client.getNif());

        return emailService.sendEmail(client.getEmail(), subjectLine, emailBody.toString());
    }

}
