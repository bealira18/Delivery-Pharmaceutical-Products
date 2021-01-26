package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PurchaseItemsControllerTest {

    private static PurchaseItemsController controller;
    private static List<Pharmacy> auxListPharmacies;
    private static List<ProductCategory> auxListProductCategories;
    private static List<Product> auxListProducts1;
    private static List<Product> auxListProducts2;
    private static HashMap<ProductCategory, List<Product>> auxMapProducts;
    private static HashMap<Product, Integer> auxBasket;

    public PurchaseItemsControllerTest() {
    }

    @BeforeAll
    public static void setUp() {

        auxListPharmacies = new ArrayList<>();
        auxListPharmacies.add(new Pharmacy(1, "TestPharma", new Address("TestAddress", 0, 0, 0)));
        auxListPharmacies.add(new Pharmacy(2, "TestPharma", new Address("TestAddress", 0, 0, 0)));

        auxListProductCategories = new ArrayList<>();
        ProductCategory category1 = new ProductCategory(1, "TestCategory1");
        ProductCategory category2 = new ProductCategory(2, "TestCategory2");
        auxListProductCategories.add(category1);
        auxListProductCategories.add(category2);

        auxListProducts1 = new ArrayList<>();
        Product product1 = new Product(1, "TestProduct1", 1,1,1);
        Product product2 = new Product(2, "TestProduct2", 1,1,1);
        auxListProducts1.add(product1);
        auxListProducts1.add(product2);

        auxListProducts2 = new ArrayList<>();
        auxListProducts2.add(new Product(3, "TestProduct3", 1,1,2));
        auxListProducts2.add(new Product(4, "TestProduct4", 1,1,2));

        auxMapProducts = new HashMap<>();
        auxMapProducts.put(category1, auxListProducts1);
        auxMapProducts.put(category2, auxListProducts2);

        auxBasket = new HashMap<>();
        auxBasket.put(product1, 2);
        auxBasket.put(product2, 3);

        PharmacyDB pharmacyDB = mock(PharmacyDB.class);
        ProductDB productDB = mock(ProductDB.class);
        PurchaseOrderDB purchaseOrderDB=mock(PurchaseOrderDB.class);
        ProductLineDB productLineDB=mock(ProductLineDB.class);
        StockDB stockDB=mock(StockDB.class);
        UpdateScooterController updateScooterController = new UpdateScooterController();

        when(pharmacyDB.getAllPharmacies()).thenReturn(auxListPharmacies);
        when(productDB.getProductsFromPharmacy(1)).thenReturn(auxMapProducts);

        controller = new PurchaseItemsController();
        controller = new PurchaseItemsController(pharmacyDB, productDB, purchaseOrderDB, productLineDB, stockDB, updateScooterController);

        controller.getProductsFromPharmacy(1);
    }

    @Test
    public void testGetPharmacies() {
        assertEquals(auxListPharmacies, controller.getPharmacies());
    }

    @Test
    public void testGetPharmacies1() {
        assertNotNull(controller.getPharmacies());
    }

    @Test
    void getProductCategories() throws SQLException {
        assertEquals(auxListProductCategories, controller.getProductCategories());
    }

    @Test
    void getProductCategories2() {
        assertNotNull(controller.getProductCategories());
    }

    @Test
    void getProductsFromCategory() {
        ProductCategory category1 = new ProductCategory(1, "TestCategory1");
        assertEquals(auxListProducts1, controller.getProductsFromCategory(category1));
    }

    @Test
    void getProductsFromCategory2() {
        ProductCategory category1 = new ProductCategory(1, "TestCategory1");
        assertNotNull(controller.getProductsFromCategory(category1));
    }

    @Test
    void getProductsFromCategory3() {
        ProductCategory category = new ProductCategory(3, "TestCategory1");
        assertNull(controller.getProductsFromCategory(category));
    }

    @Test
    void addToBasket() {
        Product product1 = new Product(1, "TestProduct1", 1,1,1);
        boolean expResult = true;
        boolean result = controller.addToBasket(product1, 2);
        assertEquals(expResult, result);

        result = controller.addToBasket(product1, 2);
        assertEquals(expResult, result);
    }

    @Test
    void purchaseItemsTest() {
        PurchaseOrder expResult = null;
        PurchaseOrder result=controller.purchaseItems(1,1, "testEmail@gmail.com", null);
        assertEquals(expResult,result);
    }
}
