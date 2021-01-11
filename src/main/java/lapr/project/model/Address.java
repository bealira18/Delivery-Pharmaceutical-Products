package lapr.project.model;

import java.util.Objects;

public class Address {

    private String description;
    private double latitude;
    private double longitude;
    private double altitude;

    public Address(String description, double latitude, double longitude, double altitude) {

        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public String getDescription() {

        return description;
    }

    public double getLatitude() {

        return latitude;
    }

    public double getLongitude() {

        return longitude;
    }

    public double getAltitude() {

        return altitude;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {

        this.longitude = longitude;
    }

    public void setAltitude(double altitude) {

        this.altitude = altitude;
    }

    @Override
    public String toString() {

        return "Address{" + "description=" + description + ", latitude=" + latitude + ", longitude=" + longitude + ", altitude=" + altitude + '}';
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
