package lapr.project.controller;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;

/**
 * Controls the process of adding a product to the system.
 *
 * @author lapr3-2020-g052
 */
public class AddProductController {

    /**
     * ProductDB instance to add a product to the system
     */
    private final ProductDB productDB;

    /**
     * Creates a instance of AddProductController, creating the required instances.
     */
    public AddProductController() {

        productDB = new ProductDB();
    }

    /**
     * Creates a instance of AddProductController with the given instances.
     * @param productDB ProductDB instance
     */
    public AddProductController(ProductDB productDB) {

        this.productDB = productDB;
    }

    /**
     * Registers a new product in the system
     * @param product new product to add
     * @return true if added, false if Product object is invalid or database failure
     */
    public boolean addProduct(Product product) {

        return productDB.addProduct(product);
    }
}
