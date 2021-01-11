package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.data.DeliveryDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AssignOrderToCourierScooterControllerTest {

    private static AssignOrderToCourierScooterController controller;
    private static List<Courier> auxListCouriers;
    private static List<Scooter> auxListScooters;

    public AssignOrderToCourierScooterControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException {

        ArrayList<Delivery> deliveries = new ArrayList<>();

        Delivery d1 = new Delivery(1, 1, "c1@gmail.com", 1, null, null, 1);
        Delivery d2 = new Delivery(2, 1, "c1@gmail.com", 1, null, null, 1);
        Delivery d3= new Delivery(3, 1, "c1@gmail.com", 1, null, null, 1);
        deliveries.add(d1);
        deliveries.add(d2);

        auxListCouriers = new ArrayList<>();
        auxListCouriers.add(new Courier("c1@gmail.com", "qwerty", "John", 958752502, 11254852166L, 1, 85));
        auxListCouriers.add(new Courier("c2@gmail.com", "qwerty", "John", 958752502, 11254852166L, 1, 85));

        auxListScooters = new ArrayList<>();
        auxListScooters.add(new Scooter(1, 1, 20, 40, 50, 60, 10, 100, 1));
        auxListScooters.add(new Scooter(2, 1, 20, 40, 50, 60, 10, 100, 1));

        DeliveryDB dDB = mock(DeliveryDB.class);
        CourierDB cDB = mock(CourierDB.class);
        ScooterDB sDB = mock(ScooterDB.class);

        when(cDB.getAllAvailableCouriers(1)).thenReturn(auxListCouriers);
        when(sDB.getAllAvailableScooters(1)).thenReturn(auxListScooters);
        when(dDB.getNextAvailableCourier(1)).thenReturn(d3);
        when(dDB.getNextAvailableScooter(1)).thenReturn(d3);
        when(dDB.addDeliveries(deliveries)).thenReturn(Boolean.TRUE);

        controller = new AssignOrderToCourierScooterController();
        controller = new AssignOrderToCourierScooterController(dDB, cDB, sDB);
    }

    /**
     * Tests of getAllAvailableCouriers1 method, of class AssignOrderToCourierScooterController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllAvailableCouriers1() throws Exception {
        PurchaseOrder order = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        assertEquals(auxListCouriers, controller.getAllAvailableCouriers(order));
    }

    @Test
    public void testGetAllAvailableCouriers2() throws Exception {
        PurchaseOrder order = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        assertNotNull(controller.getAllAvailableCouriers(order));
    }

    @Test
    public void testGetAllAvailableCouriers3() throws Exception {
        PurchaseOrder order = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        assertNotEquals(null, controller.getAllAvailableCouriers(order));
    }

    /**
     * Tests of getAllAvailableScooters method, of class AssignOrderToCourierScooterController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllAvailableScooters1() throws Exception {
    PurchaseOrder order = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
    assertEquals(auxListScooters, controller.getAllAvailableScooters(order));
}

    @Test
    public void testGetAllAvailableScooters2() throws Exception {
        PurchaseOrder order = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        assertNotNull(controller.getAllAvailableScooters(order));
    }

    @Test
    public void testGetAllAvailableScooters3() throws Exception {
        PurchaseOrder order = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        assertNotEquals(null, controller.getAllAvailableScooters(order));
    }

    /**
     * Test of addDeliveries method, of class AssignOrderToCourierScooterController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddDeliveries() throws Exception {

        System.out.println("addDeliveries");

        ArrayList<Delivery> deliveries = new ArrayList<>();

        Delivery d1 = new Delivery(1, 1, "c1@gmail.com", 1, null, null, 1);
        Delivery d2 = new Delivery(2, 1, "c1@gmail.com", 1, null, null, 1);
        deliveries.add(d1);
        deliveries.add(d2);

        PurchaseOrder order1 = new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        PurchaseOrder order2 = new PurchaseOrder(2, 1, "Test", LocalDate.of(2077, Month.MARCH, 1));
        ArrayList<PurchaseOrder> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        boolean expResult = true;
        boolean result = controller.addDeliveries(orders);
        assertEquals(expResult, result);
    }
}