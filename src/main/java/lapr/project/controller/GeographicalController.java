package lapr.project.controller;

import java.sql.SQLException;
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

    public void addAddress(Address a) {

        aDB.addAddress(a);
    }

    public List<Address> getAddresses() throws SQLException {

        return aDB.getAddresses();
    }

    public List<Address> getPharmacyAddresses() throws SQLException {

        return aDB.getPharmacyAddresses();
    }

    public void addPaths(List<Path> lp) {

        for (Path p : lp) {
            pDB.addPath(p);
        }
    }

    public void addPath(Path p) {

        pDB.addPath(p);
    }

    public void updatePath(Path p) {

        pDB.updatePath(p);
    }

    public void removePath(Address a1, Address a2) {

        pDB.removePath(a1, a2);
    }
}
