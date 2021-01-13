package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Drone;

import java.sql.SQLException;

public class addDroneController {

    private final DroneDB droneDB;
    private final ParkDB parkDB;

    public addDroneController() {
        droneDB = new DroneDB();
        parkDB = new ParkDB();
    }

    public addDroneController(DroneDB droneDB, ParkDB parkDB) {
        this.droneDB = droneDB;
        this.parkDB = parkDB;
    }

    public boolean addDrone(Drone drone) throws SQLException {

        if (parkDB.getNumberOfDronesInPharmacy(drone.getIdPharmacy()) < parkDB.getLimitDronePark(drone.getIdPharmacy())) {
            return droneDB.addDrone(drone);
        }
        return false;
    }
}
