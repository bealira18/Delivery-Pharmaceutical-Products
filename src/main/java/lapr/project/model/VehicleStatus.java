package lapr.project.model;

public class VehicleStatus {

    private int vehicleStatusId;
    private String vehicleStatusName;

    /**
     * Creates an object of VehicleStatus class
     * @param id - status' id
     * @param name - status' name
     */
    public VehicleStatus(int id, String name) {

        this.vehicleStatusId = id;
        this.vehicleStatusName = name;
    }

    /**
     * Gets status' id
     * @return Integer with status' id
     */
    public int getVehicleStatusId() {

        return vehicleStatusId;
    }

    /**
     * Gets status' name
     * @return String with status' name
     */
    public String getVehicleStatusName() {

        return vehicleStatusName;
    }

    /**
     * Sets the vehicle status id
     * @param vehicleStatusId the id of the vehicle status
     */
    public void setVehicleStatusId(int vehicleStatusId) {

        this.vehicleStatusId = vehicleStatusId;
    }

    /**
     * Sets the vehiclestatus name
     * @param vehicleStatusName the name of vehicle status
     */
    public void setVehicleStatusName(String vehicleStatusName) {

        this.vehicleStatusName = vehicleStatusName;
    }

    /**
     * VehicleStatus' String format
     * @return the VehicleStatus's String
     */
    @Override
    public String toString() {

        return "VehicleStatus{" + "vehicleStatusId=" + vehicleStatusId + ", vehicleStatusName=" + vehicleStatusName + '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 79 * hash + this.vehicleStatusId;
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
        final VehicleStatus other = (VehicleStatus) obj;

        return this.vehicleStatusId == other.vehicleStatusId;
    }
}
