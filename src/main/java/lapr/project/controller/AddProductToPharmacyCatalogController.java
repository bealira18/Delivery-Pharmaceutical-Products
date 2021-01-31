package lapr.project.controller;

import lapr.project.data.StockDB;
import lapr.project.model.Stock;

/**
 * Controls the process of adding a product to a pharmacy catalog.
 *
 * @author lapr3-2020-g052
 */
public class AddProductToPharmacyCatalogController {

    /**
     * StockDB instance to add a product to the pharmacy catalog
     */
    private final StockDB stockDB;

    /**
     * Creates a instance of AddProductToPharmacyCatalogController, creating the required instances.
     */
    public AddProductToPharmacyCatalogController() {

        stockDB = new StockDB();
    }

    /**
     * Creates a instance of AddProductToPharmacyCatalogController with the given instances.
     * @param stockDB StockDB instance
     */
    public AddProductToPharmacyCatalogController(StockDB stockDB) {

        this.stockDB = stockDB;
    }

    /**
     * Adds the product to a pharmacy catalog
     * @param stock Stock instance with the product id to add and the pharmacy id to add to
     * @return true if added, false if already exists or database failure
     */
    public boolean addProductToPharmacyCatalog(Stock stock) {

        if (!stockDB.checkIfProductExistsInCatalog(stock.getPharmacyId(), stock.getProductId())) {
            return stockDB.addProductToPharmacyCatalog(stock);
        }
        return false;
    }
}
