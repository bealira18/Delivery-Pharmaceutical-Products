package lapr.project.model;

/**
 * This class serves as a container for the Administrator role and it's specific
 * information.
 * @author lapr3-2020-g052
 */
public class Administrator extends RegisteredUser {

    /**
     * The id of the pharmacy that this administrator belongs to.
     */
    private int pharmacyId;

    /**
     * The name of the administrator.
     */
    private String name;

    /**
     * The NIF number of the administrator.
     */
    private int nif;

    /**
     * The Social Security number of the administrator.
     */
    private long socialSecurity;

    /**
     * Creates a instance of the Administrator Object with the given arguments.
     * 
     * @param email the email of the administrator.
     * @param password the password of the administrator.
     * @param pharmacyId the id of the pharmacy that this administrator belongs to.
     * @param name the name of the administrator.
     * @param nif the NIF number of the administrator.
     * @param socialSecurity the Social Security number of the administrator.
     */
    public Administrator(String email, String password, int pharmacyId, String name, int nif, long socialSecurity) {

        super(email, password, "Administrator");
        this.pharmacyId = pharmacyId;
        this.name = name;
        this.nif = nif;
        this.socialSecurity = socialSecurity;
    }

    /**
     * Retrieves the pharmacy ID field of this Administrator object.
     * @return int containing the pharmacy ID.
     */
    public int getPharmacyId() {

        return pharmacyId;
    }

    /**
     * Retrieves the name field of this Administrator object.
     * @return string containing the name.
     */
    public String getName() {

        return name;
    }

    /**
     * Retrieves the NIF field of this Administrator object.
     * @return int containing the NIF.
     */
    public int getNif() {

        return nif;
    }

    /**
     * Retrieves the Social Security field of this Administrator object.
     * @return long containing the Social Security number.
     */
    public long getSocialSecurity() {

        return socialSecurity;
    }

    /**
     * Sets a new pharmacy ID for this Administrator object.
     * @param pharmacyId new pharmacy ID.
     */
    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    /**
     * Sets a new name for this Administrator object.
     * @param name new name.
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Sets a new NIF for this Administrator object.
     * @param nif new NIF.
     */
    public void setNif(int nif) {

        this.nif = nif;
    }

    /**
     * Sets a new Social Security Number for this Administrator object.
     * @param socialSecurity new social security number.
     */
    public void setSocialSecurity(long socialSecurity) {

        this.socialSecurity = socialSecurity;
    }
    
    /**
     * Administrator String format.
     * @return string with this Administrator information.
     */
    @Override
    public String toString() {
        return "Administrator{" + "pharmacyId=" + pharmacyId + ", name=" + name + ", nif=" + nif + ", socialSecurity=" + socialSecurity + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 53 * hash + this.nif;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Administrator) {
            return super.equals(obj) && nif == ((Administrator) obj).nif;
        }
        return false;
    }
}
