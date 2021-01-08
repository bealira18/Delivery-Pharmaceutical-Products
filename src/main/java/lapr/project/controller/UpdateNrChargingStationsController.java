package lapr.project.controller;

import lapr.project.data.ScooterParkDB;
import lapr.project.model.ScooterPark;

import java.sql.SQLException;

public class UpdateNrChargingStationsController {

    private final ScooterParkDB spDB;

    public UpdateNrChargingStationsController(){
        spDB=new ScooterParkDB();
    }

    public UpdateNrChargingStationsController(ScooterParkDB spDB) {
        this.spDB = spDB;
    }

    public void updateNrChargingStations(ScooterPark sp) throws SQLException {
        spDB.updateNrChargingStations(sp);
    }
}
