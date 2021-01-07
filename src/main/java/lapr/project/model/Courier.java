package lapr.project.model;

import java.util.Objects;

public class Courier {

    private String email;
    private String name;
    private int nif;
    private int social_security;
    private int pharmacy_id;

    public Courier(String email, String name, int nif, int social_security, int pharmacy_id) {

        this.email = email;
        this.name = name;
        this.nif = nif;
        this.social_security = social_security;
        this.pharmacy_id = pharmacy_id;
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

    public int getSocial_security() {

        return social_security;
    }

    public int getPharmacy_id() {

        return pharmacy_id;
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

    public void setSocial_security(int social_security) {

        this.social_security = social_security;
    }

    public void setPharmacy_id(int pharmacy_id) {

        this.pharmacy_id = pharmacy_id;
    }

    @Override
    public String toString() {

        return "Courier{" + "email=" + email + ", name=" + name + ", nif=" + nif + ", social_security=" + social_security + ", pharmacy_id=" + pharmacy_id + '}';
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
