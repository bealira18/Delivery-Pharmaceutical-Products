package lapr.project.controller;

import lapr.project.data.DroneDB;
import lapr.project.model.Drone;

import java.util.List;

public class GetDronesController {

    private final DroneDB dDB;

    public GetDronesController() {

        dDB = new DroneDB();
    }

    public GetDronesController(DroneDB dDB) {

        this.dDB = dDB;
    }

    public List<Drone> getDrones(int orderId) {

        return dDB.getAllAvailableDrones(orderId);
    }
}
