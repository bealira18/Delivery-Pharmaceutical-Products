package lapr.project.controller;

import lapr.project.data.StockDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RemoveProductToPharmacyCatalogControllerTest {

    private static RemoveProductToPharmacyCatalogController controller;

    public RemoveProductToPharmacyCatalogControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        Pharmacy pharmacy = new Pharmacy(1, "farmacia da boavista", new Address("casa da musica", 0, 0, 0));
        ProductCategory productCategory = new ProductCategory(1, "Medicamentos");
        Product product = new Product(1, "Ben-u-ron", 2.40, 0.1, 1);

        Stock stock1 = new Stock(1, 1, 10);

        StockDB stockDB = mock(StockDB.class);

        when(stockDB.removeProductToPharmacyCatalog(stock1)).thenReturn(Boolean.TRUE);

        controller = new RemoveProductToPharmacyCatalogController();
        controller = new RemoveProductToPharmacyCatalogController(stockDB);
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

        boolean result = controller.removeProductToPharmacyCatalog(stock1);
        assertEquals(true, result);
    }

}