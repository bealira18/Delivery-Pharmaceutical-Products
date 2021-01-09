package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.ScooterDB;
import lapr.project.data.ScooterParkDB;
import lapr.project.model.Scooter;

public class AddScooterController {
    private final ScooterDB scooterDB;
    private final ScooterParkDB scooterParkDB;

    public AddScooterController(){
        scooterDB = new ScooterDB();
        scooterParkDB = new ScooterParkDB();
    }

    public AddScooterController(ScooterDB scooterDB, ScooterParkDB scooterParkDB) {
        this.scooterDB = scooterDB;
        this.scooterParkDB = scooterParkDB;
    }

    public boolean addScooter(Scooter scooter) throws SQLException {

        if(scooterParkDB.getNumberOfScootersInPharmacy(scooter.getIdPharmacy()) < scooterParkDB.getLimitScooterPark(scooter.getIdPharmacy())) {
            return scooterDB.addScooter(scooter);
        }
        return false;
    }
}
