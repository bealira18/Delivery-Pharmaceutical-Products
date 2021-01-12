package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.model.Drone;

import java.sql.SQLException;

public class UpdateDroneController {

    private final DroneDB dDB;

    public UpdateDroneController() {
        dDB = new DroneDB();
    }

    public UpdateDroneController(DroneDB dDB) {
        this.dDB = dDB;
    }

    public boolean updateDrone(int idd, Drone d) throws SQLException {
        if (d == null) {
            return false;
        }
        return dDB.updateDrone(idd,d);
    }

}
