package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.ScooterDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Scooter;

public class AddScooterController {

    private final ScooterDB scooterDB;
    private final ParkDB parkDB;

    public AddScooterController() {
        scooterDB = new ScooterDB();
        parkDB = new ParkDB();
    }

    public AddScooterController(ScooterDB scooterDB, ParkDB parkDB) {
        this.scooterDB = scooterDB;
        this.parkDB = parkDB;
    }

    public boolean addScooter(Scooter scooter) throws SQLException {

        if (parkDB.getNumberOfScootersInPharmacy(scooter.getIdPharmacy()) < parkDB.getLimitScooterPark(scooter.getIdPharmacy())) {
            return scooterDB.addScooter(scooter);
        }
        return false;
    }
}
