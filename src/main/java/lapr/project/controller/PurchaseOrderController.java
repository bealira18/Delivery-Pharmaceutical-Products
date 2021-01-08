package lapr.project.controller;

import lapr.project.model.PurchaseOrder;

public class PurchaseOrderController {

    public PurchaseOrderController() {

    }

    public boolean setDeliveryFee(double deliveryFee) {

        try {
            PurchaseOrder.setDeliveryFee(deliveryFee);
            return true;

        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            return false;
        }
    }
}
