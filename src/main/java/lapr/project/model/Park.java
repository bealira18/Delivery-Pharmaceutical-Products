package lapr.project.model;

/**
 * This class serves as a container for a park information.
 * @author lapr3-2020-g052
 */
public class Park {

    private int parkId;
    private int pharmacyId;
    private int limit;
    private int numChargingStations;
    private String category;
    private Address address;
    private double maxChargingPotency;

    /**
     * Initializes the Park object
     * @param parkId park id
     * @param pharmacyId park's pharmacy id
     * @param limit park vehicle limit
     * @param numChargingStations park number of charging stations
     * @param category park category ("drone", "scooter")
     * @param address park Address
     * @param maxChargingPotency park charging potency
     */
    public Park(int parkId, int pharmacyId, int limit, int numChargingStations, String category, Address address, double maxChargingPotency) {

        this.parkId = parkId;
        this.pharmacyId = pharmacyId;
        this.limit = limit;
        this.numChargingStations = numChargingStations;
        this.category = category;
        this.address = address;
        this.maxChargingPotency = maxChargingPotency;
    }

    /**
     * Gets the park id
     * @return park id
     */
    public int getParkId() {

        return parkId;
    }

    /**
     * Gets the park's pharmacy id
     * @return park's pharmacy id
     */
    public int getPharmacyId() {

        return pharmacyId;
    }

    /**
     * Gets the park limit
     * @return park limit
     */
    public int getLimit() {

        return limit;
    }

    /**
     * Gets the number of charging stations in the park
     * @return number of charging stations
     */
    public int getNumChargingStations() {

        return numChargingStations;
    }

    /**
     * Gets the park category
     * @return park category
     */
    public String getCategory() {

        return category;
    }

    /**
     * Gets the park address
     * @return Address object with park's location
     */
    public Address getAddress() {

        return address;
    }

    /**
     * Gets the park charging potency
     * @return charging potency
     */
    public double getMaxChargingPotency() {
        return maxChargingPotency;
    }

    /**
     * Sets the park id
     * @param scooterParkId park id
     */
    public void setParkId(int scooterParkId) {

        this.parkId = scooterParkId;
    }

    /**
     * Sets the pharmacy id
     * @param pharmacyId pharmacy id
     */
    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    /**
     * Sets the park limit
     * @param limit park limit
     */
    public void setLimit(int limit) {

        this.limit = limit;
    }

    /**
     * Sets the number of charging stations in the park
     * @param numChargingStations number of charging stations
     */
    public void setNumChargingStations(int numChargingStations) {

        this.numChargingStations = numChargingStations;
    }

    /**
     * Sets the park category
     * @param category park category ("scooter" or "drone")
     */
    public void setCategory(String category) {

        this.category = category;
    }

    /**
     * Sets the park Address
     * @param address Address object with park's location
     */
    public void setAddress(Address address) {

        this.address = address;
    }

    /**
     * Sets the park charging potency
     * @param maxChargingPotency charging potency
     */
    public void setMaxChargingPotency(double maxChargingPotency) {
        this.maxChargingPotency = maxChargingPotency;
    }

    /**
     * Park's Sting format
     * @return Park's String
     */
    @Override
    public String toString() {
        return "Park{" +
                "parkId=" + parkId +
                ", pharmacyId=" + pharmacyId +
                ", limit=" + limit +
                ", numChargingStations=" + numChargingStations +
                ", category='" + category + '\'' +
                ", address=" + address +
                ", maxChargingPotency=" + maxChargingPotency +
                '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 3;
        hash = 61 * hash + this.parkId;
        return hash;
    }

    /**
     * Compares the equality of the current object with the object of same type
     * @param obj object to compare with
     * @return true if equals, false if not
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Park other = (Park) obj;

        return this.parkId == other.parkId;
    }
}
