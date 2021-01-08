package lapr.project.model;

public class Scooter {

    private int id;
    private int pharmacyId;
    private int scooterStatusId;
    private double currentBattery;
    private double maxBattery;

    public Scooter(int id, int pharmacyId, int scooterStatusId, double currentBattery, double maxBattery) {

        this.id = id;
        this.pharmacyId = pharmacyId;
        this.scooterStatusId = scooterStatusId;
        this.currentBattery = currentBattery;
        this.maxBattery = maxBattery;
    }

    public Scooter() {

    }

    public int getId() {

        return id;
    }

    public int getPharmacyId() {

        return pharmacyId;
    }

    public int getScooterStatusId() {

        return scooterStatusId;
    }

    public double getCurrentBattery() {

        return currentBattery;
    }

    public double getMaxBattery() {

        return maxBattery;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    public void setScooterStatusId(int scooterStatusId) {

        this.scooterStatusId = scooterStatusId;
    }

    public void setCurrentBattery(double currentBattery) {

        this.currentBattery = currentBattery;
    }

    public void setMaxBattery(double maxBattery) {

        this.maxBattery = maxBattery;
    }

    @Override
    public String toString() {

        return "Scooter{" + "id=" + id + ", pharmacyId=" + pharmacyId + ", scooterStatusId=" + scooterStatusId + ", currentBattery=" + currentBattery + ", maxBattery=" + maxBattery + '}';
    }

    @Override
    public int hashCode() {

        int hash = 5;
        hash = 67 * hash + this.id;
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
        final Scooter other = (Scooter) obj;

        return this.id == other.id;
    }
}
