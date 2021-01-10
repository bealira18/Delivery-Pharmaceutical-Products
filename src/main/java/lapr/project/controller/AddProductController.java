package lapr.project.controller;

import lapr.project.data.ProductDB;
import lapr.project.data.StockDB;
import lapr.project.model.Product;
import java.sql.SQLException;

public class AddProductController {

    private final ProductDB productDB;
    private final StockDB stockDB;

    public AddProductController(){
        productDB = new ProductDB();
        stockDB = new StockDB();
    }

    public AddProductController(ProductDB productDB, StockDB stockDB) {
        this.productDB = productDB;
        this.stockDB = stockDB;
    }

    public boolean addProduct(Product product) throws SQLException {

        return productDB.addProduct(product);
    }
}
