package lapr.project.controller;

import lapr.project.data.AddressDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Park;

/**
 * Controls the process of adding a park to a pharmacy.
 *
 * @author lapr3-2020-g052
 */
public class AddParkController {

    /**
     * AddressDB instance to register the park address
     */
    private final AddressDB aDB;

    /**
     * ParkDB instance to add a new park
     */
    private final ParkDB pDB;

    /**
     * Creates a instance of AddParkController, creating the required instances.
     */
    public AddParkController() {

        aDB = new AddressDB();
        pDB = new ParkDB();
    }

    /**
     * Creates a instance of AddParkController with the given instances.
     * @param aDB AddressDB instance
     * @param pDB ParkDB instance
     */
    public AddParkController(AddressDB aDB, ParkDB pDB) {

        this.aDB = aDB;
        this.pDB = pDB;
    }

    /**
     * Adds a park to a given pharmacy and the address in the system if not already registered
     * @param park park to add
     * @return true if added, false if Park object is invalid or database failure
     */
    public boolean addPark(Park park) {

        if (!aDB.doesAddressExist(park.getAddress().getDescription())) {
            aDB.addAddress(park.getAddress());
        }
        return pDB.addPark(park);
    }
}
