package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Pharmacy;

public class PharmacyController {

    private final PharmacyDB pDB;

    public PharmacyController() {

        pDB = new PharmacyDB();
    }

    public PharmacyController(PharmacyDB pDB) {

        this.pDB = pDB;
    }

    public boolean addPharmacy(Pharmacy p, int limit) throws SQLException {

        return pDB.addPharmacy(p, limit);
    }
}
