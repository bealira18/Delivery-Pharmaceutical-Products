package lapr.project.controller;

import lapr.project.data.ClientDB;

import java.sql.SQLException;

public class UseCreditsController {

    private final ClientDB cDB;

    public UseCreditsController() {
        cDB = new ClientDB();
    }

    public UseCreditsController(ClientDB cDB) {
        this.cDB = cDB;
    }

    public int getCreditsByClientEmail(String email) throws SQLException {

        return cDB.getCreditsByClientEmail(email);
    }

    public boolean updateCreditsClient(String email, int newClientsAmount) throws SQLException {

        return cDB.updateCreditsClient(email, newClientsAmount);
    }

}
