package lapr.project.controller;

import lapr.project.data.AddressDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Park;

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

    public boolean addPark(Park park) {

        if (!aDB.doesAddressExist(park.getAddress().getDescription())) {
            aDB.addAddress(park.getAddress());
        }
        return pDB.addPark(park);
    }
}
