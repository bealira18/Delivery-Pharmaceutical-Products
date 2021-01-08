package lapr.project.model;

import java.sql.Blob;
import java.util.Objects;

public class Courier {

    private static int maximumPayload = 0;

    private String email;
    private String name;
    private int nif;
    private long socialSecurity;
    private int pharmacyId;

    public Courier(String email, String name, int nif, long socialSecurity, int pharmacyId) {

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

    public long getSocialSecurity() {

        return socialSecurity;
    }

    public int getPharmacyId() {

        return pharmacyId;
    }

    public static int getMaximumPayload() {

        return maximumPayload;
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
