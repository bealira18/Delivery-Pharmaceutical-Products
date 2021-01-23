package lapr.project.controller;

import lapr.project.data.AdministratorDB;
import lapr.project.model.Administrator;

import java.sql.SQLException;

public class AddAdministratorController {

    private final AdministratorDB administratorDB;

    public AddAdministratorController() {
        administratorDB = new AdministratorDB();
    }

    public AddAdministratorController(AdministratorDB administratorDB) {
        this.administratorDB = administratorDB;
    }

    public boolean addAdministrator(Administrator administrator) throws SQLException {
        return administratorDB.addAdministrator(administrator);
    }

}
