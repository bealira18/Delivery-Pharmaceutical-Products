package lapr.project.controller;

import lapr.project.data.PharmacyDB;

/**
 * Controls the process of updating information for a Pharmacy.
 *
 * @author lapr3-2020-g052
 */
public class UpdatePharmacyController {

    /**
     * PharmacyDB instance to save the new pharmacy information
     */
    private final PharmacyDB pDB;

    /**
     * Creates a instance of UpdatePharamcyController, creating the required instances.
     */
    public UpdatePharmacyController() {

        pDB = new PharmacyDB();
    }

    /**
     * Creates a instance of UpdatePharamcyController with the given instances.
     * @param pDB PharmacyDB instance
     */
    public UpdatePharmacyController(PharmacyDB pDB) {

        this.pDB = pDB;
    }

    /**
     * Updates a given pharmacy.
     * @param id the id of the target pharmacy to be updated.
     * @param name the new pharamcy name.
     * @return true on succes, false if Pharmacy object is invalid or a database failure.
     */
    public boolean updatePharmacy(int id, String name) {

        if (name == null) {
            return false;
        }
        return pDB.updatePharmacy(id, name);
    }
}
