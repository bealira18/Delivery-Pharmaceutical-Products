package lapr.project.model;

public class Pharmacy {

    private int pharmacyId;
    private String name;
    private Address address;

    /**
     * Initializes the Pharmacy object
     * @param id pharmacy id
     * @param name pharmacy name
     * @param address pharmacy Address
     */
    public Pharmacy(int id, String name, Address address) {

        this.pharmacyId = id;
        this.name = name;
        this.address = address;
    }

    /**
     * Gets the pharmacy id
     * @return pharmacy id
     */
    public int getId() {

        return pharmacyId;
    }

    /**
     * Gets the pharmacy name
     * @return pharmacy name
     */
    public String getName() {

        return name;
    }

    /**
     * Gets the pharmacy Address
     * @return pharmacy Address
     */
    public Address getAddress() {

        return address;
    }

    /**
     * Sets the pharmacy id
     * @param id pharmacy id
     */
    public void setId(int id) {

        this.pharmacyId = id;
    }

    /**
     * Sets the pharmacy name
     * @param name pharmacy name
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Sets the pharmacy Address
     * @param address pharmacy Address
     */
    public void setAddress(Address address) {

        this.address = address;
    }

    /**
     * Pharmacy's String format
     * @return Pharmacy's String
     */
    @Override
    public String toString() {

        return "Pharmacy{" + "id=" + pharmacyId + ", name=" + name + ", address=" + address + '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 5;
        hash = 83 * hash + this.pharmacyId;
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
        final Pharmacy other = (Pharmacy) obj;

        return this.pharmacyId == other.pharmacyId;
    }
}
