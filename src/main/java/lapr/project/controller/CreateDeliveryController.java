package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;

import java.util.ArrayList;
import java.util.List;

public class CreateDeliveryController {

    /**
     * DeliveryDB instance to manage deliveries and delivery runs
     */
    private final DeliveryDB deliveryDB;

    /**
     * ProductLineDB instance to get product lines from a delivery
     */
    private final ProductLineDB productLineDB;

    /**
     * ProductDB to get products from a delivery
     */
    private final ProductDB productDB;

    /**
     * ScooterDB to associate a scooter to a delivery run
     */
    private final ScooterDB scooterDB;

    /**
     * DroneDB to associate a drone to a delivery run
     */
    private final DroneDB droneDB;

    /**
     * Creates a instance of CreateDeliveryController, creating the required instances.
     */
    public CreateDeliveryController() {
        deliveryDB = new DeliveryDB();
        productLineDB = new ProductLineDB();
        productDB = new ProductDB();
        scooterDB = new ScooterDB();
        droneDB = new DroneDB();
    }

    /**
     * Creates a instance of CreateDeliveryController with the given instances.
     * @param deliveryDB DeliveryDB instance
     * @param productLineDB ProductLineDB instance
     * @param productDB ProductDB instance
     * @param scooterDB ScooterDB instance
     * @param droneDB DroneDB instance
     */
    public CreateDeliveryController(DeliveryDB deliveryDB, ProductLineDB productLineDB, ProductDB productDB, ScooterDB scooterDB, DroneDB droneDB) {
        this.deliveryDB = deliveryDB;
        this.productLineDB = productLineDB;
        this.productDB = productDB;
        this.scooterDB = scooterDB;
        this.droneDB = droneDB;
    }

    /**
     * Creates a delivery run
     * @param orderList purchase orders to be delivered
     * @param deliveryRun delivery run id
     * @return true is deliveries added, false if Delivery object is invalid or database failure
     */
    public boolean createDeliveries(List<PurchaseOrder> orderList, int deliveryRun) {
        boolean check = false;
        for(PurchaseOrder purchaseOrder : orderList) {
            if(deliveryDB.createNewDelivery(purchaseOrder.getId(), deliveryRun)) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Associates a vehicle to a delivery run
     * @param idVehicle vehicle id
     * @param deliveryRun delivery run id
     * @return true if vehicle added, false if database failure
     */
    public boolean deliveryRunAssociateVehicle(int idVehicle, int deliveryRun) {
        return deliveryDB.deliveryRunAssociateVehicle(idVehicle, deliveryRun);
    }

    /**
     * Associates a courier to a delivery run
     * @param emailCourier courier email
     * @param deliveryRun delivery run id
     * @return true if courier added, false if database failure
     */
    public boolean deliveryRunAssociateCourier(String emailCourier, int deliveryRun) {
        return deliveryDB.deliveryRunAssociateCourier(emailCourier, deliveryRun);
    }

    /**
     * Gets the scooter with the highest battery from a pharmacy
     * @param pharmacyId pharmacy id
     * @return Scooter object
     */
    public Scooter getHighestBatteryScooter(int pharmacyId) {
        return scooterDB.getHighestBatteryScooter(pharmacyId);
    }

    /**
     * Gets the drone with the highest battery from a pharmacy
     * @param pharmacyId pharmacy id
     * @return Drone object
     */
    public Drone getHighestBatteryDrone(int pharmacyId) {
        return droneDB.getHighestBatteryDrone(pharmacyId);
    }

    /**
     * Gets purchase orders from delivery run
     * @param deliveryRun delivery run id
     * @return purchase order list
     */
    public List<PurchaseOrder> getPurchaseOrdersFromDeliveryRun(int deliveryRun) {
        List<PurchaseOrder> purchaseOrderList;

        purchaseOrderList = deliveryDB.getPurchaseOrdersByDeliveryRun(deliveryRun);

        return purchaseOrderList;
    }

    /**
     * Gets the product lines form a list of purchase orders
     * @param purchaseOrderList purchase order list
     * @return list of product lines
     */
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

    /**
     * Gets products from a list of product lines
     * @param productLineList product line list
     * @return product list
     */
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
