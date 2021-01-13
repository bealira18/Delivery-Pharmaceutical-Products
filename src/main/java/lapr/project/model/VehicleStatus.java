package lapr.project.model;

public class VehicleStatus {

    private int vehicleStatusId;
    private String vehicleStatusName;

    public VehicleStatus(int id, String name) {

        this.vehicleStatusId = id;
        this.vehicleStatusName = name;
    }

    public int getVehicleStatusId() {

        return vehicleStatusId;
    }

    public String getVehicleStatusName() {

        return vehicleStatusName;
    }

    public void setVehicleStatusId(int scooterStatusId) {

        this.vehicleStatusId = scooterStatusId;
    }

    public void setVehicleStatusName(String scooterStatusName) {

        this.vehicleStatusName = scooterStatusName;
    }

    @Override
    public String toString() {

        return "VehicleStatus{" + "vehicleStatusId=" + vehicleStatusId + ", vehicleStatusName=" + vehicleStatusName + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 79 * hash + this.vehicleStatusId;
        return hash;
    }

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
