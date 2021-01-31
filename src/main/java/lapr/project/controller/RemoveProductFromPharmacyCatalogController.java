package lapr.project.controller;

import lapr.project.data.StockDB;
import lapr.project.model.Stock;

/**
 * Controls the process of removing a product from a pharmacy catalog.
 *
 * @author lapr3-2020-g052
 */
public class RemoveProductFromPharmacyCatalogController {

    /**
     * StockDB instance to update a pharmacy catalog
     */
    private final StockDB stockDB;

    /**
     * Creates a instance of RemoveProductFromPharmacyCatalogController, creating the required instances.
     */
    public RemoveProductFromPharmacyCatalogController() {

        stockDB = new StockDB();
    }

    /**
     * Creates a instance of RemoveProductFromPharmacyCatalogController with the given instances.
     * @param stockDB StockDB instance
     */
    public RemoveProductFromPharmacyCatalogController(StockDB stockDB) {

        this.stockDB = stockDB;
    }

    /**
     * Removes a product from a pharmacy catalog by their ids.
     * @param stock product id and pharmacy id
     * @return true if removed, false if Stock object is invalid or a database failure
     */
    public boolean removeProductFromPharmacyCatalog(Stock stock) {

        return stockDB.removeProductFromPharmacyCatalog(stock);
    }

}
