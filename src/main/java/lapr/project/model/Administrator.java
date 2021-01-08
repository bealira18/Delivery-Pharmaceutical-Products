package lapr.project.model;

import java.util.Objects;

public class Administrator extends RegisteredUser{

    private int pharmacyId;
    private String name;
    private int nif;
    private int socialSecurity;

    public Administrator(String email, String password, String role, int pharmacyId, String name, int nif, int socialSecurity) {

        super(email, password, role);
        this.pharmacyId = pharmacyId;
        this.name = name;
        this.nif = nif;
        this.socialSecurity = socialSecurity;
    }

    public Administrator(RegisteredUser user, int pharmacyId, String name, int nif, int socialSecurity) {

        super(user);
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

    public int getSocialSecurity() {

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

    public void setSocialSecurity(int socialSecurity) {

        this.socialSecurity = socialSecurity;
    }

    @Override
    public String toString() {

        return "Administrator{" + "email=" + getEmail() + ", pharmacyId=" + pharmacyId + ", name=" + name + ", nif=" + nif + ", social_security=" + socialSecurity + '}';
    }

}
