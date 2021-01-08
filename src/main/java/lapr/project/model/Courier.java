package lapr.project.model;

public class Courier extends RegisteredUser{

    private String name;
    private int nif;
    private int socialSecurity;
    private int pharmacyId;

    public Courier(RegisteredUser user, String password, String role, String name, int nif, int socialSecurity, int pharmacyId) {

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

    public int getSocialSecurity() {

        return socialSecurity;
    }

    public int getPharmacyId() {

        return pharmacyId;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setNif(int nif) {

        this.nif = nif;
    }

    public void setSocialSecurity(int socialSecurity) {

        this.socialSecurity = socialSecurity;
    }

    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    @Override
    public String toString() {

        return "Courier{" + "email=" + getEmail() + ", name=" + name + ", nif=" + nif + ", socialSecurity=" + socialSecurity + ", pharmacyId=" + pharmacyId + '}';
    }
}
