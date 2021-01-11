package lapr.project.controller;

import lapr.project.data.StockDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddProductToPharmacyCatalogControllerTest {

    private static AddProductToPharmacyCatalogController controller;

    public AddProductToPharmacyCatalogControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        Pharmacy pharmacy = new Pharmacy(1, "Coimbrões", new Address("TestAddress", 0, 0, 0));
        ProductCategory productCategory = new ProductCategory(1, "NameTest");
        Product product = new Product(1, "NameTest", 10, 20, 1);

        Stock stock1 = new Stock(1, 1, 0);

        StockDB stockDB = mock(StockDB.class);

        when(stockDB.addProductToPharmacyCatalog(stock1)).thenReturn(Boolean.TRUE);
        when(stockDB.checkIfProductExistsInCatalog(stock1.getPharmacyId(), stock1.getProductId())).thenReturn(Boolean.FALSE);

        controller = new AddProductToPharmacyCatalogController();
        controller = new AddProductToPharmacyCatalogController(stockDB);
    }

    /**
     * Test of addScooter method, of class AddScooterController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddProductToPharmacyCatalog() throws Exception {

        System.out.println("addProductToPharmacyCatalog");

        Pharmacy pharmacy = new Pharmacy(1, "Coimbrões", new Address("TestAddress", 0, 0, 0));
        ProductCategory productCategory = new ProductCategory(1, "NameTest");
        Product product = new Product(1, "NameTest", 10, 20, 1);

        Stock stock1 = new Stock(1, 1, 0);

        boolean result = controller.addProductToPharmacyCatalog(stock1);
        assertEquals(true, result);

        StockDB stockDB = mock(StockDB.class);

        when(stockDB.checkIfProductExistsInCatalog(stock1.getPharmacyId(), stock1.getProductId())).thenReturn(Boolean.FALSE);

        AddProductToPharmacyCatalogController controller1 = new AddProductToPharmacyCatalogController(stockDB);

        result = controller1.addProductToPharmacyCatalog(stock1);
        assertEquals(false, result);

        when(stockDB.addProductToPharmacyCatalog(stock1)).thenReturn(Boolean.FALSE);
        when(stockDB.checkIfProductExistsInCatalog(stock1.getPharmacyId(), stock1.getProductId())).thenReturn(Boolean.TRUE);

        result = controller1.addProductToPharmacyCatalog(stock1);
        assertEquals(false, result);
    }

}
