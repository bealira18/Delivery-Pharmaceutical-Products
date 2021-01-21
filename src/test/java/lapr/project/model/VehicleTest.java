package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    public VehicleTest() {
    }

    /**
     * Test of getIdVehicle method, of class Vehicle.
     */
    @Test
    public void testGetIdVehicle() {

        System.out.println("getIdVehicle");
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        int expResult = 1;
        int result = instance.getIdVehicle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdPharmacy method, of class Vehicle.
     */
    @Test
    public void testGetIdPharmacy() {

        System.out.println("getIdPharmacy");
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        int expResult = 1;
        int result = instance.getIdPharmacy();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeight method, of class Vehicle.
     */
    @Test
    public void testGetWeight() {

        System.out.println("getWeight");
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        double expResult = 1.0;
        double result = instance.getWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getAerodynamicCoeficient method, of class Vehicle.
     */
    @Test
    public void testGetAerodynamicCoeficient() {

        System.out.println("getAerodynamicCoeficient");
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        double expResult = 1.0;
        double result = instance.getAerodynamicCoeficient();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getFrontalArea method, of class Vehicle.
     */
    @Test
    public void testGetFrontalArea() {

        System.out.println("getFrontalArea");
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        double expResult = 1.0;
        double result = instance.getFrontalArea();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMotor method, of class Vehicle.
     */
    @Test
    public void testGetMotor() {

        System.out.println("getMotor");
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        double expResult = 1.0;
        double result = instance.getMotor();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCurrentBattery method, of class Vehicle.
     */
    @Test
    public void testGetCurrentBattery() {

        System.out.println("getCurrentBattery");
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        double expResult = 1.0;
        double result = instance.getCurrentBattery();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaxBattery method, of class Vehicle.
     */
    @Test
    public void testGetMaxBattery() {

        System.out.println("getMaxBattery");
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        double expResult = 1.0;
        double result = instance.getMaxBattery();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setIdVehicle method, of class Vehicle.
     */
    @Test
    public void testSetIdVehicle() {

        System.out.println("setIdVehicle");
        int idVehicle = 2;
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        instance.setIdVehicle(idVehicle);
        int result = instance.getIdVehicle();
        assertEquals(idVehicle, result);
    }

    /**
     * Test of setIdPharmacy method, of class Vehicle.
     */
    @Test
    public void testSetIdPharmacy() {

        System.out.println("setIdPharmacy");
        int idPharmacy = 2;
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        instance.setIdPharmacy(idPharmacy);
        int result = instance.getIdPharmacy();
        assertEquals(idPharmacy, result);
    }

    /**
     * Test of setWeight method, of class Vehicle.
     */
    @Test
    public void testSetWeight() {

        System.out.println("setWeight");
        double weight = 2.0;
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        instance.setWeight(weight);
        double result = instance.getWeight();
        assertEquals(weight, result, 0.0);
    }

    /**
     * Test of setAerodynamicCoeficient method, of class Vehicle.
     */
    @Test
    public void testSetAerodynamicCoeficient() {

        System.out.println("setAerodynamicCoeficient");
        double aerodynamicCoeficient = 2.0;
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        instance.setAerodynamicCoeficient(aerodynamicCoeficient);
        double result = instance.getAerodynamicCoeficient();
        assertEquals(aerodynamicCoeficient, result);
    }

    /**
     * Test of setFrontalArea method, of class Vehicle.
     */
    @Test
    public void testSetFrontalArea() {

        System.out.println("setFrontalArea");
        double frontalArea = 2.0;
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        instance.setFrontalArea(frontalArea);
        double result = instance.getFrontalArea();
        assertEquals(frontalArea, result);
    }

    /**
     * Test of setMotor method, of class Vehicle.
     */
    @Test
    public void testSetMotor() {

        System.out.println("setMotor");
        double motor = 2.0;
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        instance.setMotor(motor);
        double result = instance.getMotor();
        assertEquals(motor, result);
    }

    /**
     * Test of setCurrentBattery method, of class Vehicle.
     */
    @Test
    public void testSetCurrentBattery() {

        System.out.println("setCurrentBattery");
        double currentBattery = 2.0;
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        instance.setCurrentBattery(currentBattery);
        double result = instance.getCurrentBattery();
        assertEquals(currentBattery, result);
    }

    /**
     * Test of setMaxBattery method, of class Vehicle.
     */
    @Test
    public void testSetMaxBattery() {

        System.out.println("setMaxBattery");
        double maxBattery = 2.0;
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        instance.setMaxBattery(maxBattery);
        double result = instance.getMaxBattery();
        assertEquals(maxBattery, result);
    }

    /**
     * Test of toString method, of class Vehicle.
     */
    @Test
    public void testToString() {

        System.out.println("toString");
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        String expResult = "Vehicle{idVehicle=1, idPharmacy=1, weight=1.0, aerodynamicCoeficient=1.0,"
                + " frontalArea=1.0, motor=1.0, currentBattery=1.0, maxBattery=1.0, averageSpeed=8.9}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Vehicle.
     */
    @Test
    public void testHashCode() {

        System.out.println("hashCode");
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        int expResult = 40;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Vehicle.
     */
    @Test
    public void testEquals() {

        System.out.println("equals");
        Object obj = null;
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

        result = instance.equals(obj);
        assertEquals(false, result);

        obj = "";
        result = instance.equals(obj);
        assertEquals(false, result);

        result = instance.equals(new Vehicle(2, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9));
        assertEquals(false, result);

        result = instance.equals(new Vehicle(1, 2, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 8.9));
        assertEquals(true, result);
    }

    /**
     * Test of getAverageSpeed method, of class Vehicle.
     */
    @Test
    public void testGetAverageSpeed() {

        System.out.println("getAverageSpeed");
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        double expResult = 8.9;
        double result = instance.getAverageSpeed();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setAverageSpeed method, of class Vehicle.
     */
    @Test
    public void testSetAverageSpeed() {
        System.out.println("setAverageSpeed");
        double averageSpeed = 9;
        Vehicle instance = new Vehicle(1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 8.9);
        instance.setAverageSpeed(averageSpeed);
        double result = instance.getAverageSpeed();
        assertEquals(averageSpeed, result);
    }
}
