package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.model.Drone;

import lapr.project.data.SettingsHandler;

/**
 * Controls the process of updating information for a drone.
 * 
 * @author lapr3-2020-g052
 */
public class UpdateDroneController {

    /**
     * DroneDB instance to save the new scooter information
     */
    private final DroneDB dDB;

    /**
     * SettingsHandler instance to save settings.
     */
    private final SettingsHandler sH;

    /**
     * Creates a instance of UpdateDroneController, creating the required 
     * instances.
     */
    public UpdateDroneController() {

        dDB = new DroneDB();
        sH = new SettingsHandler();
    }

    /**
     * Creates a instance of UpdateDroneController with the given instances.
     * @param sH SettingsHandler instance.
     * @param dDB DroneDB instance.
     */
    public UpdateDroneController(DroneDB dDB, SettingsHandler sH) {

        this.dDB = dDB;
        this.sH = sH;
    }

    /**
     * Updates a given drone.
     * @param idd the id of the target drone to be updated.
     * @param d the Drones new parameters.
     * @return true on succes, false if Drone object is invalid or a database
     * failure.
     */
    public boolean updateDrone(int idd, Drone d) {

        if (d == null) {
            return false;
        }
        return dDB.updateDrone(idd, d);
    }

    /**
     * Retrieves the maximum payload value for drone transportation.
     * @return double containing the drone maximum paayload.
     */
    public double getDroneMaxPayload() {

        return Drone.getDroneMaxPayload();
    }

    /**
     * Sets a new maximum payload value for drone transportation and saves it
     * on the configuration file.
     * @param droneMaxPayload new drone maximum payload.
     * @return true if successful, false if it fails to save the value on the 
     * configuration file.
     */
    public boolean updateDroneMaxPayload(double droneMaxPayload) {

        Drone.setDroneMaxPayload(droneMaxPayload);
        return sH.saveSettings(SettingsHandler.SETTINGS_FILE);
    }
}
