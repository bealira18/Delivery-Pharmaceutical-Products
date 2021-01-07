package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Pharmacy;

public class PharmacyController {

    private final PharmacyDB p_db;

    public PharmacyController() {

        p_db = new PharmacyDB();
    }

    public PharmacyController(PharmacyDB p_db) {

        this.p_db = p_db;
    }

    public boolean addPharmacy(Pharmacy p, int limit) throws SQLException {

        return p_db.addPharmacy(p, limit);
    }
}
