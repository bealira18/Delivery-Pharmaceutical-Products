package lapr.project.controller;

import lapr.project.data.AddressDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Park;

import java.sql.SQLException;

public class AddParkController {

    private final AddressDB aDB;
    private final ParkDB pDB;

    public AddParkController() {
        aDB = new AddressDB();
        pDB = new ParkDB();
    }

    public AddParkController(AddressDB aDB, ParkDB pDB) {
        this.aDB = aDB;
        this.pDB = pDB;
    }

    public boolean addPark(Park p) throws SQLException {
        if(!aDB.doesAddressExist(p.getAddress().getDescription())) {
            aDB.addAddress(p.getAddress());
        }
        return pDB.addPark(p);
    }
}
