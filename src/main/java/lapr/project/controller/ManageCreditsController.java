/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.SettingsHandler;
import lapr.project.model.Client;

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
    
    public int addCreditsAfterPurchase(Client client, double purchaseAmount) throws SQLException {
        double ratio = getCreditConversionRatio();
        int creditsEarned = (int) (ratio * purchaseAmount);
        client.setCredits(client.getCredits() + creditsEarned);
        cDB.updateCredits(client.getEmail(), creditsEarned);
        return creditsEarned;
    }
    
    public double getCreditConversionRatio()
    {
        double ratio = Double.parseDouble(System.getProperty("client.credits.purchase.ratio", "0.0"));
        if (ratio > 1 || ratio < 0)
        {
            throw new IllegalArgumentException("The conversion ratio must be a value between 0 and 1 inclusive. Please check your configuration file.");
        }
        return ratio;
    }
    
    public void setCreditConversionRatio(double newRatio)
    {
        if (newRatio > 1 || newRatio < 0)
            throw new IllegalArgumentException("The conversion ratio must be a value between 0 and 1 inclusive.");
        
        System.setProperty("client.credits.purchase.ratio", String.valueOf(newRatio));
        sH.saveSettings(SettingsHandler.SETTINGS_FILE);
        
    }

    //recebe email do cliente retorna nº creditos
    //verifica se cliente tem mais creditos que o das properties
    //retorna 0 ou delivery fee
    public int getCreditsByClientEmail(String email) throws SQLException {

        return cDB.getCreditsByClientEmail(email);
    }

    public boolean updateCreditsClient(String email, int newClientsAmount) throws SQLException {

        return cDB.updateCreditsClient(email, newClientsAmount);
    }

    /*public boolean payDeliveryFee(String clientEmail) throws SQLException {
        int clientCredits = getCreditsByClientEmail(clientEmail);
        int creditsToPayDeliveryFee = getCreditValueDeliveryFee();

        if(clientCredits >= creditsToPayDeliveryFee) {
            int newCredits = clientCredits - creditsToPayDeliveryFee;
            updateCreditsClient(clientEmail, newCredits);
            return true;
        }
        return false;
    }*/
}
