package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.model.Park;

import java.sql.SQLException;

public class UpdateNrChargingStationsController {

    private final ParkDB spDB;

    public UpdateNrChargingStationsController() {
        spDB = new ParkDB();
    }

    public UpdateNrChargingStationsController(ParkDB spDB) {
        this.spDB = spDB;
    }

    @SuppressWarnings("null")
    public boolean updateNrChargingStations(int parkId, int nr) throws SQLException {

        Park sp = spDB.getParkById(parkId);

        if(sp==null)
            return false;

        if(sp.getLimit()<nr)
            return false;

        sp.setNumChargingStations(nr);

        return spDB.updateChargingStations(sp);
    }
}
