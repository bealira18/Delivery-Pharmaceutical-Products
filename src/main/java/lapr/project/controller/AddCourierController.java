package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;

import java.sql.SQLException;
import java.util.List;

public class AddCourierController {

    private final CourierDB cDB;
    private final PharmacyDB pDB;

    public AddCourierController() {
        cDB = new CourierDB();
        pDB = new PharmacyDB();
    }

    public List<Pharmacy> findPharmacies() {
        return pDB.getAllPharmacies();
    }

    public boolean addCourier(String email, String name, int nif, int socialSecurity, int pharmacyId) throws SQLException {
        Courier courier = new Courier(email, name, nif, socialSecurity, pharmacyId);
        return cDB.addCourier(courier);
    }

}
