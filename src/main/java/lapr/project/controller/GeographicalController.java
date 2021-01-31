package lapr.project.controller;

import java.util.List;
import lapr.project.data.AddressDB;
import lapr.project.data.PathDB;
import lapr.project.model.Address;
import lapr.project.model.Path;

/**
 * Controls the processes pertinent to geographical point and path manipulation
 * and creation.
 *
 * @author lapr3-2020-g052
 */
public class GeographicalController {

    /**
     * The AddressDB instance, in order to access the pertinent Database.
     */
    private final AddressDB aDB;
    /**
     * The PathDB instance, in order to access the pertinent Database.
     */
    private final PathDB pDB;

    /**
     * Creates an instance of a GeographicalController.
     */
    public GeographicalController() {

        aDB = new AddressDB();
        pDB = new PathDB();
    }

    /**
     * Creates an instance of a GeographicalController with the Database
     * instances relevant to this object received as parameters.
     *
     * @param aDB
     * @param pDB
     */
    public GeographicalController(AddressDB aDB, PathDB pDB) {

        this.aDB = aDB;
        this.pDB = pDB;
    }

    /**
     * Initializes the process of adding a set of addresses, received as
     * parameters, to the Database.
     *
     * @param la the list of addresses to be added.
     */
    public void addAddresses(List<Address> la) {

        for (Address a : la) {
            aDB.addAddress(a);
        }
    }

    /**
     * Initializes the process of adding an address, received as parameter, to
     * the Database.
     *
     * @param a the address to be added.
     * @return true, if it has been added successfully. Otherwise, returns
     * false.
     */
    public boolean addAddress(Address a) {

        return aDB.addAddress(a);
    }

    /**
     * Returns all the addresses contained in the Database.
     *
     * @return all the addresses contained in the Database.
     */
    public List<Address> getAddresses() {

        return aDB.getAddresses();
    }

    /**
     * Returns all the addresses where pharmacies are stationed.
     *
     * @return all the addresses where pharmacies are stationed.
     */
    public List<Address> getPharmacyAddresses() {

        return aDB.getPharmacyAddresses();
    }

    /**
     * Returns the list of paths whose end points are contained in the list of
     * addresses received as parameter.
     *
     * @param la the list of addresses.
     * @return the list of paths whose end points are contained in the list of
     * addresses received as parameter.
     */
    public List<Path> getPaths(List<Address> la) {
        return pDB.getPaths(la);
    }

    /**
     * Initializes the process of adding a set of paths, received as parameters,
     * to the Database.
     *
     * @param lp the list of paths to be added.
     */
    public void addPaths(List<Path> lp) {

        for (Path p : lp) {
            pDB.addPath(p);
        }
    }

    /**
     * Initializes the process of adding a path, received as parameter, to the
     * Database.
     *
     * @param p the path to be added.
     * @return true, if it has been added successfully. Otherwise, returns
     * false.
     */
    public boolean addPath(Path p) {

        return pDB.addPath(p);
    }

    /**
     * Initializes the process of updating a path with the contents of the
     * parameter received.
     *
     * @param p the path containing the changes to be made.
     * @return true, if it has been updated successfully. Otherwise, returns
     * false.
     */
    public boolean updatePath(Path p) {

        return pDB.updatePath(p);
    }

    /**
     * Removes a path, whose endpoints are the addresses received as parameters,
     * from the Database.
     *
     * @param a1 the address from the left endpoint.
     * @param a2 the address from the right endpoint.
     * @return true, if it has been removed successfully. Otherwise, returns
     * false.
     */
    public boolean removePath(Address a1, Address a2) {

        return pDB.removePath(a1, a2);
    }
}
