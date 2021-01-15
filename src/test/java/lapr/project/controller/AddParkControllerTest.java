package lapr.project.controller;

import lapr.project.data.AddressDB;
import lapr.project.data.CourierDB;
import lapr.project.data.ParkDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.Courier;
import lapr.project.model.Park;
import lapr.project.model.Pharmacy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddParkControllerTest {

    private static AddParkController controller;

    public AddParkControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        Address a = new Address("TestAddress", 0, 0, 0);
        Park park = new Park(1, 1, 1,1, "TestCategory", a);

        AddressDB aDB = mock(AddressDB.class);
        ParkDB pDB = mock(ParkDB.class);

        when(aDB.doesAddressExist(park.getAddress().getDescription())).thenReturn(Boolean.TRUE);
        when(pDB.addPark(park)).thenReturn(Boolean.TRUE);

        controller = new AddParkController();
        controller = new AddParkController(aDB, pDB);
    }

    /**
     * Test of addPark method, of class AddParkController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddCourier() throws Exception {

        System.out.println("addCourier");

        Address a = new Address("TestAddress", 0, 0, 0);
        Park park = new Park(1, 1, 1,1, "TestCategory", a);

        boolean expResult = true;
        boolean result = controller.addPark(park);
        assertEquals(expResult, result);

        AddressDB aDB = mock(AddressDB.class);
        ParkDB pDB = mock(ParkDB.class);

        when(aDB.doesAddressExist(park.getAddress().getDescription())).thenReturn(Boolean.FALSE);
        when(aDB.addAddress(park.getAddress())).thenReturn(Boolean.TRUE);
        when(pDB.addPark(park)).thenReturn(Boolean.TRUE);

        expResult = true;
        result = controller.addPark(park);
        assertEquals(expResult, result);

        AddressDB aDB2 = mock(AddressDB.class);
        ParkDB pDB2 = mock(ParkDB.class);

        when(aDB2.doesAddressExist(park.getAddress().getDescription())).thenReturn(Boolean.FALSE);
        when(aDB2.addAddress(park.getAddress())).thenReturn(Boolean.FALSE);
        when(pDB2.addPark(park)).thenReturn(Boolean.FALSE);
        AddParkController controller2 = new AddParkController(aDB2, pDB2);

        expResult = false;
        result = controller2.addPark(park);
        assertEquals(expResult, result);
    }

}