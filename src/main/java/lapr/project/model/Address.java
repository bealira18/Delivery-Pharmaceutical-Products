package lapr.project.model;

import java.util.Objects;

/**
 * This class serves as a container for one's address information.
 * @author lapr3-2020-g052
 */
public class Address {

    /**
     * The description of the address, usually the street name, number and floor if applicable.
     */
    private String description;

    /**
     * Latitude of the location of the address.
     */
    private double latitude;

    /**
     * Longitude of the location of the address.
     */
    private double longitude;

    /**
     * Altitude of the location of the address.
     */
    private double altitude;

    /**
     * Creates a instance of the Address Object with the given arguments.
     * 
     * @param description the description of the address, usually the street name, number and floor if applicable.
     * @param latitude latitude of the location of the address.
     * @param longitude longitude of the location of the address.
     * @param altitude altitude of the location of the address.
     */
    public Address(String description, double latitude, double longitude, double altitude) {

        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    /**
     * Retrieves the description field of this Address object.
     * @return string containing the description.
     */
    public String getDescription() {

        return description;
    }

    /**
     * Retrieves the latitude field of this Address object.
     * @return double containing the latitude.
     */
    public double getLatitude() {

        return latitude;
    }

    /**
     * Retrieves the longitude field of this Address object.
     * @return double containing the longitude.
     */
    public double getLongitude() {

        return longitude;
    }

    /**
     * Retrieves the altitude field of this Address object.
     * @return double containing the altitude.
     */
    public double getAltitude() {

        return altitude;
    }

    /**
     * Sets a new description for this Address object.
     * @param description new description string.
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * Sets a new latitude for this Address object.
     * @param latitude new latitude value.
     */
    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    /**
     * Sets a new longitude for this Address object.
     * @param longitude new longitude value.
     */
    public void setLongitude(double longitude) {

        this.longitude = longitude;
    }

    /**
     * Sets a new altitude for this Address object.
     * @param altitude new altitude value.
     */
    public void setAltitude(double altitude) {

        this.altitude = altitude;
    }
    
    /**
     * Address String format.
     * @return string with this Addresses information.
     */
    @Override
    public String toString() {

        return "Address{" + "description=" + description + ", latitude=" + latitude + ", longitude=" + longitude + ", altitude=" + altitude + '}';
    }

    /**
     * Returns the hash code for this Address object.
     * @return hash code for the object.
     */
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.description);
        return hash;
    }
    
    /**
     * Compares a Address object to another object and checks if they are equal.
     * @param obj the object to compare to.
     * @return true if equal, false if not.
     */
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
