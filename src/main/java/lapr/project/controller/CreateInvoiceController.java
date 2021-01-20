package lapr.project.controller;

import lapr.project.data.InvoiceDB;
import lapr.project.model.Invoice;
import lapr.project.model.PurchaseOrder;

public class CreateInvoiceController {

    private final InvoiceDB invoiceDB;

    public CreateInvoiceController() {
        invoiceDB = new InvoiceDB();
    }

    public CreateInvoiceController(InvoiceDB invoiceDB) {
        this.invoiceDB = invoiceDB;
    }

    public boolean createInvoice(int idInvoice, PurchaseOrder po, double deliveryFee) {
        double totalPrice = 0;

        Invoice invoice = new Invoice(idInvoice, po.getId(), po.getPharmacyId(), po.getClientEmail(), totalPrice);
        return invoiceDB.addInvoice(invoice, deliveryFee);
    }
}
