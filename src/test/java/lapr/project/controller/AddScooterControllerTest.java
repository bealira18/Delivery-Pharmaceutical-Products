package lapr.project.controller;

import lapr.project.data.ScooterDB;
import lapr.project.data.ScooterParkDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddScooterControllerTest {

    private static AddScooterController controller;

    public AddScooterControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        Scooter scooter1 = new Scooter(1, 1, 20, 40, 50, 60, 10, 100, 1);

        ScooterDB scooterDB = mock(ScooterDB.class);
        ScooterParkDB scooterParkDB = mock(ScooterParkDB.class);

        when(scooterDB.addScooter(scooter1)).thenReturn(Boolean.TRUE);
        when(scooterParkDB.getNumberOfScootersInPharmacy(scooter1.getIdPharmacy())).thenReturn(0);
        when(scooterParkDB.getLimitScooterPark(scooter1.getIdPharmacy())).thenReturn(1);

        controller = new AddScooterController();
        controller = new AddScooterController(scooterDB, scooterParkDB);
    }

    /**
     * Test of addScooter method, of class AddScooterController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddScooter() throws Exception {

        System.out.println("addScooter");

        Scooter scooter1 = new Scooter(1, 1, 20, 40, 50, 60, 10, 100, 1);

        boolean result = controller.addScooter(scooter1);
        assertEquals(true, result);

        ScooterDB scooterDB = mock(ScooterDB.class);
        ScooterParkDB scooterParkDB = mock(ScooterParkDB.class);

        when(scooterParkDB.getNumberOfScootersInPharmacy(scooter1.getIdPharmacy())).thenReturn(1);

        AddScooterController controller1 = new AddScooterController(scooterDB, scooterParkDB);

        result = controller1.addScooter(scooter1);
        assertEquals(false, result);

        when(scooterDB.addScooter(scooter1)).thenReturn(Boolean.FALSE);
        when(scooterParkDB.getNumberOfScootersInPharmacy(scooter1.getIdPharmacy())).thenReturn(0);

        result = controller1.addScooter(scooter1);
        assertEquals(false, result);
    }
}
