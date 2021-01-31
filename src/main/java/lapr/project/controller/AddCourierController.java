package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;

import java.util.List;

/**
 * Controls the process of adding a courier.
 *
 * @author lapr3-2020-g052
 */
public class AddCourierController {

    /**
     * CourierDB instance to add a courier
     */
    private final CourierDB cDB;

    /**
     * PharmacyDB instance to get registered pharmacies
     */
    private final PharmacyDB pDB;

    /**
     * Creates a instance of AddCourierController, creating the required instances.
     */
    public AddCourierController() {

        cDB = new CourierDB();
        pDB = new PharmacyDB();
    }

    /**
     * Creates a instance of AddCourierController with the given instances.
     * @param cDB CourierDB instance
     * @param pDB pharmacyDB instance
     */
    public AddCourierController(CourierDB cDB, PharmacyDB pDB) {

        this.cDB = cDB;
        this.pDB = pDB;
    }

    /**
     * Gets a list with all the pharmacies registered in the system
     * @return list with all pharmacies
     */
    public List<Pharmacy> findPharmacies() {

        return pDB.getAllPharmacies();
    }

    /**
     * Register a courier in the system
     * @param email the email of the courier.
     * @param password the password of the courier.
     * @param name the name of the courier.
     * @param nif the nif of the courier.
     * @param socialSecurity the social security number of the courier.
     * @param pharmacyId the pharmacy ID associated to the courier.
     * @param weight the weight of the courier.
     * @return true if added, false if Courier is invalid or database failure
     */
    public boolean addCourier(String email, String password, String name, int nif, long socialSecurity,
            int pharmacyId, double weight) {

        Courier courier = new Courier(email, password, name, nif, socialSecurity, pharmacyId, weight);
        return cDB.addCourier(courier);
    }
}
