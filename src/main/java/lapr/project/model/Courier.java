package lapr.project.model;

import java.util.Objects;

public class Courier {

    private String email;
    private int pharmacy_id;
    private int scooter_id;
    private int nif;
    private String name;
    private int social_security;

    public Courier(String email, int pharmacy_id, int scooter_id, int nif, String name, int social_security) {

        this.email = email;
        this.pharmacy_id = pharmacy_id;
        this.scooter_id = scooter_id;
        this.nif = nif;
        this.name = name;
        this.social_security = social_security;
    }

    public Courier() {

    }

    public String getEmail() {

        return email;
    }

    public int getPharmacy_id() {

        return pharmacy_id;
    }

    public int getScooter_id() {

        return scooter_id;
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

    public void setScooter_id(int scooter_id) {

        this.scooter_id = scooter_id;
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

        return "Courrier{" + "email=" + email + ", pharmacy_id=" + pharmacy_id + ", scooter_id=" + scooter_id + ", nif=" + nif + ", name=" + name + ", social_security=" + social_security + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.email);
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
