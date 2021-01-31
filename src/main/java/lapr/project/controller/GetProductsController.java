package lapr.project.controller;

import java.util.List;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;

/**
 * Controls the process of getting all products in the system.
 *
 * @author lapr3-2020-g052
 */
public class GetProductsController {

    /**
     * ProductDB instance to get the registered products
     */
    private final ProductDB pDB;

    /**
     * Creates a instance of GetProductsController, creating the required instances.
     */
    public GetProductsController() {

        pDB = new ProductDB();
    }

    /**
     * Creates a instance of GetProductsController with the given instances.
     * @param pDB ProductDB instance
     */
    public GetProductsController(ProductDB pDB) {

        this.pDB = pDB;
    }

    /**
     * Gets all products in the database
     * @return list with all products
     */
    public List<Product> getProducts() {

        return pDB.getProducts();
    }
}
