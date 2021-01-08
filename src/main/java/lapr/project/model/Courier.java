package lapr.project.model;

import java.util.Objects;

public class Courier {

    private String email;
    private String name;
    private int nif;
    private int socialSecurity;
    private int pharmacyId;

    public Courier(String email, String name, int nif, int socialSecurity, int pharmacyId) {

        this.email = email;
        this.name = name;
        this.nif = nif;
        this.socialSecurity = socialSecurity;
        this.pharmacyId = pharmacyId;
    }

    public Courier() {

    }

    public String getEmail() {

        return email;
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

    public void setEmail(String email) {

        this.email = email;
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

    public void setPharmacy_id(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    @Override
    public String toString() {

        return "Courier{" + "email=" + email + ", name=" + name + ", nif=" + nif + ", socialSecurity=" + socialSecurity + ", pharmacyId=" + pharmacyId + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.email);
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
        final Courier other = (Courier) obj;

        return this.email.equals(other.email);
    }
}
