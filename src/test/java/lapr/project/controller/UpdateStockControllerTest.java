package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.data.StockDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UpdateStockControllerTest {

    private static UpdateStockController stockController;

    public UpdateStockControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        StockDB stockDB = mock(StockDB.class);

        stockController = new UpdateStockController();
        stockController = new UpdateStockController(stockDB);
    }

    @Test
    void updateProductStockAfterSaleTest() throws SQLException {

        System.out.println("testUpdateProductStockAfterSale");

        PurchaseOrder order = new PurchaseOrder(1,1, "emailTeste@gmail.com", LocalDate.now());

        boolean actual=stockController.updateProductStockAfterSale(order.getId());
        assertFalse(actual);
    }
}