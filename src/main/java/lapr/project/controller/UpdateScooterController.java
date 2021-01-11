package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;

public class UpdateScooterController {

    private final ScooterDB sDB;

    public UpdateScooterController() {
        sDB = new ScooterDB();
    }

    public UpdateScooterController(ScooterDB sDB) {
        this.sDB = sDB;
    }

    public boolean updateScooter(int ids,int idp,Scooter s) {
        if (s == null) {
            return false;
        }
        return sDB.updateScooter(ids,idp,s);
    }

}
