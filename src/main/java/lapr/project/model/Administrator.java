package lapr.project.model;

import java.util.Objects;

public class Administrator {

    private String email;
    private int pharmacy_id;
    private int nif;
    private String name;
    private int social_security;

    public Administrator(String email, int pharmacy_id, int nif, String name, int social_security) {

        this.email = email;
        this.pharmacy_id = pharmacy_id;
        this.nif = nif;
        this.name = name;
        this.social_security = social_security;
    }

    public Administrator() {

    }

    public String getEmail() {

        return email;
    }

    public int getPharmacy_id() {

        return pharmacy_id;
    }

    public int getNif() {

        return nif;
    }

    public String getName() {

        return name;
    }

    public int getSocial_security() {

        return social_security;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setPharmacy_id(int pharmacy_id) {

        this.pharmacy_id = pharmacy_id;
    }

    public void setNif(int nif) {

        this.nif = nif;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setSocial_security(int social_security) {

        this.social_security = social_security;
    }

    @Override
    public String toString() {

        return "Administrator{" + "email=" + email + ", pharmacy_id=" + pharmacy_id + ", nif=" + nif + ", name=" + name + ", social_security=" + social_security + '}';
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
