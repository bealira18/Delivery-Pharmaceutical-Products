package lapr.project.model;

/**
 * This class serves as a container for a scooter information.
 * @author lapr3-2020-g052
 */
public class Scooter extends Vehicle {

    private int scooterStatusId;

    /**
     * Initializes the Scooter object with all parameters
     * @param idScooter scooter id
     * @param idPharmacy pharmacy id
     * @param weight weight in kg
     * @param aerodynamicCoeficient aerodynamic coeficient
     * @param frontalArea frontal area
     * @param motor motor
     * @param currentBattery current battery
     * @param maxBattery max battery
     * @param averageSpeed avarage speed
     * @param scooterStatusId scooter status id
     */
    public Scooter(int idScooter, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea, double motor,
            double currentBattery, double maxBattery, double averageSpeed, int scooterStatusId) {

        super(idScooter, idPharmacy, weight, aerodynamicCoeficient, frontalArea, motor, currentBattery, maxBattery, averageSpeed);
        this.scooterStatusId = scooterStatusId;
    }

    /**
     * Initializes the Scooter object with id and status
     * @param idVehicle scooter id
     * @param scooterStatusId scooter status id
     */
    public Scooter(int idVehicle, int scooterStatusId) {

        super(idVehicle);
        this.scooterStatusId = scooterStatusId;
    }

    /**
     * Gets scooter status id
     * @return scooter status id
     */
    public int getScooterStatusId() {

        return scooterStatusId;
    }

    /**
     * Gets the maximum payload a scooter can carry which is defined in the properties
     * @return scooter's max payload
     */
    public static double getScooterMaxPayload() {
        
        double scooterMaxPayload = Double.parseDouble(System.getProperty("scooter.max.payload", "0.0"));
        if (scooterMaxPayload <= 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative or 0 Scooter Max Payload). Please check your configuration file.");
        }
        return scooterMaxPayload;
    }

    /**
     * Sets the scooter status by id
     * @param scooterStatusId scooter status id
     */
    public void setScooterStatusId(int scooterStatusId) {

        this.scooterStatusId = scooterStatusId;
    }

    /**
     * Sets maximum payload a scooter can carry which is defined in the properties
     * @param scooterMaxPayload scooter's max payload
     */
    public static void setScooterMaxPayload(double scooterMaxPayload) {

        if (scooterMaxPayload <= 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative or 0 Scooter Max Payload)");
        }
        System.setProperty("scooter.max.payload", String.valueOf(scooterMaxPayload));
    }

    /**
     * Scooter's String format
     * @return Scooter's String
     */
    @Override
    public String toString() {

        return super.toString() + " Scooter{" + "scooterStatusId=" + scooterStatusId + '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 19 * hash + this.scooterStatusId;
        return hash;
    }

    /**
     * Compares the equality of the current object with the object of same type
     * @param obj object to compare with
     * @return true if equals, false if not
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Scooter) {
            return super.equals(obj) && scooterStatusId == ((Scooter) obj).scooterStatusId;
        }
        return false;
    }
}
