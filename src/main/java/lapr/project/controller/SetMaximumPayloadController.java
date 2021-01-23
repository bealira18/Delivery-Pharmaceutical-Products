package lapr.project.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Courier;

public class SetMaximumPayloadController {

    public SetMaximumPayloadController() {
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
