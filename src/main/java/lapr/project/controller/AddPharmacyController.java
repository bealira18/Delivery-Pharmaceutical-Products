package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Pharmacy;

public class AddPharmacyController {

    private final PharmacyDB pDB;

    public AddPharmacyController() {

        pDB = new PharmacyDB();
    }

    public AddPharmacyController(PharmacyDB pDB) {

        this.pDB = pDB;
    }

    public boolean addPharmacy(Pharmacy p, int limit) throws SQLException {

        return pDB.addPharmacy(p, limit);
    }
}
