/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.SettingsHandler;

import java.sql.SQLException;

/**
 *
 * @author Ricardo
 */
public class ManageCreditsController {

    private final SettingsHandler sH;
    private final ClientDB cDB;

    public ManageCreditsController(SettingsHandler sH, ClientDB cDB) {
        this.sH = sH;
        this.cDB = cDB;
    }

    public ManageCreditsController() {
        sH = new SettingsHandler();
        cDB = new ClientDB();
    }

    public int addCreditsAfterPurchase(String clientEmail, double purchaseAmount) throws SQLException {
        double ratio = getCreditConversionRatio();
        int creditsEarned = (int) (ratio * purchaseAmount);
        cDB.updateCredits(clientEmail, creditsEarned);
        return creditsEarned;
    }

    public boolean payDeliveryFee(String clientEmail) throws SQLException {
        int clientCredits = getCreditsByClientEmail(clientEmail);
        int creditsToPayDeliveryFee = getCreditValueDeliveryFee();

        if(clientCredits >= creditsToPayDeliveryFee) {
            int newCredits = clientCredits - creditsToPayDeliveryFee;
            return updateCreditsClient(clientEmail, newCredits);
        }
        return false;
    }

    public int getCreditsByClientEmail(String email) throws SQLException {

        return cDB.getCreditsByClientEmail(email);
    }

    public boolean updateCreditsClient(String email, int newClientsAmount) throws SQLException {

        return cDB.updateCreditsClient(email, newClientsAmount);
    }

    public double getCreditConversionRatio() {

        double ratio = Double.parseDouble(System.getProperty("client.credits.purchase.ratio", "0.0"));

        if (ratio > 1 || ratio < 0) {
            throw new IllegalArgumentException("The conversion ratio must be a value between 0 and 1 inclusive. Please check your configuration file.");
        }
        return ratio;
    }

    public void setCreditConversionRatio(double newRatio) {

        if (newRatio > 1 || newRatio < 0) {
            throw new IllegalArgumentException("The conversion ratio must be a value between 0 and 1 inclusive.");
        }
        System.setProperty("client.credits.purchase.ratio", String.valueOf(newRatio));
        sH.saveSettings(SettingsHandler.SETTINGS_FILE);

    }

    public int getCreditValueDeliveryFee() {

        int credits = Integer.parseInt(System.getProperty("client.credits.delivery.fee.payment"));

        if (credits <= 0) {
            throw new IllegalArgumentException("The amount of credits to pay a delivery fee cannot be negative or zero. Please check your configuration file.");
        }
        return credits;
    }

    public void setCreditValueDeliveryFee(int newCredits) {

        if (newCredits <= 0) {
            throw new IllegalArgumentException("The amount of credits to pay a delivery fee cannot be negative or zero.");
        }
        System.setProperty("client.credits.delivery.fee.payment", String.valueOf(newCredits));
        sH.saveSettings(SettingsHandler.SETTINGS_FILE);
    }

}
