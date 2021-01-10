package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lapr.project.data.CourierDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Courier;
import lapr.project.model.Pharmacy;
import lapr.project.model.RegisteredUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddCourierControllerTest {

    private static AddCourierController controller;
    private static List<Pharmacy> auxListPharmacies;

    public AddCourierControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        RegisteredUser user = new RegisteredUser("c1@gmail.com", "qwerty", "courier");
        Courier courier = new Courier(user, "John", 958752502, 11254852166L, 1, 85);

        auxListPharmacies = new ArrayList<>();
        auxListPharmacies.add(new Pharmacy(1, "TestPharma", "TestAddress"));
        auxListPharmacies.add(new Pharmacy(2, "TestPharma2", "TestAddress2"));
        CourierDB cDB = mock(CourierDB.class);
        PharmacyDB pDB = mock(PharmacyDB.class);

        when(pDB.getAllPharmacies()).thenReturn(auxListPharmacies);
        when(cDB.addCourier(courier)).thenReturn(Boolean.TRUE);

        controller = new AddCourierController();
        controller = new AddCourierController(cDB, pDB);
    }

    /**
     * Tests of addCourier method, of class AddCourierController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindPharmacies() throws Exception {
        assertEquals(auxListPharmacies, controller.findPharmacies());
    }

    @Test
    public void testFindPharmacies1() throws Exception {
        assertNotNull(controller.findPharmacies());
    }

    @Test
    public void testFindPharmacies2() throws Exception {
        assertNotEquals(null, controller.findPharmacies());
    }

    /**
     * Test of addCourier method, of class AddCourierController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddCourier() throws Exception {

        System.out.println("addCourier");

        boolean expResult = true;
        boolean result = controller.addCourier("cl@gmail.com", "qwerty", "John", 958752502, 11254852166L, 1, 85);
        assertEquals(expResult, result);

        CourierDB cDB = mock(CourierDB.class);
        PharmacyDB pDB = mock(PharmacyDB.class);
        when(cDB.addCourier(new Courier("cl@gmail.com", "qwerty", "John", 958752502, 11254852166L, 1, 85))).thenReturn(Boolean.FALSE);
        AddCourierController controller1 = new AddCourierController(cDB, pDB);

        expResult = false;
        result = controller1.addCourier("cl@gmail.com", "qwerty", "John", 958752502, 11254852166L, 1, 85);
        assertEquals(expResult, result);
    }
}
