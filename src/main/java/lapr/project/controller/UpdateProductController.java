package lapr.project.controller;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;

import java.sql.SQLException;

public class UpdateProductController {

    private final ProductDB pDB;

    public UpdateProductController() {
        pDB = new ProductDB();
    }

    public UpdateProductController(ProductDB pDB) {
        this.pDB = pDB;
    }

    public boolean updateProduct(int id, Product p) throws SQLException {
        if (p == null) {
            return false;
        }
        return pDB.updateProduct(id, p);
    }
}
