/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.EmailService;
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Ricardo
 */
public class VehicleParkingControllerTest {
    
    private static VehicleParkingController vpC;
    
    private static List<String> stub;
    
    public VehicleParkingControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
        VehicleDB vDB = mock(VehicleDB.class);
        stub = new ArrayList<>();
        stub.add("Name");
        stub.add("example@me.too");
        when(vDB.getEmailNameFromParkedVehicleResponsible(2)).thenReturn(stub);
        EmailService eS = mock(EmailService.class);
        System.setProperty("charger.comm.dir", "target/test-classes/");
        
        vpC = new VehicleParkingController();
        vpC = new VehicleParkingController(vDB, eS);
    }

    /**
     * Test of interpretChargerInfo method, of class VehicleParkingController.
     */
    @Test
    public void testInterpretChargerInfo() {
        System.out.println("interpretChargerInfo");
        
        vpC.interpretChargerInfo(null);
        String fileName = "park_test.data";
        vpC.interpretChargerInfo(fileName);
        
        fileName = "park_test_fail.data";
        vpC.interpretChargerInfo(fileName);
        
        final String fileName2 = "park_test_bad.data";
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            vpC.interpretChargerInfo(fileName2);
        });
        assertEquals("Unexpected number of arguments", ex.getMessage());
        
    }

    /**
     * Test of writeChargerRequest method, of class VehicleParkingController.
     */
    @Test
    public void testWriteChargerRequest() {
        System.out.println("writeChargerRequest");
        Vehicle vehicle = new Vehicle(2, 1, 1.0, 1.0, 1.0, 1.0, 84.0, 120.0, 999.0);
        boolean isReal = false;
        vpC.writeChargerRequest(vehicle, isReal);
        isReal = true;
        vpC.writeChargerRequest(vehicle, isReal);
        
    }
    
}
