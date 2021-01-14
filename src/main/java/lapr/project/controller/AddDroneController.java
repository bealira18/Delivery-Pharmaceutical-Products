package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Drone;

import java.sql.SQLException;

public class AddDroneController {

    private final DroneDB droneDB;
    private final ParkDB parkDB;

    public AddDroneController() {
        droneDB = new DroneDB();
        parkDB = new ParkDB();
    }

    public AddDroneController(DroneDB droneDB, ParkDB parkDB) {
        this.droneDB = droneDB;
        this.parkDB = parkDB;
    }

    public boolean addDrone(Drone drone) throws SQLException {

        if (parkDB.getNumberOfVehiclesInPharmacy(drone.getIdPharmacy(), "drone") < parkDB.getLimitVehiclesPark(drone.getIdPharmacy(), "drone")) {
            return droneDB.addDrone(drone);
        }
        return false;
    }
}
