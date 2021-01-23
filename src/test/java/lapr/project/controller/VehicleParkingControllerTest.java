/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.EmailService;
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle;
import lapr.project.utils.Constants;
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
    public void testInterpretChargerInfo() throws FileNotFoundException {
        System.out.println("interpretChargerInfo");
        
        assertThrows(FileNotFoundException.class, () -> {
            vpC.interpretChargerInfo(null);
        });
        
        
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
    }

    /**
     * Test of buildStatusEmail method, of class VehicleParkingController.
     */
    @Test
    public void testBuildStatusEmail() {
        System.out.println("buildStatusEmail");
        String name = "A name";
        LocalDateTime date = LocalDateTime.now();
        int vehicleID = 2;
        String vehicleType = "tractor";
        float timeToFull = 0.0F;
        String expResult = "Caro Sr/a A name," + System.getProperty(Constants.LINE_BREAK)
                + System.getProperty(Constants.LINE_BREAK)
                + "O veículo do tipo tractor de ID 2 estacionado às " + date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + " foi propriamente estacionado." + System.getProperty(Constants.LINE_BREAK)
                + "A bateria desta estará carregada em 0.00 horas.";
        StringBuilder result = VehicleParkingController.buildStatusEmail(name, date, vehicleID, vehicleType, timeToFull);
        assertEquals(expResult, result.toString());

        name = "another name";
        vehicleID = 3;
        vehicleType = "tricycle";
        timeToFull = 13.37F;
        expResult = "Caro Sr/a another name," + System.getProperty(Constants.LINE_BREAK)
                + System.getProperty(Constants.LINE_BREAK)
                + "O veículo do tipo tricycle de ID 3 estacionado às " + date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + " foi propriamente estacionado." + System.getProperty(Constants.LINE_BREAK)
                + "A bateria desta estará carregada em 13.37 horas.";
        result = VehicleParkingController.buildStatusEmail(name, date, vehicleID, vehicleType, timeToFull);
        assertEquals(expResult, result.toString());

        name = "yet another name";
        vehicleID = 5;
        vehicleType = "drift bathtub";
        timeToFull = -1.0F;
        expResult = "Caro Sr/a yet another name," + System.getProperty(Constants.LINE_BREAK)
                + System.getProperty(Constants.LINE_BREAK)
                + "O veículo do tipo drift bathtub de ID 5 estacionado às " + date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + " foi mal estacionado." + System.getProperty(Constants.LINE_BREAK)
                + "Por favor, regresse ao parque e estacione o veículo apropriadamente.";
        result = VehicleParkingController.buildStatusEmail(name, date, vehicleID, vehicleType, timeToFull);
        assertEquals(expResult, result.toString());
    }

    /**
     * Test of writeChargerInfo method, of class VehicleParkingController.
     */
    @Test
    public void testWriteChargerInfo() {
        System.out.println("writeChargerInfo");
        Vehicle vehicle = new Vehicle(2, 1, 1.0, 1.0, 1.0, 1.0, 84.0, 120.0, 999.0);
        long timestamp = 1611423663L;
        boolean isReal = true;
        String expResult = "1611423663;2;120;84";
        String result = VehicleParkingController.writeChargerInfo(vehicle, timestamp, isReal);
        assertEquals(expResult, result);
        isReal = false;
        expResult = "1611423663;2";
        result = VehicleParkingController.writeChargerInfo(vehicle, timestamp, isReal);
        assertEquals(expResult, result);
    }
}
