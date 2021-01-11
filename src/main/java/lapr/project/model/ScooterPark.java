package lapr.project.model;

public class ScooterPark {

    private int scooterParkId;
    private int pharmacyId;
    private int limit;
    private int numChargingStations;
    private Address address;

    public ScooterPark(int scooterParkId, int pharmacyId, int limit, int numChargingStations, Address address) {

        this.scooterParkId = scooterParkId;
        this.pharmacyId = pharmacyId;
        this.limit = limit;
        this.numChargingStations = numChargingStations;
        this.address = address;
    }

    public int getScooterParkId() {

        return scooterParkId;
    }

    public int getPharmacyId() {

        return pharmacyId;
    }

    public int getLimit() {

        return limit;
    }

    public int getNumChargingStations() {

        return numChargingStations;
    }

    public Address getAddress() {

        return address;
    }

    public void setScooterParkId(int scooterParkId) {

        this.scooterParkId = scooterParkId;
    }

    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    public void setLimit(int limit) {

        this.limit = limit;
    }

    public void setNumChargingStations(int numChargingStations) {

        this.numChargingStations = numChargingStations;
    }

    public void setAddress(Address address) {

        this.address = address;
    }

    @Override
    public String toString() {

        return "ScooterPark{" + "scooterParkId=" + scooterParkId + ", pharmacyId=" + pharmacyId + ", limit=" + limit + ", numChargingStations=" + numChargingStations + ", address=" + address + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 61 * hash + this.scooterParkId;
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
        final ScooterPark other = (ScooterPark) obj;

        return this.scooterParkId == other.scooterParkId;
    }
}
