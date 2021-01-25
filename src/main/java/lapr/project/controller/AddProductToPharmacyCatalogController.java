package lapr.project.controller;

import lapr.project.data.StockDB;
import lapr.project.model.Stock;

public class AddProductToPharmacyCatalogController {

    private final StockDB stockDB;

    public AddProductToPharmacyCatalogController() {

        stockDB = new StockDB();
    }

    public AddProductToPharmacyCatalogController(StockDB stockDB) {

        this.stockDB = stockDB;
    }

    public boolean addProductToPharmacyCatalog(Stock stock) {

        if (!stockDB.checkIfProductExistsInCatalog(stock.getPharmacyId(), stock.getProductId())) {
            return stockDB.addProductToPharmacyCatalog(stock);
        }
        return false;
    }
}
