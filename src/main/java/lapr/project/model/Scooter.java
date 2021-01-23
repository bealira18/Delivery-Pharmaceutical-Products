package lapr.project.model;

public class Scooter extends Vehicle {

    private int scooterStatusId;

    public Scooter(int idScooter, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea, double motor,
            double currentBattery, double maxBattery, double averageSpeed, int scooterStatusId) {

        super(idScooter, idPharmacy, weight, aerodynamicCoeficient, frontalArea, motor, currentBattery, maxBattery, averageSpeed);
        this.scooterStatusId = scooterStatusId;
    }

    public Scooter(int idVehicle, int scooterStatusId) {

        super(idVehicle);
        this.scooterStatusId = scooterStatusId;
    }

    public int getScooterStatusId() {

        return scooterStatusId;
    }

    public void setScooterStatusId(int scooterStatusId) {

        this.scooterStatusId = scooterStatusId;
    }

    @Override
    public String toString() {

        return super.toString() + " Scooter{" + "scooterStatusId=" + scooterStatusId + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 19 * hash + this.scooterStatusId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Scooter) {
            return super.equals(obj) && scooterStatusId == ((Scooter) obj).scooterStatusId;
        }
        return false;
    }
}
