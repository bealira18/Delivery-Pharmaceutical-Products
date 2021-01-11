package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.model.Address;
import lapr.project.model.CreditCard;

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
