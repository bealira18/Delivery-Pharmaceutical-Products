package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Drone;

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

    public boolean addDrone(Drone drone) {

        if (parkDB.getNumberOfDronesInPharmacy(drone.getIdPharmacy()) < parkDB.getLimitVehiclesPark(drone.getIdPharmacy(), "drone")) {
            return droneDB.addDrone(drone,200,200);
        }
        return false;
    }
}
