package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;

import java.sql.SQLException;

public class UseCreditsController {

    private final ClientDB cDB;

    public UseCreditsController() {
        cDB = new ClientDB();
    }

    public UseCreditsController(ClientDB cDB) {
        this.cDB = cDB;
    }

    public boolean useCredits(String email,int idInvoice) throws SQLException {

        return cDB.useCredits(email, idInvoice);
    }

}
