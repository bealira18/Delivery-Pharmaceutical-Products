package lapr.project.controller;

import lapr.project.data.ScooterParkDB;
import lapr.project.model.ScooterPark;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateNrChargingStationsController {

    private final ScooterParkDB spDB;

    public UpdateNrChargingStationsController() {
        spDB = new ScooterParkDB();
    }

    public UpdateNrChargingStationsController(ScooterParkDB spDB) {
        this.spDB = spDB;
    }

    public boolean updateNrChargingStations(int nr) {
        try {
            ScooterPark.setNumChargingStations(nr);
            return true;

        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ScooterPark.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
