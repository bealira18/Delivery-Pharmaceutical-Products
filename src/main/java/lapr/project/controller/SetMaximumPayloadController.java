package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.CourierDB;
import lapr.project.model.Courier;

public class SetMaximumPayloadController {

    private final CourierDB cDB;

    public SetMaximumPayloadController() {

        cDB = new CourierDB();
    }

    public SetMaximumPayloadController(CourierDB cDB) {

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
