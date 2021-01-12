package lapr.project.controller;

import lapr.project.data.StockDB;

import java.sql.SQLException;

public class UpdateStockController {

    private final StockDB stockDB;

    public UpdateStockController() {

        stockDB = new StockDB();
    }

    public UpdateStockController(StockDB stockDB) {

        this.stockDB = stockDB;
    }

    public boolean updateProductStockAfterSale(int idOrder) throws SQLException {

        return stockDB.updateProductStockAfterSale(idOrder);
    }
}
