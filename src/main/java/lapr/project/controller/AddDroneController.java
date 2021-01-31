package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Drone;

/**
 * Controls the process of adding a drone.
 *
 * @author lapr3-2020-g052
 */
public class AddDroneController {

    /**
     * DroneDB instance to add a drone
     */
    private final DroneDB droneDB;

    /**
     * ParkDB instance to verify the limits of the park and number of drones
     */
    private final ParkDB parkDB;

    /**
     * Creates a instance of AddDroneController, creating the required instances.
     */
    public AddDroneController() {

        droneDB = new DroneDB();
        parkDB = new ParkDB();
    }

    /**
     * Creates a instance of AddDroneController with the given instances.
     *
     * @param droneDB droneDB instance
     * @param parkDB parkDB instance
     */
    public AddDroneController(DroneDB droneDB, ParkDB parkDB) {

        this.droneDB = droneDB;
        this.parkDB = parkDB;
    }

    /**
     * Adds a drone.
     * @param drone drone
     * @return true if added, false if Drone or Park objects are invalid or a database failure
     */
    public boolean addDrone(Drone drone) {

        if (parkDB.getNumberOfDronesInPharmacy(drone.getIdPharmacy()) < parkDB.getLimitVehiclesPark(drone.getIdPharmacy(), "drone")) {
            return droneDB.addDrone(drone,200,200);
        }
        return false;
    }
}
