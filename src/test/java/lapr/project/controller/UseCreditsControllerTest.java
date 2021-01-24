package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.model.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class UseCreditsControllerTest {

    private static UseCreditsController controller;

    public UseCreditsControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        ClientDB cDB = mock(ClientDB.class);

        controller = new UseCreditsController();
        controller = new UseCreditsController(cDB);
    }



}
