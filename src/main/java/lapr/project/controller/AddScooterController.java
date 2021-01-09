package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;

public class AddScooterController {
    private final ScooterDB scooterDB;

    public AddScooterController(){
        scooterDB = new ScooterDB();
    }

    public AddScooterController(ScooterDB scooterDB) {
        this.scooterDB = scooterDB;
    }

    public boolean addScooter(Scooter scooter) throws SQLException {

        return scooterDB.addScooter(scooter);
    }
}
