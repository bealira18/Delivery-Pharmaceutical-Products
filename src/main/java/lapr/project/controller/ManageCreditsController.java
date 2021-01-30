package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.SettingsHandler;

/**
 * Controls all interactions related to credits.
 * 
 * @author lapr3-2020-g052
 */
public class ManageCreditsController {

    /**
     * SettingsHandler instance to save settings.
     */
    private final SettingsHandler sH;

    /**
     * ClientDB instance for obtaining the credits information of a client.
     */
    private final ClientDB cDB;

    /**
     * Creates a instance of ManageCreditsController, creating the required 
     * instances.
     */
    public ManageCreditsController() {

        sH = new SettingsHandler();
        cDB = new ClientDB();
    }

    /**
     * Creates a instance of ManageCreditsController with the given instances.
     * @param sH SettingsHandler instance.
     * @param cDB ClientDB instance.
     */
    public ManageCreditsController(SettingsHandler sH, ClientDB cDB) {

        this.sH = sH;
        this.cDB = cDB;
    }

    /**
     * Updates a clients credits amount after a purchase.
     * 
     * @param clientEmail the email of the client.
     * @param purchaseAmount the purchase value.
     * @return int containing the number of credits earned.
     */
    public int addCreditsAfterPurchase(String clientEmail, double purchaseAmount) {

        double ratio = getCreditConversionRatio();
        int creditsEarned = (int) (ratio * purchaseAmount);
        cDB.updateCredits(clientEmail, creditsEarned);
        return creditsEarned;
    }

    /**
     * Allows a client to pay a delivery fee using his earned credits.
     * 
     * @param clientEmail the emial of the client
     * @return true if succesfull, false if not enough credits to pay for the
     * delivery fee.
     */
    public boolean payDeliveryFee(String clientEmail) {

        int clientCredits = getCreditsByClientEmail(clientEmail);
        int creditsToPayDeliveryFee = getCreditValueDeliveryFee();

        if (clientCredits >= creditsToPayDeliveryFee) {
            int newCredits = clientCredits - creditsToPayDeliveryFee;
            return updateCreditsClient(clientEmail, newCredits);
        }
        return false;
    }

    /**
     * Retrieves the number of credits of a client.
     * 
     * @param email the email of the client
     * @return int with the number of credits the client has.
     */
    public int getCreditsByClientEmail(String email) {

        return cDB.getCreditsByClientEmail(email);
    }

    /**
     * Updates the credit amount of a client on the databse.
     *
     * @param email the email of the client.
     * @param newClientsAmount the new amount of credits.
     * @return true on success, false on failure.
     */
    public boolean updateCreditsClient(String email, int newClientsAmount) {

        return cDB.updateCreditsClient(email, newClientsAmount);
    }

    /**
     * Retrieves the ratio for the amount of credits to give based on a purchase value.
     *
     * @return double containing the ratio or throws a IllegalArgumentException
     * if the value is invalid or out of bounds.
     */
    public double getCreditConversionRatio() {

        double ratio = Double.parseDouble(System.getProperty("client.credits.purchase.ratio", "0.0"));

        if (ratio > 1 || ratio < 0) {
            throw new IllegalArgumentException("The conversion ratio must be a value between 0 and 1 inclusive. Please check your configuration file.");
        }
        return ratio;
    }

    /**
     * Sets a new ratio for the amount of credits to give based on a purchase value
     * and saves it on the configuration file.
     *
     * @param newRatio new purchase value to credits ratio.
     */
    public void setCreditConversionRatio(double newRatio) {

        if (newRatio > 1 || newRatio < 0) {
            throw new IllegalArgumentException("The conversion ratio must be a value between 0 and 1 inclusive.");
        }
        System.setProperty("client.credits.purchase.ratio", String.valueOf(newRatio));
        sH.saveSettings(SettingsHandler.SETTINGS_FILE);

    }

    /**
     * Retrieves the amount of credits required to pay the delivery fee with credits.
     * 
     * @return double containing the amount of credits required or throws a
     * IllegalArgumentException if the value is invalid or out of bounds.
     */
    public int getCreditValueDeliveryFee() {

        int credits = Integer.parseInt(System.getProperty("client.credits.delivery.fee.payment"));

        if (credits <= 0) {
            throw new IllegalArgumentException("The amount of credits to pay a delivery fee cannot be negative or zero. Please check your configuration file.");
        }
        return credits;
    }

    /**
     * Sets a new amount of credits required to pay the delivery fee with credits
     * and saves it to the configuration fiel
     * 
     * @param newCredits the new amount of credits required to pay for the delivery
     * fee.
     */
    public void setCreditValueDeliveryFee(int newCredits) {

        if (newCredits <= 0) {
            throw new IllegalArgumentException("The amount of credits to pay a delivery fee cannot be negative or zero.");
        }
        System.setProperty("client.credits.delivery.fee.payment", String.valueOf(newCredits));
        sH.saveSettings(SettingsHandler.SETTINGS_FILE);
    }
}
