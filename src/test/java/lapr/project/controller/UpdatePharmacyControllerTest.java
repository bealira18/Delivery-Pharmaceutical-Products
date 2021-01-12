package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class UpdatePharmacyControllerTest {

    private static UpdatePharmacyController uPC;

    public UpdatePharmacyControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        PharmacyDB pDB = mock(PharmacyDB.class);

        uPC = new UpdatePharmacyController();
        uPC = new UpdatePharmacyController(pDB);
    }

    @Test
    void testUpdatePharmacy1() throws SQLException {
        boolean actual=uPC.updatePharmacy(0,"name");
        assertFalse(actual); //porque não existem farmacias
    }

    @Test
    void testUpdatePharmacy2() throws SQLException {
        boolean actual=uPC.updatePharmacy(1,null);
        assertFalse(actual);
    }

}
