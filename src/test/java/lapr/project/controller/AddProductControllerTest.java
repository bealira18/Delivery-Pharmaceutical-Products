package lapr.project.controller;

import lapr.project.data.ProductDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddProductControllerTest {

    private static AddProductController controller;

    public AddProductControllerTest(){
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        ProductCategory productCategory = new ProductCategory(1, "NameTest");

        Product product1 = new Product(1, "NameTest", 10, 20, 1);

        ProductDB productDB = mock(ProductDB.class);

        when(productDB.addProduct(product1)).thenReturn(Boolean.TRUE);

        controller = new AddProductController();
        controller = new AddProductController(productDB);
    }

    /**
     * Test of addProduct method, of class AddProductController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddProduct() throws Exception {

        System.out.println("addProduct");

        ProductCategory productCategory = new ProductCategory(1, "NameTest");

        Product product1 = new Product(1, "NameTest", 10, 20, 1);

        boolean result = controller.addProduct(product1);
        assertEquals(true, result);
    }
}