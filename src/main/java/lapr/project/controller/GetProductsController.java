package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;

public class GetProductsController {

    private final ProductDB pDB;

    public GetProductsController() {

        pDB = new ProductDB();
    }

    public GetProductsController(ProductDB pDB) {

        this.pDB = pDB;
    }

    public List<Product> getProducts() throws SQLException {

        return pDB.getProducts();
    }
}