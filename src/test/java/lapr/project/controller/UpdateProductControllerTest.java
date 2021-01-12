package lapr.project.controller;

import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class UpdateProductControllerTest {

    private static UpdateProductController uPC;

    public UpdateProductControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        ProductDB pDB = mock(ProductDB.class);

        uPC = new UpdateProductController();
        uPC = new UpdateProductController(pDB);
    }

    @Test
    void testUpdateProduct1() throws SQLException {
        Product p=new Product(0,"name1",2.0,2.5,1);
        boolean actual=uPC.updateProduct(0,p);
        assertFalse(actual); //porque não existem produtos
    }

    @Test
    void testUpdateProduct2() throws SQLException {
        boolean actual=uPC.updateProduct(1,null);
        assertFalse(actual);
    }

}
