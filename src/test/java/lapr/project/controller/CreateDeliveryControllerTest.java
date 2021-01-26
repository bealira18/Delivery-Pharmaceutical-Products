/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.DeliveryDB;
import lapr.project.data.DroneDB;
import lapr.project.data.ProductDB;
import lapr.project.data.ProductLineDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Drone;
import lapr.project.model.Product;
import lapr.project.model.ProductLine;
import lapr.project.model.PurchaseOrder;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Ricardo
 */
public class CreateDeliveryControllerTest {
    
    public static CreateDeliveryController cdC;
    public static List<PurchaseOrder> pOrderEmpty;
    public static List<PurchaseOrder> pWithOrder;
    public static List<ProductLine> pLineEmpty;
    public static List<ProductLine> pLineWith;
    public static List<Product> pEmpty;
    public static List<Product> pWith;
    
    public CreateDeliveryControllerTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
        
        pOrderEmpty = new ArrayList<>();
        pWithOrder = new ArrayList<>();
        pWithOrder.add(new PurchaseOrder(1, 1, "Test", LocalDate.of(2077, Month.MARCH, 1)));
        
        pLineEmpty = new ArrayList<>();
        pLineWith = new ArrayList<>();
        pLineWith.add(new ProductLine(1, 1, 1, 1.0));
        
        pEmpty = new ArrayList<>();
        pWith = new ArrayList<>();
        pWith.add(new Product(1, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446));
        
        DeliveryDB ddDB = mock(DeliveryDB.class);
        when(ddDB.deliveryRunAssociateVehicle(1, 2)).thenReturn(true);
        when(ddDB.deliveryRunAssociateVehicle(2, 2)).thenReturn(false);
        when(ddDB.deliveryRunAssociateCourier("email", 1)).thenReturn(true);
        when(ddDB.deliveryRunAssociateCourier("email", 2)).thenReturn(false);
        when(ddDB.getPurchaseOrdersByDeliveryRun(1)).thenReturn(pOrderEmpty);
        when(ddDB.getPurchaseOrdersByDeliveryRun(2)).thenReturn(pWithOrder);
        when(ddDB.createNewDelivery(1, 1)).thenReturn(true);
        when(ddDB.createNewDelivery(1, 2)).thenReturn(false);
        
        ProductLineDB plDB = mock(ProductLineDB.class);
        when(plDB.getProductLinesFromOrder(1)).thenReturn(pLineWith);
        
        ProductDB pDB = mock(ProductDB.class);
        when(pDB.getProduct(1)).thenReturn(new Product(1, "SARS-CoV2 Vaccine POGGERS", 1337.42, 548.54, 446));
        
        ScooterDB sDB = mock(ScooterDB.class);
        when(sDB.getHighestBatteryScooter(0)).thenReturn(null);
        when(sDB.getHighestBatteryScooter(1)).thenReturn(new Scooter(1, 1));
        
        DroneDB dDB = mock(DroneDB.class);
        when(dDB.getHighestBatteryDrone(0)).thenReturn(null);
        when(dDB.getHighestBatteryDrone(1)).thenReturn(new Drone(1, 1, 1, 1));
        
        cdC = new CreateDeliveryController();
        cdC = new CreateDeliveryController(ddDB, plDB, pDB, sDB, dDB);
        
    }

    /**
     * Test of createDeliveries method, of class CreateDeliveryController.
     */
    @Test
    public void testCreateDeliveries() {
        System.out.println("createDeliveries");
        List<PurchaseOrder> orderList = pOrderEmpty;
        int deliveryRun = 1;
        boolean expResult = false;
        boolean result = cdC.createDeliveries(orderList, deliveryRun);
        assertEquals(expResult, result);
        
        orderList = pWithOrder;
        expResult = true;
        result = cdC.createDeliveries(orderList, deliveryRun);
        assertEquals(expResult, result);
        
        deliveryRun = 2;
        expResult = false;
        result = cdC.createDeliveries(orderList, deliveryRun);
        assertEquals(expResult, result);
    }

    /**
     * Test of deliveryRunAssociateVehicle method, of class CreateDeliveryController.
     */
    @Test
    public void testDeliveryRunAssociateVehicle() {
        System.out.println("deliveryRunAssociateVehicle");
        int idVehicle = 1;
        int deliveryRun = 2;
        boolean expResult = true;
        boolean result = cdC.deliveryRunAssociateVehicle(idVehicle, deliveryRun);
        assertEquals(expResult, result);
        
        idVehicle = 2;
        expResult = false;
        result = cdC.deliveryRunAssociateVehicle(idVehicle, deliveryRun);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of deliveryRunAssociateCourier method, of class CreateDeliveryController.
     */
    @Test
    public void testDeliveryRunAssociateCourier() {
        System.out.println("deliveryRunAssociateCourier");
        String emailCourier = "email";
        int deliveryRun = 1;
        boolean expResult = true;
        boolean result = cdC.deliveryRunAssociateCourier(emailCourier, deliveryRun);
        assertEquals(expResult, result);
        
        deliveryRun = 2;
        expResult = false;
        result = cdC.deliveryRunAssociateCourier(emailCourier, deliveryRun);
        assertEquals(expResult, result);
    }

    /**
     * Test of getHighestBatteryScooter method, of class CreateDeliveryController.
     */
    @Test
    public void testGetHighestBatteryScooter() {
        System.out.println("getHighestBatteryScooter");
        int pharmacyId = 0;
        Scooter expResult = null;
        Scooter result = cdC.getHighestBatteryScooter(pharmacyId);
        assertEquals(expResult, result);
        
        pharmacyId = 1;
        expResult = new Scooter(1, 1);
        result = cdC.getHighestBatteryScooter(pharmacyId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getHighestBatteryDrone method, of class CreateDeliveryController.
     */
    @Test
    public void testGetHighestBatteryDrone() {
        System.out.println("getHighestBatteryDrone");
        int pharmacyId = 0;
        Drone expResult = null;
        Drone result = cdC.getHighestBatteryDrone(pharmacyId);
        assertEquals(expResult, result);
        
        pharmacyId = 1;
        expResult = new Drone(1, 1, 1, 1);
        result = cdC.getHighestBatteryDrone(pharmacyId);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPurchaseOrdersFromDeliveryRun method, of class CreateDeliveryController.
     */
    @Test
    public void testGetPurchaseOrdersFromDeliveryRun() {
        System.out.println("getPurchaseOrdersFromDeliveryRun");
        int deliveryRun = 1;
        List<PurchaseOrder> expResult = pOrderEmpty;
        List<PurchaseOrder> result = cdC.getPurchaseOrdersFromDeliveryRun(deliveryRun);
        assertEquals(expResult, result);
        
        deliveryRun = 2;
        expResult = pWithOrder;
        result = cdC.getPurchaseOrdersFromDeliveryRun(deliveryRun);
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductLinesFromDeliveryRun method, of class CreateDeliveryController.
     */
    @Test
    public void testGetProductLinesFromDeliveryRun() {
        System.out.println("getProductLinesFromDeliveryRun");
        List<PurchaseOrder> purchaseOrderList = pOrderEmpty;
        List<ProductLine> expResult = pLineEmpty;
        List<ProductLine> result = cdC.getProductLinesFromDeliveryRun(purchaseOrderList);
        assertEquals(expResult, result);
        
        expResult = pLineWith;
        purchaseOrderList = pWithOrder;
        result = cdC.getProductLinesFromDeliveryRun(purchaseOrderList);
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductsFromDeliveryRun method, of class CreateDeliveryController.
     */
    @Test
    public void testGetProductsFromDeliveryRun() {
        System.out.println("getProductsFromDeliveryRun");
        List<ProductLine> productLineList = pLineEmpty;
        List<Product> expResult = pEmpty;
        List<Product> result = cdC.getProductsFromDeliveryRun(productLineList);
        assertEquals(expResult, result);
        
        productLineList = pLineWith;
        expResult = pWith;
        result = cdC.getProductsFromDeliveryRun(productLineList);
        assertEquals(expResult, result);
    }
    
}
