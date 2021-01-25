package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;

import lapr.project.data.SettingsHandler;

public class UpdateScooterController {

    private final ScooterDB sDB;
    private final SettingsHandler sH;

    public UpdateScooterController() {

        sDB = new ScooterDB();
        sH = new SettingsHandler();
    }

    public UpdateScooterController(ScooterDB sDB, SettingsHandler sH) {

        this.sDB = sDB;
        this.sH = sH;
    }

    public boolean updateScooter(int ids, Scooter s) {

        if (s == null) {
            return false;
        }
        return sDB.updateScooter(ids, s);
    }

    public double getScooterMaxPayload() {

        return Scooter.getScooterMaxPayload();
    }

    public boolean updateScooterMaxPayload(double scooterMaxPayload) {

        Scooter.setScooterMaxPayload(scooterMaxPayload);
        return sH.saveSettings(SettingsHandler.SETTINGS_FILE);
    }
}
