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

    public AddScooterControllerTest(){
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        Pharmacy pharmacy = new Pharmacy(1, "Coimbrões", "R. Domingos de Matos 680, 4405 Vila Nova de Gaia");
        ScooterPark scooterPark = new ScooterPark(1,1,3,1,"R. Domingos de Matos 680, 4405 Vila Nova de Gaia");
        ScooterStatus scooterStatus = new ScooterStatus(1, "TestName");

        Scooter scooter1 = new Scooter(1, 1, 1,10, 100);
        Scooter scooter2 = new Scooter(2, 1, 1,20,100);


        ScooterDB scooterDB = mock(ScooterDB.class);
        ScooterParkDB scooterParkDB = mock(ScooterParkDB.class);



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

        Pharmacy pharmacy = new Pharmacy(1, "Coimbrões", "R. Domingos de Matos 680, 4405 Vila Nova de Gaia");
        ScooterPark scooterPark = new ScooterPark(1,1,3,1,"R. Domingos de Matos 680, 4405 Vila Nova de Gaia");
        ScooterStatus scooterStatus = new ScooterStatus(1, "TestName");
        Scooter scooter3 = new Scooter(3, 1, 1,30, 100);

        boolean expResult = true;
        boolean result = controller.addScooter(scooter3);
        assertEquals(expResult, result);

        ScooterDB scooterDB = mock(ScooterDB.class);
        ScooterParkDB scooterParkDB = mock(ScooterParkDB.class);
        when(scooterDB.addScooter(new Scooter(2, 1, 1, 15, 100))).thenReturn(Boolean.FALSE);
        AddScooterController controller1 = new AddScooterController(scooterDB, scooterParkDB);

        expResult = false;
        result = controller1.addScooter(scooter3);
        assertEquals(expResult, result);

    }

}