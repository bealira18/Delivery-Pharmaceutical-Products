package lapr.project.model;

import java.util.Objects;

public class Address {

    private String address;
    private double longitude;
    private double latitude;
    private double altitude;

    public Address(String address, double longitude, double latitude, double altitude) {

        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public Address() {

    }

    public String getAddress() {

        return address;
    }

    public double getLongitude() {

        return longitude;
    }

    public double getLatitude() {

        return latitude;
    }

    public double getAltitude() {

        return altitude;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public void setLongitude(double longitude) {

        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    public void setAltitude(double altitude) {

        this.altitude = altitude;
    }

    @Override
    public String toString() {

        return "Address{" + "address=" + address + ", longitude=" + longitude + ", latitude=" + latitude + ", altitude=" + altitude + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.address);
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
        final Address other = (Address) obj;

        return this.address.equals(other.address);
    }
}
