package lapr.project.model;

public class ScooterPark {

    private static int numChargingStations=0;

    private int id;
    private int pharmacyId;
    private int limit;
    private String address;

    public ScooterPark(int id, int pharmacyId, int limit, int numChargingStations, String address) {

        this.id = id;
        this.pharmacyId = pharmacyId;
        this.limit = limit;
        setNumChargingStations(numChargingStations);
        this.address = address;
    }

    public ScooterPark() {

    }

    public int getId() {

        return id;
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

    public String getAddress() {

        return address;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    public void setLimit(int limit) {

        this.limit = limit;
    }

    public static void setNumChargingStations(int numChargingStations) {

        if (numChargingStations < 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative Number of Charging Stations)");
        }
        ScooterPark.numChargingStations=numChargingStations;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    @Override
    public String toString() {

        return "ScooterPark{" + "id=" + id + ", pharmacyId=" + pharmacyId + ", limit=" + limit + ", numChargingStations=" + numChargingStations + ", address=" + address + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 61 * hash + this.id;
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

        return this.id == other.id;
    }
}
