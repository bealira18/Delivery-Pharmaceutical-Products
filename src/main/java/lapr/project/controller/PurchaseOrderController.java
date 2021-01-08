package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.PurchaseOrderDB;
import lapr.project.model.PurchaseOrder;

public class PurchaseOrderController {

    private final PurchaseOrderDB pOrdDB;

    public PurchaseOrderController() {

        pOrdDB = new PurchaseOrderDB();
    }

    public PurchaseOrderController(PurchaseOrderDB pOrdDB) {

        this.pOrdDB = pOrdDB;
    }

    public boolean setDeliveryFee(double deliveryFee) {

        try {
            PurchaseOrder.setDeliveryFee(deliveryFee);
            return true;

        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PurchaseOrder.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
