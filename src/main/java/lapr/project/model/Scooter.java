package lapr.project.model;

import java.util.Objects;

public class Scooter extends Vehicle{

    private int scooterStatusId;

    public Scooter(int idScooter, int idPharmacy, double weight, double aerodynamicCoeficient, double frontalArea, double motor, double currentBattery, double maxBattery, int scooterStatusId) {
        super(idScooter, idPharmacy, weight, aerodynamicCoeficient, frontalArea, motor, currentBattery, maxBattery);
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
        return "Scooter{" +
                "scooterStatusId=" + scooterStatusId +
                '}';
    }
}
