package lapr.project.model;

import java.util.Objects;

public class Vehicle {

    private int idVehicle;
    private int idPharmacy;
    private double weight;
    private double aerodynamicCoeficient;
    private double frontalArea;
    private double motor;
    private double currentBattery;
    private double maxBattery;

    public Vehicle(int idVehicle, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea, double motor, double currentBattery, double maxBattery) {
        this.idVehicle = idVehicle;
        this.idPharmacy = idPharmacy;
        this.weight = weight;
        this.aerodynamicCoeficient = aerodynamicCoeficient;
        this.frontalArea = frontalArea;
        this.motor = motor;
        this.currentBattery = currentBattery;
        this.maxBattery = maxBattery;
    }

    public int getIdVehicle() {
        return idVehicle;
    }

    public int getIdPharmacy() {
        return idPharmacy;
    }

    public double getWeight() {
        return weight;
    }

    public double getAerodynamicCoeficient() {
        return aerodynamicCoeficient;
    }

    public double getFrontalArea() {
        return frontalArea;
    }

    public double getMotor() {
        return motor;
    }

    public double getCurrentBattery() {
        return currentBattery;
    }

    public double getMaxBattery() {
        return maxBattery;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public void setIdPharmacy(int idPharmacy) {
        this.idPharmacy = idPharmacy;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAerodynamicCoeficient(double aerodynamicCoeficient) {
        this.aerodynamicCoeficient = aerodynamicCoeficient;
    }

    public void setFrontalArea(double frontalArea) {
        this.frontalArea = frontalArea;
    }

    public void setMotor(double motor) {
        this.motor = motor;
    }

    public void setCurrentBattery(double currentBattery) {
        this.currentBattery = currentBattery;
    }

    public void setMaxBattery(double maxBattery) {
        this.maxBattery = maxBattery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return getIdVehicle() == vehicle.getIdVehicle() &&
                getIdPharmacy() == vehicle.getIdPharmacy() &&
                Double.compare(vehicle.getWeight(), getWeight()) == 0 &&
                Double.compare(vehicle.getAerodynamicCoeficient(), getAerodynamicCoeficient()) == 0 &&
                Double.compare(vehicle.getFrontalArea(), getFrontalArea()) == 0 &&
                Double.compare(vehicle.getMotor(), getMotor()) == 0 &&
                Double.compare(vehicle.getCurrentBattery(), getCurrentBattery()) == 0 &&
                Double.compare(vehicle.getMaxBattery(), getMaxBattery()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVehicle(), getIdPharmacy(), getWeight(), getAerodynamicCoeficient(), getFrontalArea(), getMotor(), getCurrentBattery(), getMaxBattery());
    }

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
                '}';
    }
}
