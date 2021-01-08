package lapr.project.model;

import java.util.Objects;

public class Address {

    private String description;
    private double longitude;
    private double latitude;
    private double altitude;

    public Address(String description, double longitude, double latitude, double altitude) {

        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public Address() {

    }

    public String getDescription() {

        return description;
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

    public void setDescription(String description) {

        this.description = description;
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

        return "Address{" + "description=" + description + ", longitude=" + longitude + ", latitude=" + latitude + ", altitude=" + altitude + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.description);
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

        return this.description.equals(other.description);
    }
}
