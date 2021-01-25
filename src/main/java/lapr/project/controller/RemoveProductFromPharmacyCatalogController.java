package lapr.project.controller;

import lapr.project.data.StockDB;
import lapr.project.model.Stock;

public class RemoveProductFromPharmacyCatalogController {

    private final StockDB stockDB;

    public RemoveProductFromPharmacyCatalogController() {

        stockDB = new StockDB();
    }

    public RemoveProductFromPharmacyCatalogController(StockDB stockDB) {

        this.stockDB = stockDB;
    }

    public boolean removeProductFromPharmacyCatalog(Stock stock) {

        return stockDB.removeProductFromPharmacyCatalog(stock);
    }

}
