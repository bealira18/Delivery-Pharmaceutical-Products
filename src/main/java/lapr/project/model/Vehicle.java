package lapr.project.model;

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
    
    public Vehicle(int idVehicle){
        
        this.idVehicle = idVehicle;
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
    public String toString() {
        return "Vehicle{" + "idVehicle=" + idVehicle + ", idPharmacy=" + idPharmacy + ", weight=" + weight + ", aerodynamicCoeficient=" + aerodynamicCoeficient + ", frontalArea=" + frontalArea + ", motor=" + motor + ", currentBattery=" + currentBattery + ", maxBattery=" + maxBattery + '}';
    }

    @Override
    public int hashCode() {
        
        int hash = 3;
        hash = 13 * hash + this.idVehicle;
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
        Vehicle other = (Vehicle) obj;
        
        return idVehicle == other.idVehicle;
    }   
}
