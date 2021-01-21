package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.Administrator;
import lapr.project.model.Courier;
import lapr.project.model.Drone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddAdministratorControllerTest {

    private static AddAdministratorController controller;

    public AddAdministratorControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        Administrator administrator1 = new Administrator("teste@gmail.com", "teste", 1, "teste", 223445365, 1234567882);

        AdministratorDB administratorDB = mock(AdministratorDB.class);

        when(administratorDB.addAdministrator(administrator1)).thenReturn(Boolean.TRUE);

        controller = new AddAdministratorController();
        controller = new AddAdministratorController(administratorDB);
    }

    /**
     * Test of addAdministrator method, of class AddAdministratorController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddAdministrator() throws Exception {

        System.out.println("addAdministrator");

        Administrator administrator1 = new Administrator("teste@gmail.com", "teste", 1, "teste", 223445365, 1234567882);

        boolean result = controller.addAdministrator(administrator1);
        assertEquals(true, result);

        AdministratorDB administratorDB = mock(AdministratorDB.class);

        when(administratorDB.addAdministrator(administrator1)).thenReturn(Boolean.FALSE);

        AddAdministratorController controller1 = new AddAdministratorController(administratorDB);

        result = controller1.addAdministrator(administrator1);
        assertEquals(false, result);
    }
}