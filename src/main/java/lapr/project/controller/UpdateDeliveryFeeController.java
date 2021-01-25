package lapr.project.controller;

import lapr.project.model.PurchaseOrder;
import lapr.project.data.SettingsHandler;

public class UpdateDeliveryFeeController {

    private final SettingsHandler sH;

    public UpdateDeliveryFeeController() {

        sH = new SettingsHandler();
    }

    public UpdateDeliveryFeeController(SettingsHandler sh) {

        sH = sh;
    }

    public double getDeliveryFee() {

        return PurchaseOrder.getDeliveryFee();
    }

    public boolean updateDeliveryFee(double fee) {

        PurchaseOrder.setDeliveryFee(fee);
        return sH.saveSettings(SettingsHandler.SETTINGS_FILE);
    }
}
