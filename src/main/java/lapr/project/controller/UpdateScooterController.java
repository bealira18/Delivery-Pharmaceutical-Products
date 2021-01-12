package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;

import java.sql.SQLException;

public class UpdateScooterController {

    private final ScooterDB sDB;

    public UpdateScooterController() {
        sDB = new ScooterDB();
    }

    public UpdateScooterController(ScooterDB sDB) {
        this.sDB = sDB;
    }

    public boolean updateScooter(int ids,Scooter s) throws SQLException {
        if (s == null) {
            return false;
        }
        return sDB.updateScooter(ids,s);
    }

}
