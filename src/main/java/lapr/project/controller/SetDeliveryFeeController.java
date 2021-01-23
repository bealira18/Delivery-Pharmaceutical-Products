package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.PurchaseOrder;

public class SetDeliveryFeeController {

    public SetDeliveryFeeController() {
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
