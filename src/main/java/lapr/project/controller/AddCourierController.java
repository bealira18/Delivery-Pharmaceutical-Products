package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;

import java.util.List;

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

    public boolean addCourier(String email, String password, String name, int nif, long socialSecurity,
            int pharmacyId, double weight) {

        Courier courier = new Courier(email, password, name, nif, socialSecurity, pharmacyId, weight);
        return cDB.addCourier(courier);
    }
}
