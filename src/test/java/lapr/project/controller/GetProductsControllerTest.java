package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetProductsControllerTest {

    private static GetProductsController gPCont;

    public GetProductsControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        ProductDB pDB = mock(ProductDB.class);
        when(pDB.getProducts()).thenReturn(new ArrayList<>());

        gPCont = new GetProductsController();
        gPCont = new GetProductsController(pDB);
    }

    /**
     * Test of getProducts method, of class GetProductsController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetProducts() throws Exception {

        System.out.println("getProducts");

        assertEquals(Collections.emptyList(), gPCont.getProducts());

        Product product = new Product(1, "a", 1,1,1);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        ProductDB pDB = mock(ProductDB.class);
        when(pDB.getProducts()).thenReturn(productList);

        GetProductsController controller2 = new GetProductsController(pDB);

        List<Product> result = controller2.getProducts();
        assertEquals(productList, result);
    }
}
