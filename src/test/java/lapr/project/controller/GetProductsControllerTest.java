package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import lapr.project.data.ProductDB;
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

        assertEquals(new ArrayList<>(), gPCont.getProducts());
    }
}
