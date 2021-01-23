package lapr.project.model;

public class Drone extends Vehicle {

    private int droneStatusId;
    private double width;
    private double averageVerticalSpeed;

    public Drone(int idVehicle, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea, double motor,
            double currentBattery, double maxBattery, double averageSpeed, double width, double averageVerticalSpeed, int droneStatusId) {
        super(idVehicle, idPharmacy, weight, aerodynamicCoeficient, frontalArea, motor, currentBattery, maxBattery, averageSpeed);
        this.width = width;
        this.averageVerticalSpeed = averageVerticalSpeed;
        this.droneStatusId = droneStatusId;
    }

    public Drone(int idVehicle, double width, double averageVerticalSpeed, int droneStatusId) {
        super(idVehicle);
        this.width = width;
        this.averageVerticalSpeed = averageVerticalSpeed;
        this.droneStatusId = droneStatusId;
    }

    public int getDroneStatusId() {
        return droneStatusId;
    }

    public double getWidth() {
        return width;
    }

    public double getAverageVerticalSpeed() {
        return averageVerticalSpeed;
    }

    public void setDroneStatusId(int droneStatusId) {
        this.droneStatusId = droneStatusId;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setAverageVerticalSpeed(double averageVerticalSpeed) {
        this.averageVerticalSpeed = averageVerticalSpeed;
    }

    @Override
    public String toString() {
        return "Drone{"
                + "droneStatusId=" + droneStatusId
                + ", width=" + width
                + ", averageVerticalSpeed=" + averageVerticalSpeed
                + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 89 * hash + this.droneStatusId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Drone) {
            return super.equals(obj) && droneStatusId == ((Drone) obj).droneStatusId;
        }
        return false;
    }
}
