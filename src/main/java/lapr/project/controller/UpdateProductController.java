package lapr.project.controller;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;

/**
 * Controls the process of updating information for a Product.
 *
 * @author lapr3-2020-g052
 */
public class UpdateProductController {

    /**
     * ProductDB instance to save the new product information
     */
    private final ProductDB pDB;

    /**
     * Creates a instance of UpdateProductController, creating the required instances.
     */
    public UpdateProductController() {
        pDB = new ProductDB();
    }

    /**
     * Creates a instance of UpdateProductController with the given instances.
     * @param pDB ProductDB instance
     */
    public UpdateProductController(ProductDB pDB) {
        this.pDB = pDB;
    }

    /**
     * Updates a given product.
     * @param id the id of the target product to be updated.
     * @param p the product new parameters.
     * @return true on succes, false if Product object is invalid or a database failure.
     */
    public boolean updateProduct(int id, Product p) {

        if (p == null) {
            return false;
        }
        return pDB.updateProduct(id, p);
    }
}
