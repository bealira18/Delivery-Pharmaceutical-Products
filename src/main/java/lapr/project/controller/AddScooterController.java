package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Scooter;

/**
 * Controls the process of adding a scooter to a pharmacy fleet.
 *
 * @author lapr3-2020-g052
 */
public class AddScooterController {

    /**
     * ScooterDB instance to add a scooter to a pharmacy
     */
    private final ScooterDB scooterDB;

    /**
     * ParkDB instance to see if the scooter can be added
     */
    private final ParkDB parkDB;

    /**
     * Creates a instance of AddScooterController, creating the required instances.
     */
    public AddScooterController() {

        scooterDB = new ScooterDB();
        parkDB = new ParkDB();
    }

    /**
     * Creates a instance of AddScooterController with the given instances.
     * @param scooterDB ScooterDB instance
     * @param parkDB ParkDB instance
     */
    public AddScooterController(ScooterDB scooterDB, ParkDB parkDB) {

        this.scooterDB = scooterDB;
        this.parkDB = parkDB;
    }

    /**
     * Adds a scooter to a given pharmacy if it is withing park limits
     * @param scooter scooter to add
     * @return true if added, false if database failure or park limit exceeded
     */
    public boolean addScooter(Scooter scooter) {

        if (parkDB.getNumberOfScootersInPharmacy(scooter.getIdPharmacy()) < parkDB.getLimitVehiclesPark(scooter.getIdPharmacy(), "scooter")) {
            return scooterDB.addScooter(scooter, 200, 200);
        }
        return false;
    }
}
