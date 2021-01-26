package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.model.Park;

public class UpdateParkController {

    private final ParkDB spDB;

    public UpdateParkController() {

        spDB = new ParkDB();
    }

    public UpdateParkController(ParkDB spDB) {

        this.spDB = spDB;
    }

    public boolean updateNrChargingStations(int parkId, int nr) {

        Park sp = spDB.getParkById(parkId);

        if (sp == null || sp.getLimit() < nr) {
            return false;
        }

        return spDB.updateChargingStations(sp);
    }

    public boolean updateParkChargingPotency(int parkId, double chargingPotency) {
        return spDB.updateParkChargingPotency(parkId, chargingPotency);
    }
}
