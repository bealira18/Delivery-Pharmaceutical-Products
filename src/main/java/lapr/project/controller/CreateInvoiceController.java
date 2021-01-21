package lapr.project.controller;

import lapr.project.data.InvoiceDB;
import lapr.project.data.ProductDB;
import lapr.project.data.ProductLineDB;
import lapr.project.model.Invoice;
import lapr.project.model.ProductLine;
import lapr.project.model.PurchaseOrder;

import java.sql.SQLException;
import java.util.List;

public class CreateInvoiceController {

    private final InvoiceDB invoiceDB;
    private final ProductLineDB productLineDB;
    private List<ProductLine> productLineList;

    public CreateInvoiceController() {
        invoiceDB = new InvoiceDB();
        productLineDB = new ProductLineDB();
    }

    public CreateInvoiceController(InvoiceDB invoiceDB, ProductLineDB productLineDB) {
        this.invoiceDB = invoiceDB;
        this.productLineDB = productLineDB;
    }

    public boolean createInvoice(int idInvoice, PurchaseOrder po, double deliveryFee) throws SQLException {
        double totalPrice;
        getProductLinesFromOrder(po);
        totalPrice = getTotalPriceFromOrder();

        Invoice invoice = new Invoice(idInvoice, po.getId(), po.getPharmacyId(), po.getClientEmail(), totalPrice);
        return invoiceDB.addInvoice(invoice, deliveryFee);
    }

    public void getProductLinesFromOrder (PurchaseOrder po) throws SQLException {
        this.productLineList = productLineDB.getProductLinesFromOrder(po.getId());
    }

    public double getTotalPriceFromOrder() {
        double total = 0;
        for (ProductLine productLine : productLineList) {
            total = total + productLine.getPrice();
        }
        return total;
    }
}
