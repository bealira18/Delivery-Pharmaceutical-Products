package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.model.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class UseCreditsControllerTest {

    private static UseCreditsController uPC;

    public UseCreditsControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        ClientDB cDB = mock(ClientDB.class);

        uPC = new UseCreditsController();
        uPC = new UseCreditsController(cDB);
    }

    @Test
    void testUpdateProduct1() throws SQLException {
        boolean actual=uPC.useCredits("email",0);
        assertFalse(actual); //porque não existem produtos
    }


}
