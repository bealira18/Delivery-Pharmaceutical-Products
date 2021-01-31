package lapr.project.controller;

import lapr.project.model.PurchaseOrder;
import lapr.project.data.SettingsHandler;

/**
 * Controls the update process of the delivery fee.
 * 
 * @author lapr3-2020-g052
 */
public class UpdateDeliveryFeeController {

    /**
     * SettingsHandler instance to save settings.
     */
    private final SettingsHandler sH;

    /**
     * Creates a instance of UpdateDeliveryFeeController, creating the required 
     * instances.
     */
    public UpdateDeliveryFeeController() {

        sH = new SettingsHandler();
    }

    /**
     * Creates a instance of UpdateDeliveryFeeController with the given instances.
     * @param sh SettingsHandler instance.
     */
    public UpdateDeliveryFeeController(SettingsHandler sh) {

        sH = sh;
    }

    /**
     * Retrieves the delivery fee.
     * @return double containing the delivery fee value.
     */
    public double getDeliveryFee() {

        return PurchaseOrder.getDeliveryFee();
    }

    /**
     * Sets a new delivery fee and saves it on the configuration file.
     * 
     * @param fee new delivery fee.
     * @return true if successful, false if it fails to save the value on the 
     * configuration file.
     */
    public boolean updateDeliveryFee(double fee) {

        PurchaseOrder.setDeliveryFee(fee);
        return sH.saveSettings(SettingsHandler.SETTINGS_FILE);
    }
}
