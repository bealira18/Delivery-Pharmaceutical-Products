package lapr.project.controller;

import lapr.project.data.PharmacyDB;

public class UpdatePharmacyController {

    private final PharmacyDB pDB;

    public UpdatePharmacyController() {

        pDB = new PharmacyDB();
    }

    public UpdatePharmacyController(PharmacyDB pDB) {

        this.pDB = pDB;
    }

    public boolean updatePharmacy(int id, String name) {

        if (name == null) {
            return false;
        }
        return pDB.updatePharmacy(id, name);
    }
}
