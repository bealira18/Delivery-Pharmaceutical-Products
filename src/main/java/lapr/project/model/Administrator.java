package lapr.project.model;

import java.util.Objects;

public class Administrator {

    private String email;
    private int pharmacyId;
    private String name;
    private int nif;
    private int socialSecurity;

    public Administrator(String email, int pharmacyId, String name, int nif, int socialSecurity) {

        this.email = email;
        this.pharmacyId = pharmacyId;
        this.name = name;
        this.nif = nif;
        this.socialSecurity = socialSecurity;
    }

    public Administrator() {

    }

    public String getEmail() {

        return email;
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

    public void setEmail(String email) {

        this.email = email;
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

        return "Administrator{" + "email=" + email + ", pharmacyId=" + pharmacyId + ", name=" + name + ", nif=" + nif + ", social_security=" + socialSecurity + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.email);
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
        final Administrator other = (Administrator) obj;

        return this.email.equals(other.email);
    }
}
