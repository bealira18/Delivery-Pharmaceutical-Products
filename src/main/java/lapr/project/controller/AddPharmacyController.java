package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;

/**
 * Controls the process of adding a pharmacy to the system.
 *
 * @author lapr3-2020-g052
 */
public class AddPharmacyController {

    /**
     * PharmacyDB instance to add a product to the system
     */
    private final PharmacyDB pDB;

    /**
     * Creates a instance of AddPharmacyController, creating the required instances.
     */
    public AddPharmacyController() {

        pDB = new PharmacyDB();
    }

    /**
     * Creates a instance of AddPharmacyController with the given instances.
     * @param pDB PharmacyDB instance
     */
    public AddPharmacyController(PharmacyDB pDB) {

        this.pDB = pDB;
    }

    /**
     * Adds a new pharmacy to the system
     * @param a pharmacy address
     * @param p pharmacy name
     * @param limitScooterPark scooter park limit
     * @param limitDronePark drone park limit
     * @return true if added, false if Pharmacy object is invalid or database failure
     */
    public boolean addPharmacy(Address a, Pharmacy p, int limitScooterPark, int limitDronePark) {

        return pDB.addPharmacy(a, p, limitScooterPark, limitDronePark);
    }
}
