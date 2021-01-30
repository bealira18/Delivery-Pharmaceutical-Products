package lapr.project.model;

/**
 * This class serves as a container for a vehicle information.
 * @author lapr3-2020-g052
 */
public class Vehicle {

    private int idVehicle;
    private int idPharmacy;
    private double weight;
    private double aerodynamicCoeficient;
    private double frontalArea;
    private double motor;
    private double currentBattery;
    private double maxBattery;
    private double averageSpeed;

    /**
     * Initializes the Vehicle object with all parameters
     * @param idVehicle vehicle id
     * @param idPharmacy pharmacy id
     * @param weight weight in kg
     * @param aerodynamicCoeficient aerodynamic coeficient
     * @param frontalArea frontal area
     * @param motor motor
     * @param currentBattery current battery
     * @param maxBattery max battery
     * @param averageSpeed avarage speed
     */
    public Vehicle(int idVehicle, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea, double motor, double currentBattery, double maxBattery, double averageSpeed) {

        this.idVehicle = idVehicle;
        this.idPharmacy = idPharmacy;
        this.weight = weight;
        this.aerodynamicCoeficient = aerodynamicCoeficient;
        this.frontalArea = frontalArea;
        this.motor = motor;
        this.currentBattery = currentBattery;
        this.maxBattery = maxBattery;
        this.averageSpeed = averageSpeed;
    }

    /**
     * Initializes the Vehicle object with only the id
     * @param idVehicle vehicle id
     */
    public Vehicle(int idVehicle){
        
        this.idVehicle = idVehicle;
    }

    /**
     * Gets vehicle id
     * @return the vehicle's id
     */
    public int getIdVehicle() {
        
        return idVehicle;
    }

    /**
     * Gets pharmacy id
     * @return the pharmacy's id
     */
    public int getIdPharmacy() {
        
        return idPharmacy;
    }

    /**
     * Gets the weight of the vehicle
     * @return the vehicle's weight
     */
    public double getWeight() {
        
        return weight;
    }

    /**
     * Gets the aerodynamic coeficient of the vehicle
     * @return the vehicle's aerodynamic coeficient
     */
    public double getAerodynamicCoeficient() {
        
        return aerodynamicCoeficient;
    }

    /**
     * Gets the frontal area of the vehicle
     * @return the vehicle's frontal area
     */
    public double getFrontalArea() {
        
        return frontalArea;
    }

    /**
     * Gets the motor's potency of the vehicle
     * @return the vehicle's motor potency
     */
    public double getMotor() {
        
        return motor;
    }

    /**
     * Gets the current battery of the vehicle
     * @return the vehicle's current battery
     */
    public double getCurrentBattery() {
        
        return currentBattery;
    }

    /**
     * Gets the max battery of the vehicle
     * @return the vehicle's maximumm battery
     */
    public double getMaxBattery() {
        
        return maxBattery;
    }

    /**
     * Gets the average speed of the vehicle
     * @return the vehicle's average speed
     */
    public double getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * Sets the vehicle's id
     * @param idVehicle the vehicle's id
     */
    public void setIdVehicle(int idVehicle) {
        
        this.idVehicle = idVehicle;
    }

    /**
     * Sets the pharmacy's id
     * @param idPharmacy the pharmacy's id
     */
    public void setIdPharmacy(int idPharmacy) {
        
        this.idPharmacy = idPharmacy;
    }

    /**
     * Setsthe vehicle's weight
     * @param weight the vehicle's weight
     */
    public void setWeight(double weight) {
        
        this.weight = weight;
    }

    /**
     * Sets the vehicle's aerodynamic coeficient
     * @param aerodynamicCoeficient the vehicle's aerodynamic coeficient
     */
    public void setAerodynamicCoeficient(double aerodynamicCoeficient) {
        
        this.aerodynamicCoeficient = aerodynamicCoeficient;
    }

    /**
     * Sets the vehicle's frontal area
     * @param frontalArea the vehicle's frontal area
     */
    public void setFrontalArea(double frontalArea) {
        
        this.frontalArea = frontalArea;
    }

    /**
     * Sets the vehicle's motor
     * @param motor the vehicle's motor
     */
    public void setMotor(double motor) {
        
        this.motor = motor;
    }

    /**
     * Sets the vehicle's current battery
     * @param currentBattery the vehicle's current battery
     */
    public void setCurrentBattery(double currentBattery) {
        
        this.currentBattery = currentBattery;
    }

    /**
     * Sets the vehicle's maximumm battery
     * @param maxBattery the vehicle's maximumm battery
     */
    public void setMaxBattery(double maxBattery) {
        
        this.maxBattery = maxBattery;
    }

    /**
     * Sets the vehicle's average speed
     * @param averageSpeed the vehicle's average speed
     */
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    /**
     * Vehicle's String format
     * @return the Vehicle's String
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "idVehicle=" + idVehicle +
                ", idPharmacy=" + idPharmacy +
                ", weight=" + weight +
                ", aerodynamicCoeficient=" + aerodynamicCoeficient +
                ", frontalArea=" + frontalArea +
                ", motor=" + motor +
                ", currentBattery=" + currentBattery +
                ", maxBattery=" + maxBattery +
                ", averageSpeed=" + averageSpeed +
                '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {
        
        int hash = 3;
        hash = 13 * hash + this.idVehicle;
        return hash;
    }

    /**
     * Compares the equality of the current object with the object of same type
     * @param obj object to compare with
     * @return true if equals, false if not
     */
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vehicle other = (Vehicle) obj;
        
        return idVehicle == other.idVehicle;
    }   
}
