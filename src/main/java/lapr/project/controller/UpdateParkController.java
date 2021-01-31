package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.model.Park;

/**
 * Controls the process of updating information for a Park.
 *
 * @author lapr3-2020-g052
 */
public class UpdateParkController {

    /**
     * ParkDB instance to save the new park information
     */
    private final ParkDB spDB;

    /**
     * Creates a instance of UpdateParkController, creating the required instances.
     */
    public UpdateParkController() {

        spDB = new ParkDB();
    }

    /**
     * Creates a instance of UpdateParkController with the given instances.
     * @param spDB ParkDB instance
     */
    public UpdateParkController(ParkDB spDB) {

        this.spDB = spDB;
    }

    /**
     * Updates the number of charging stations in a park
     * @param parkId park id
     * @param nr numeber of charging stations
     * @return true if updated, fase if Park object is invalid or nr greater than park limit or database failure
     */
    public boolean updateNrChargingStations(int parkId, int nr) {

        Park sp = spDB.getParkById(parkId);

        if (sp == null || sp.getLimit() < nr) {
            return false;
        }

        return spDB.updateChargingStations(sp);
    }

    /**
     * Updates the maximum charging potency of a park
     * @param parkId park id
     * @param chargingPotency new charging potency
     * @return true if updated, false if database failure
     */
    public boolean updateParkChargingPotency(int parkId, double chargingPotency) {
        return spDB.updateParkChargingPotency(parkId, chargingPotency);
    }
}
