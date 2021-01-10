/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.time.LocalDate;
import lapr.project.data.ClientDB;
import lapr.project.model.Address;
import lapr.project.model.CreditCard;

/**
 *
 * @author Ricardo
 */
public class RegisterClientController {

    private final ClientDB cDB;

    public RegisterClientController(ClientDB cDB) {
        this.cDB = cDB;
    }

    public RegisterClientController() {
        cDB = new ClientDB();
    }

    public boolean addClient(String email, String password, String name, int nif, CreditCard creditCard, Address address) {
        return cDB.addClient(email, password, name, nif, creditCard.getCreditCardNumber(), creditCard.getValidityDate(), creditCard.getCcv(), address.getDescription(), address.getLatitude(), address.getLongitude(), address.getAltitude());
    }

}
