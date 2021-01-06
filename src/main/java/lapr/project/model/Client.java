package lapr.project.model;

import java.util.Objects;

public class Client {

    private String email;
    private int nif;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private int credits;

    public Client(){
        this.email=null;
        this.nif=0;
        this.name=null;
        this.address=null;
        this.latitude=0;
        this.longitude=0;
        this.credits=0;
    }

    public Client(String email, int nif, String name, String address, double latitude, double longitude, int credits) {
        this.email = email;
        this.nif = nif;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.credits = credits;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Client client = (Client) obj;
        return nif == client.nif;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, nif, name, address, latitude, longitude, credits);
    }

    @Override
    public String toString() {
        return "email='" + email +
                ", nif=" + nif +
                ", name='" + name +
                ", address='" + address +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", credits=" + credits;
    }
}
