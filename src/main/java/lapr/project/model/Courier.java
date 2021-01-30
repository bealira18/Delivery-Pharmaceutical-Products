package lapr.project.model;

/**
 * This class serves as a container for the Courier role and it's specific
 * information.
 * @author lapr3-2020-g052
 */
public class Courier extends RegisteredUser {

    /**
     * The name of the courier.
     */
    private String name;

    /**
     * The NIF of the courier.
     */
    private int nif;

    /**
     * The Social Security number of the courier.
     */
    private long socialSecurity;

    /**
     * The id of the pharmacy that this courier is associated to.
     */
    private int pharmacyId;

    /**
     * The weight of the couier.
     */
    private double weight;

    /**
     * Creates a instance of the Courier Object with the given arguments.
     *
     * @param email the email of the courier.
     * @param password the password of the courier.
     * @param name the name of the courier.
     * @param nif the nif of the courier.
     * @param socialSecurity the social security number of the courier.
     * @param pharmacyId the pharmacy ID associated to the courier.
     * @param weight the weight of the courier.
     */
    public Courier(String email, String password, String name, int nif, long socialSecurity, int pharmacyId, double weight) {

        super(email, password, "Courier");
        this.name = name;
        this.nif = nif;
        this.socialSecurity = socialSecurity;
        this.pharmacyId = pharmacyId;
        this.weight = weight;
    }

    /**
     * Retrieves the name field of this courier object.
     * @return string containing the name.
     */
    public String getName() {

        return name;
    }

    /**
     * Retrieves the NIF field of this courier object.
     * @return int containing the NIF.
     */
    public int getNif() {

        return nif;
    }

    /**
     * Retrieves the Social Security field of this courier object.
     * @return long containing the Social Security number.
     */
    public long getSocialSecurity() {

        return socialSecurity;
    }

    /**
     * Retrieves the pharmacy ID field of this courier object.
     * @return int containing the pharmacy ID.
     */
    public int getPharmacyId() {

        return pharmacyId;
    }

    /**
     * Retrieves the weight value of this courier object.
     * @return double containing the weight.
     */
    public double getWeight() {

        return weight;
    }

    /**
     * Sets a new name for this courier object.
     * @param name new name.
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Sets a new NIF for this courier object.
     * @param nif new NIF.
     */
    public void setNif(int nif) {

        this.nif = nif;
    }

    /**
     * Sets a new Social Security Number for this courier object.
     * @param socialSecurity new social security number.
     */
    public void setSocialSecurity(long socialSecurity) {

        this.socialSecurity = socialSecurity;
    }

    /**
     * Sets a new pharmacy ID for this courier object.
     * @param pharmacyId new pharmacy ID.
     */
    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    /**
     * Sets a new weight for this courier object.
     * @param weight new weight.
     */
    public void setWeight(double weight) {

        this.weight = weight;
    }
    
    /**
     * Courier String format.
     * @return string with this courier information.
     */
    @Override
    public String toString() {
        return "Courier{" + "name=" + name + ", nif=" + nif + ", socialSecurity=" + socialSecurity + ", pharmacyId=" + pharmacyId + ", weight=" + weight + '}';
    }   
    
    /**
     * Returns the hash code for this Courier object.
     * @return hash code for the object.
     */
    @Override
    public int hashCode() {

        int hash = 5;
        hash = 37 * hash + this.nif;
        return hash;
    }
    
    /**
     * Compares a Courier object to another object and checks if they are
     * equal.
     * @param obj the object to compare to.
     * @return true if equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Courier) {
            return super.equals(obj) && nif == ((Courier) obj).nif;
        }
        return false;
    }
}
