package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.Pharmacy;

public class AddPharmacyController {

    private final PharmacyDB pDB;

    public AddPharmacyController() {

        pDB = new PharmacyDB();
    }

    public AddPharmacyController(PharmacyDB pDB) {

        this.pDB = pDB;
    }

    public boolean addPharmacy(Address a, Pharmacy p, int limitScooterPark, int limitDronePark) {

        return pDB.addPharmacy(a, p, limitScooterPark, limitDronePark);
    }
}
