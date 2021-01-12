package lapr.project.model;

public class Drone extends Vehicle{

    private int droneStatusId;

    public Drone(int idVehicle, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea, double motor, double currentBattery, double maxBattery, int droneStatusId) {
        super(idVehicle, idPharmacy, weight, aerodynamicCoeficient, frontalArea, motor, currentBattery, maxBattery);
        this.droneStatusId=droneStatusId;
    }

    public Drone(int idVehicle, int droneStatusId) {
        super(idVehicle);
        this.droneStatusId = droneStatusId;
    }

    public int getDroneStatusId() {
        return droneStatusId;
    }

    public void setDroneStatusId(int droneStatusId) {
        this.droneStatusId = droneStatusId;
    }

    @Override
    public String toString() {
        return super.toString() + " Drone{" + "droneStatusId=" + droneStatusId + '}';
    }
}
