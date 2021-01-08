package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.CourierDB;
import lapr.project.model.Courier;

public class CourierController {

    private final CourierDB cDB;

    public CourierController() {

        cDB = new CourierDB();
    }

    public CourierController(CourierDB cDB) {

        this.cDB = cDB;
    }

    public boolean setMaximumPayload(int maximumPayload) {

        try {
            Courier.setMaximumPayload(maximumPayload);
            return true;

        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Courier.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
