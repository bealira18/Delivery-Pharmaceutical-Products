package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.PurchaseOrderDB;
import lapr.project.model.PurchaseOrder;

public class SetDeliveryFeeController {

    private final PurchaseOrderDB pOrdDB;

    public SetDeliveryFeeController() {

        pOrdDB = new PurchaseOrderDB();
    }

    public SetDeliveryFeeController(PurchaseOrderDB pOrdDB) {

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
