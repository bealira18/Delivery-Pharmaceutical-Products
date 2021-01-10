package lapr.project.model;

public class Courier extends RegisteredUser {

    private static int maximumPayload = 0;

    private String name;
    private int nif;
    private long socialSecurity;
    private int pharmacyId;
    private double weight;

    public Courier(String email, String password, String name, int nif, long socialSecurity, int pharmacyId, double weight) {

        super(email, password, "Courier");
        this.name = name;
        this.nif = nif;
        this.socialSecurity = socialSecurity;
        this.pharmacyId = pharmacyId;
        this.weight = weight;
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

    public double getWeight() {

        return weight;
    }

    public static int getMaximumPayload() {

        return maximumPayload;
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

    public void setWeight(double weight) {

        this.weight = weight;
    }

    public static void setMaximumPayload(int maximumPayload) {

        if (maximumPayload < 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative Maximum Payload)");
        }
        Courier.maximumPayload = maximumPayload;
    }

    @Override
    public String toString() {
        return "Courier{" + "name=" + name + ", nif=" + nif + ", socialSecurity=" + socialSecurity + ", pharmacyId=" + pharmacyId + ", weight=" + weight + '}';
    }   

    @Override
    public int hashCode() {

        int hash = 5;
        hash = 37 * hash + this.nif;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Courier) {
            return super.equals(obj) && nif == ((Courier) obj).nif;
        }
        return false;
    }
}
