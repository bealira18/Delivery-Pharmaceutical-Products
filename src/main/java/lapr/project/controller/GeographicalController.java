package lapr.project.controller;

import java.util.List;
import lapr.project.data.AddressDB;
import lapr.project.data.PathDB;
import lapr.project.model.Address;
import lapr.project.model.Path;

public class GeographicalController {

    private final AddressDB aDB;
    private final PathDB pDB;

    public GeographicalController() {

        aDB = new AddressDB();
        pDB = new PathDB();
    }

    public GeographicalController(AddressDB aDB, PathDB pDB) {

        this.aDB = aDB;
        this.pDB = pDB;
    }

    public void addAddresses(List<Address> la) {

        for (Address a : la) {
            aDB.addAddress(a);
        }
    }

    public boolean addAddress(Address a) {

        return aDB.addAddress(a);
    }

    public List<Address> getAddresses() {

        return aDB.getAddresses();
    }

    public List<Address> getPharmacyAddresses() {

        return aDB.getPharmacyAddresses();
    }

    public List<Path> getPaths(List<Address> la) {
        return pDB.getPaths(la);
    }

    public void addPaths(List<Path> lp) {

        for (Path p : lp) {
            pDB.addPath(p);
        }
    }

    public boolean addPath(Path p) {

        return pDB.addPath(p);
    }

    public boolean updatePath(Path p) {

        return pDB.updatePath(p);
    }

    public boolean removePath(Address a1, Address a2) {

        return pDB.removePath(a1, a2);
    }

}
