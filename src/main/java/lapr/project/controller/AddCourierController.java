package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;

import java.sql.SQLException;
import java.util.List;
import lapr.project.model.RegisteredUser;

public class AddCourierController {

    private final CourierDB cDB;
    private final PharmacyDB pDB;

    public AddCourierController() {
        cDB = new CourierDB();
        pDB = new PharmacyDB();
    }

    public AddCourierController(CourierDB cDB, PharmacyDB pDB) {
        this.cDB = cDB;
        this.pDB = pDB;
    }

    public List<Pharmacy> findPharmacies() {
        return pDB.getAllPharmacies();
    }

    public boolean addCourier(RegisteredUser user, String name, int nif, long socialSecurity, int pharmacyId, double weight) throws SQLException {
        Courier courier = new Courier(user, name, nif, socialSecurity, pharmacyId, weight);
        return cDB.addCourier(courier);
    }

}
