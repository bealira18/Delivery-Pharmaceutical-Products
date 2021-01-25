package lapr.project.controller;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;

public class AddProductController {

    private final ProductDB productDB;

    public AddProductController() {

        productDB = new ProductDB();
    }

    public AddProductController(ProductDB productDB) {

        this.productDB = productDB;
    }

    public boolean addProduct(Product product) {

        return productDB.addProduct(product);
    }
}
