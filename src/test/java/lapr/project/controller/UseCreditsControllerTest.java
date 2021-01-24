package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.model.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UseCreditsControllerTest {

    private static UseCreditsController controller;

    public UseCreditsControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        ClientDB cDB = mock(ClientDB.class);

        when(cDB.getCreditsByClientEmail("test@gmail.com")).thenReturn(5);
        when(cDB.updateCreditsClient("test@gmail.com", 0)).thenReturn(Boolean.TRUE);

        controller = new UseCreditsController();
        controller = new UseCreditsController(cDB);
    }

    @Test
    public void testGetCreditsByClientEmail() throws SQLException {

        int expResult = 5;
        int result = controller.getCreditsByClientEmail("test@gmail.com");
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateCreditsClient() throws SQLException {

        boolean expResult = true;
        boolean result = controller.updateCreditsClient("test@gmail.com", 0);
        assertEquals(expResult, result);

        ClientDB cDB = mock(ClientDB.class);

        when(cDB.updateCreditsClient("test@gmail.com", 0)).thenReturn(Boolean.FALSE);
        UseCreditsController controller2 = new UseCreditsController(cDB);

        expResult = false;
        result = controller2.updateCreditsClient("test@gmail.com", 0);
        assertEquals(expResult, result);
    }
}
