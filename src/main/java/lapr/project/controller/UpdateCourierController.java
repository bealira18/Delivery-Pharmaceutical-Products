package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.model.Courier;

/**
 * Controls the process of updating information for a Courier.
 *
 * @author lapr3-2020-g052
 */
public class UpdateCourierController {

    /**
     * CourierDB instance to save the new courier information
     */
    private final CourierDB cDB;

    /**
     * Creates a instance of UpdateCourierController, creating the required instances.
     */
    public UpdateCourierController() {

        cDB = new CourierDB();
    }

    /**
     * Creates a instance of UpdateCourierController with the given instances.
     * @param cDB CourierDB instance
     */
    public UpdateCourierController(CourierDB cDB) {

        this.cDB = cDB;
    }

    /**
     * Updates a given courier.
     * @param email the email of the target courier to be updated.
     * @param c the courier new parameters.
     * @return true on succes, false if Courier object is invalid or a database failure.
     */
    public boolean updateCourier(String email, Courier c) {

        if (c == null) {
            return false;
        }
        return cDB.updateCourier(email, c);
    }
}
