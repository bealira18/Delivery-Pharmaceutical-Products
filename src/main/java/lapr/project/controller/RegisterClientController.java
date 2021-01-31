package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.model.Address;
import lapr.project.model.CreditCard;

/**
 * Controls the process of registering a Client.
 *
 * @author lapr3-2020-g052
 */
public class RegisterClientController {

    /**
     * ClientDB instance to register a client
     */
    private final ClientDB cDB;

    /**
     * Creates a instance of RegisterClientController with the given instances.
     * @param cDB ClientDB instance
     */
    public RegisterClientController(ClientDB cDB) {
        this.cDB = cDB;
    }

    /**
     * Creates a instance of RegisterClientController, creating the required instances.
     */
    public RegisterClientController() {
        cDB = new ClientDB();
    }

    /**
     * Adds a client.
     * @param email email
     * @param password password
     * @param name name
     * @param nif nif
     * @param creditCard credit card
     * @param address address
     * @return true if added, false if Client object is invalid or a database failure
     */
    public boolean addClient(String email, String password, String name, int nif,
            CreditCard creditCard, Address address) {

        return cDB.addClient(email, password, name, nif, creditCard.getCreditCardNumber(),
                creditCard.getValidityDate(), creditCard.getCcv(), address.getDescription(),
                address.getLatitude(), address.getLongitude(), address.getAltitude());
    }
}
