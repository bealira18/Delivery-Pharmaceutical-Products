package lapr.project.model;

public class Courier extends RegisteredUser{

    private static int maximumPayload = 0;

    private String name;
    private int nif;
    private long socialSecurity;
    private int pharmacyId;
    private double weight;

    public Courier(String email, String password, String name, int nif, long socialSecurity, int pharmacyId, double weight) {

        super(email, password, "courier");
        this.name = name;
        this.nif = nif;
        this.socialSecurity = socialSecurity;
        this.pharmacyId = pharmacyId;
    }
    
    public Courier(RegisteredUser user, String name, int nif, long socialSecurity, int pharmacyId, double weight) {

        super(user);
        this.name = name;
        this.nif = nif;
        this.socialSecurity = socialSecurity;
        this.pharmacyId = pharmacyId;
    }

    public String getName() {

        return name;
    }

    public int getNif() {

        return nif;
    }

    public long getSocialSecurity() {

        return socialSecurity;
    }

    public int getPharmacyId() {

        return pharmacyId;
    }

    public static int getMaximumPayload() {

        return maximumPayload;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setNif(int nif) {

        this.nif = nif;
    }

    public void setSocialSecurity(long socialSecurity) {

        this.socialSecurity = socialSecurity;
    }

    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    public static void setMaximumPayload(int maximumPayload) {

        if (maximumPayload < 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative Maximum Payload)");
        }
        Courier.maximumPayload = maximumPayload;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {

        return "Courier{" + "email=" + getEmail() + ", name=" + name + ", nif=" + nif + ", socialSecurity=" + socialSecurity + ", pharmacyId=" + pharmacyId + '}';
    }
}
