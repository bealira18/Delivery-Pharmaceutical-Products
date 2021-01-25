package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.model.Park;

public class UpdateNrChargingStationsController {

    private final ParkDB spDB;

    public UpdateNrChargingStationsController() {

        spDB = new ParkDB();
    }

    public UpdateNrChargingStationsController(ParkDB spDB) {

        this.spDB = spDB;
    }

    public boolean updateNrChargingStations(int parkId, int nr) {

        Park sp = spDB.getParkById(parkId);

        if (sp == null || sp.getLimit() < nr) {
            return false;
        }
        sp.setNumChargingStations(nr);

        return spDB.updateChargingStations(sp);
    }
}
