package lapr.project.controller;

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

    public boolean addScooter(Scooter scooter) {

        if (parkDB.getNumberOfScootersInPharmacy(scooter.getIdPharmacy()) < parkDB.getLimitVehiclesPark(scooter.getIdPharmacy(), "scooter")) {
            return scooterDB.addScooter(scooter, 200, 200);
        }
        return false;
    }
}
