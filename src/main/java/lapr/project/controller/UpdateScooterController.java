package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;

import lapr.project.data.SettingsHandler;

/**
 * Controls the process of updating information for a scooter.
 * 
 * @author lapr3-2020-g052
 */
public class UpdateScooterController {

    /**
     * ScooterDB instance to save the new scooter information
     */
    private final ScooterDB sDB;

    /**
     * SettingsHandler instance to save settings.
     */
    private final SettingsHandler sH;

    /**
     * Creates a instance of UpdateScooterController, creating the required 
     * instances.
     */
    public UpdateScooterController() {

        sDB = new ScooterDB();
        sH = new SettingsHandler();
    }

    /**
     * Creates a instance of UpdateScooterController with the given instances.
     * @param sH SettingsHandler instance.
     * @param sDB ScooterDB instance.
     */
    public UpdateScooterController(ScooterDB sDB, SettingsHandler sH) {

        this.sDB = sDB;
        this.sH = sH;
    }

    /**
     * Updates a given scooter.
     * @param ids the id of the target scooter to be updated.
     * @param s the Scooters new parameters.
     * @return true on succes, false if Scooter object is invalid or a database
     * failure.
     */
    public boolean updateScooter(int ids, Scooter s) {

        if (s == null) {
            return false;
        }
        return sDB.updateScooter(ids, s);
    }

    /**
     * Retrieves the maximum payload value for scooter transportation.
     * @return double containing the Scooter maximum paayload.
     */
    public double getScooterMaxPayload() {

        return Scooter.getScooterMaxPayload();
    }

    /**
     * Sets a new maximum payload value for scooter transportation and saves it
     * on the configuration file.
     * @param scooterMaxPayload new scooter maximum payload.
     * @return true if successful, false if it fails to save the value on the 
     * configuration file.
     */
    public boolean updateScooterMaxPayload(double scooterMaxPayload) {

        Scooter.setScooterMaxPayload(scooterMaxPayload);
        return sH.saveSettings(SettingsHandler.SETTINGS_FILE);
    }
}
