package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;

import java.sql.SQLException;

public class UpdatePharmacyController {

    private final PharmacyDB pDB;

    public UpdatePharmacyController() {
        pDB = new PharmacyDB();
    }

    public UpdatePharmacyController(PharmacyDB pDB) {
        this.pDB = pDB;
    }

    public boolean updatePharmacy(int id, String name) throws SQLException {
        if (name == null) {
            return false;
        }
        return pDB.updatePharmacy(id, name);
    }

}
