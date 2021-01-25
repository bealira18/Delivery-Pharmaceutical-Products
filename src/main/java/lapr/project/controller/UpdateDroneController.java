package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.model.Drone;

import lapr.project.data.SettingsHandler;

public class UpdateDroneController {

    private final DroneDB dDB;
    private final SettingsHandler sH;

    public UpdateDroneController() {

        dDB = new DroneDB();
        sH = new SettingsHandler();
    }

    public UpdateDroneController(DroneDB dDB, SettingsHandler sH) {

        this.dDB = dDB;
        this.sH = sH;
    }

    public boolean updateDrone(int idd, Drone d) {

        if (d == null) {
            return false;
        }
        return dDB.updateDrone(idd, d);
    }

    public double getDroneMaxPayload() {

        return Drone.getDroneMaxPayload();
    }

    public boolean updateDroneMaxPayload(double droneMaxPayload) {

        Drone.setDroneMaxPayload(droneMaxPayload);
        return sH.saveSettings(SettingsHandler.SETTINGS_FILE);
    }
}
