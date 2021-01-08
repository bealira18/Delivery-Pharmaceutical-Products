package lapr.project.controller;

import lapr.project.data.ScooterParkDB;
import lapr.project.model.ScooterPark;

import java.sql.SQLException;

public class ScooterParkController {

    private final ScooterParkDB spDB;

    public ScooterParkController(){
        spDB=new ScooterParkDB();
    }

    public ScooterParkController(ScooterParkDB spDB) {
        this.spDB = spDB;
    }

    public void updateNrChargingStations(ScooterPark sp) throws SQLException {
        spDB.updateNrChargingStations(sp);
    }

}
