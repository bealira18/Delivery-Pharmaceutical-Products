package lapr.project.controller;

import lapr.project.data.StockDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RemoveProductFromPharmacyCatalogControllerTest {

    private static RemoveProductFromPharmacyCatalogController controller;

    public RemoveProductFromPharmacyCatalogControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        Pharmacy pharmacy = new Pharmacy(1, "farmacia da boavista", new Address("casa da musica", 0, 0, 0));
        ProductCategory productCategory = new ProductCategory(1, "Medicamentos");
        Product product = new Product(1, "Ben-u-ron", 2.40, 0.1, 1);

        Stock stock1 = new Stock(1, 1, 10);

        StockDB stockDB = mock(StockDB.class);

        when(stockDB.removeProductFromPharmacyCatalog(stock1)).thenReturn(Boolean.TRUE);

        controller = new RemoveProductFromPharmacyCatalogController();
        controller = new RemoveProductFromPharmacyCatalogController(stockDB);
    }

    /**
     * Test of removeProductToPharmacyCatalog method, of class RemoveProductToPharmacyCatalogController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveProductToPharmacyCatalog() throws Exception {

        System.out.println("addProductToPharmacyCatalog");

        Pharmacy pharmacy = new Pharmacy(1, "farmacia da boavista", new Address("casa da musica", 0, 0, 0));
        ProductCategory productCategory = new ProductCategory(1, "Medicamentos");
        Product product = new Product(1, "Ben-u-ron", 2.40, 0.1, 1);

        Stock stock1 = new Stock(1, 1, 0);

        boolean result = controller.removeProductFromPharmacyCatalog(stock1);
        assertEquals(true, result);

        StockDB stockDB = mock(StockDB.class);

        when(stockDB.removeProductFromPharmacyCatalog(stock1)).thenReturn(Boolean.FALSE);

        RemoveProductFromPharmacyCatalogController controller2 = new RemoveProductFromPharmacyCatalogController(stockDB);

        result = controller2.removeProductFromPharmacyCatalog(stock1);
        boolean expResult = false;
        assertEquals(expResult, result);

    }

}