package lapr.project.controller;

import lapr.project.data.DeliveryDB;
import lapr.project.data.ProductDB;
import lapr.project.data.ProductLineDB;
import lapr.project.model.Product;
import lapr.project.model.ProductLine;
import lapr.project.model.PurchaseOrder;

import java.util.ArrayList;
import java.util.List;

public class CreateDeliveryController {

    private final DeliveryDB deliveryDB;
    private final ProductLineDB productLineDB;
    private final ProductDB productDB;

    public CreateDeliveryController() {
        deliveryDB = new DeliveryDB();
        productLineDB = new ProductLineDB();
        productDB = new ProductDB();
    }

    public CreateDeliveryController(DeliveryDB deliveryDB, ProductLineDB productLineDB, ProductDB productDB) {
        this.deliveryDB = deliveryDB;
        this.productLineDB = productLineDB;
        this.productDB = productDB;
    }

    public boolean createDeliveries(List<PurchaseOrder> orderList, int deliveryRun) {
        boolean check = false;
        for(PurchaseOrder purchaseOrder : orderList) {
            if(deliveryDB.createNewDelivery(purchaseOrder.getId(), deliveryRun)) {
                check = true;
            }
        }
        return check;
    }

    public boolean deliveryRunAssociateVehicle(int idVehicle, int deliveryRun) {
        return deliveryDB.deliveryRunAssociateVehicle(idVehicle, deliveryRun);
    }

    public boolean deliveryRunAssociateCourier(String emailCourier, int deliveryRun) {
        return deliveryDB.deliveryRunAssociateCourier(emailCourier, deliveryRun);
    }

    public List<PurchaseOrder> getDeliveryRunProducts(int deliveryRun) {
        List<PurchaseOrder> purchaseOrderList;

        purchaseOrderList = deliveryDB.getPurchaseOrdersByDeliveryRun(deliveryRun);

        return purchaseOrderList;
    }

    public List<ProductLine> getProductLinesFromDeliveryRun(List<PurchaseOrder> purchaseOrderList) {
        List<ProductLine> productLineList = new ArrayList<>();

        for(PurchaseOrder purchaseOrder : purchaseOrderList) {
            List<ProductLine> productLinesFromOneOrder = productLineDB.getProductLinesFromOrder(purchaseOrder.getId());
            for(ProductLine productLine : productLinesFromOneOrder) {
                productLineList.add(productLine);
            }
        }
        return productLineList;
    }

    public List<Product> getProductsFromDeliveryRun(List<ProductLine> productLineList) {
        List<Product> productList = new ArrayList<>();

        for(ProductLine productLine : productLineList) {
            Product product = productDB.getProduct(productLine.getProductId());
            for(int i = 0; i < productLine.getProductQuantity(); i++) {
                productList.add(product);
            }
        }

        return productList;
    }
}
