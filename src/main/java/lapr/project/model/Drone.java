package lapr.project.model;

/**
 * This class serves as a container for a drone information.
 * @author lapr3-2020-g052
 */
public class Drone extends Vehicle {

    private int droneStatusId;
    private double width;
    private double averageVerticalSpeed;

    /**
     * Initializes the Drone object with all parameters
     * @param idVehicle drone id
     * @param idPharmacy pharmacy id
     * @param weight weight in kg
     * @param aerodynamicCoeficient aerodynamic coeficient
     * @param frontalArea frontal area
     * @param motor motor
     * @param currentBattery current battery
     * @param maxBattery max battery
     * @param averageSpeed avarage speed
     * @param width width
     * @param averageVerticalSpeed average vertical speed
     * @param droneStatusId drone status
     */
    public Drone(int idVehicle, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea, double motor,
            double currentBattery, double maxBattery, double averageSpeed, double width, double averageVerticalSpeed, int droneStatusId) {
        super(idVehicle, idPharmacy, weight, aerodynamicCoeficient, frontalArea, motor, currentBattery, maxBattery, averageSpeed);
        this.width = width;
        this.averageVerticalSpeed = averageVerticalSpeed;
        this.droneStatusId = droneStatusId;
    }

    /**
     * Initializes the Drone object with id, width, average vertical spped and status
     * @param idVehicle drone id
     * @param width width
     * @param averageVerticalSpeed average vertical speed
     * @param droneStatusId drone status
     */
    public Drone(int idVehicle, double width, double averageVerticalSpeed, int droneStatusId) {
        super(idVehicle);
        this.width = width;
        this.averageVerticalSpeed = averageVerticalSpeed;
        this.droneStatusId = droneStatusId;
    }

    /**
     * Gets drone status id
     * @return drone status id
     */
    public int getDroneStatusId() {
        return droneStatusId;
    }

    /**
     * Gets drone width
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets drone average vertical speed
     * @return average vertical speed
     */
    public double getAverageVerticalSpeed() {
        return averageVerticalSpeed;
    }

    /**
     * Gets drone maximum payload defined in the properties
     * @return max payload
     */
    public static double getDroneMaxPayload() {
        
        double droneMaxPayload = Double.parseDouble(System.getProperty("drone.max.payload", "0.0"));
        if (droneMaxPayload <= 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative or 0 Drone Max Payload). Please check your configuration file.");
        }
        return droneMaxPayload;
    }

    /**
     * Sets the drone status by id
     * @param droneStatusId drone status id
     */
    public void setDroneStatusId(int droneStatusId) {
        this.droneStatusId = droneStatusId;
    }

    /**
     * Sets the drone width
     * @param width drone width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Sets the drone average vertical speed
     * @param averageVerticalSpeed average vertical speed
     */
    public void setAverageVerticalSpeed(double averageVerticalSpeed) {
        this.averageVerticalSpeed = averageVerticalSpeed;
    }

    /**
     * Sets drone maximum payload in the properties
     * @param droneMaxPayload
     */
    public static void setDroneMaxPayload(double droneMaxPayload) {

        if (droneMaxPayload <= 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative or 0 Drone Max Payload)");
        }
        System.setProperty("drone.max.payload", String.valueOf(droneMaxPayload));
    }

    /**
     * Drone's String format
     * @return Drone's String
     */
    @Override
    public String toString() {
        return "Drone{"
                + "droneStatusId=" + droneStatusId
                + ", width=" + width
                + ", averageVerticalSpeed=" + averageVerticalSpeed
                + '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 89 * hash + this.droneStatusId;
        return hash;
    }

    /**
     * Compares the equality of the current object with the object of same type
     * @param obj object to compare with
     * @return true if equals, false if not
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Drone) {
            return super.equals(obj) && droneStatusId == ((Drone) obj).droneStatusId;
        }
        return false;
    }
}
