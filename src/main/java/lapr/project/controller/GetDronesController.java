package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.model.Drone;

import java.util.List;

/**
 * Controls the process of getting the drones available to make a delivery.
 *
 * @author lapr3-2020-g052
 */
public class GetDronesController {

    /**
     * DroneDB instance to get the available drones
     */
    private final DroneDB dDB;

    /**
     * Creates a instance of GetDronesController, creating the required instances.
     */
    public GetDronesController() {

        dDB = new DroneDB();
    }

    /**
     * Creates a instance of GetDronesController with the given instances.
     * @param dDB DroneDB instance
     */
    public GetDronesController(DroneDB dDB) {

        this.dDB = dDB;
    }

    /**
     * Gets a list with the available drones to make a delivery
     * @param orderId order to be delivered
     * @return list with available drones
     */
    public List<Drone> getDrones(int orderId) {

        return dDB.getAllAvailableDrones(orderId);
    }
}
