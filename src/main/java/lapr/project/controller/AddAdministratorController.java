package lapr.project.controller;

import lapr.project.data.AdministratorDB;
import lapr.project.model.Administrator;

/**
 * Controls the process of adding an administrator.
 *
 * @author lapr3-2020-g052
 */
public class AddAdministratorController {

    /**
     * AdministratorDB instance to add an Administrator
     */
    private final AdministratorDB administratorDB;

    /**
     * Creates a instance of AddAdministratorController, creating the required instances.
     */
    public AddAdministratorController() {

        administratorDB = new AdministratorDB();
    }

    /**
     * Creates a instance of AddAdministratorController with the given instances.
     * @param administratorDB administratorDB instance
     */
    public AddAdministratorController(AdministratorDB administratorDB) {

        this.administratorDB = administratorDB;
    }

    /**
     * Adds an administrator
     * @param administrator administrator
     * @return true if added, false if Administrator objects are invalid or a database failure
     */
    public boolean addAdministrator(Administrator administrator) {

        return administratorDB.addAdministrator(administrator);
    }
}
