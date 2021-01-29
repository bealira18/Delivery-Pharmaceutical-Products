package lapr.project.model;

public class ParkingSpace {

    private int parkingSpaceId;
    private int parkId;
    private int vehicleId;
    private boolean isChargingStation;

    /**
     * Initializes the ParkingSpace object
     * @param parkingSpaceId parking space id
     * @param parkId park id
     * @param vehicleId parked vehicle id
     * @param isChargingStation true if it's a charging station, false if is not
     */
    public ParkingSpace(int parkingSpaceId, int parkId, int vehicleId, boolean isChargingStation) {

        this.parkingSpaceId = parkingSpaceId;
        this.parkId = parkId;
        this.vehicleId = vehicleId;
        this.isChargingStation = isChargingStation;
    }

    /**
     *Gets the parking space id
     * @return parking space id
     */
    public int getParkingSpaceId() {

        return parkingSpaceId;
    }

    /**
     * Gets the park id
     * @return park id
     */
    public int getParkId() {

        return parkId;
    }

    /**
     * Gets the parked vehicle id
     * @return parked vehicle id, 0 if none
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Gets the value of being a charging station
     * @return true if is a charging station, false if it isn't
     */
    public boolean isIsChargingStation() {

        return isChargingStation;
    }

    /**
     * Sets the parking space id
     * @param parkingSpaceId parking space id
     */
    public void setParkingSpaceId(int parkingSpaceId) {

        this.parkingSpaceId = parkingSpaceId;
    }

    /**
     * Sets the park id
     * @param parkId park id
     */
    public void setParkId(int parkId) {

        this.parkId = parkId;
    }

    /**
     * Sets the parked vehicle id
     * @param vehicleId parked vehicle id, 0 if none
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Sets the parking space as a charging station or as not a charging station
     * @param isChargingStation true if it's a charging station, false if it is not
     */
    public void setIsChargingStation(boolean isChargingStation) {

        this.isChargingStation = isChargingStation;
    }

    /**
     * ParkingSpace's String format
     * @return ParkingSpace's String
     */
    @Override
    public String toString() {
        return "ParkingSpace{" + "parkingSpaceId=" + parkingSpaceId + ", parkId=" + parkId + ", vehicleId=" + vehicleId + ", isChargingStation=" + isChargingStation + '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 3;
        hash = 43 * hash + this.parkingSpaceId;
        hash = 43 * hash + this.parkId;
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
        ParkingSpace other = (ParkingSpace) obj;

        return parkingSpaceId == other.parkingSpaceId
                && parkId == other.parkId;
    }
}
