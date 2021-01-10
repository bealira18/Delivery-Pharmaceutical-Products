package lapr.project.model;

public class Administrator extends RegisteredUser {

    private int pharmacyId;
    private String name;
    private int nif;
    private long socialSecurity;

    public Administrator(String email, String password, int pharmacyId, String name, int nif, long socialSecurity) {

        super(email, password, "Administrator");
        this.pharmacyId = pharmacyId;
        this.name = name;
        this.nif = nif;
        this.socialSecurity = socialSecurity;
    }

    public int getPharmacyId() {

        return pharmacyId;
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

    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
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
