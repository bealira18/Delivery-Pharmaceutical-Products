package lapr.project.controller;

import lapr.project.data.StockDB;
import lapr.project.model.Stock;

import java.sql.SQLException;

public class RemoveProductToPharmacyCatalogController {

    private final StockDB stockDB;

    public RemoveProductToPharmacyCatalogController() {

        stockDB = new StockDB();
    }

    public RemoveProductToPharmacyCatalogController(StockDB stockDB) {

        this.stockDB = stockDB;
    }

    public boolean removeProductToPharmacyCatalog(Stock stock) throws SQLException {

            return stockDB.removeProductToPharmacyCatalog(stock);
    }

}
