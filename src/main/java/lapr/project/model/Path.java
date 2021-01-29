package lapr.project.model;

import java.util.Objects;

public class Path {

    private Address address1;
    private Address address2;
    private double kineticCoeficient;
    private double windAngle;
    private double windSpeed;

    /**
     * Initializes the Path object
     * @param address1 first Address object
     * @param address2 second Address object
     * @param kineticCoeficient kinetic coefficient of the road
     * @param windAngle wind angle
     * @param windSpeed wind speed
     */
    public Path(Address address1, Address address2, double kineticCoeficient, double windAngle, double windSpeed) {

        this.address1 = address1;
        this.address2 = address2;
        this.kineticCoeficient = kineticCoeficient;
        this.windAngle = windAngle;
        this.windSpeed = windSpeed;
    }

    /**
     * Gets the first Address
     * @return first Address obejct
     */
    public Address getAddress1() {

        return address1;
    }

    /**
     * Gets the second Address
     * @return second Address object
     */
    public Address getAddress2() {

        return address2;
    }

    /**
     * Gets the road kinetic coefficient
     * @return kinetic coefficient
     */
    public double getKineticCoeficient() {

        return kineticCoeficient;
    }

    /**
     * Gets the wind angle
     * @return wind angle
     */
    public double getWindAngle() {
        return windAngle;
    }

    /**
     * Gets the wind speed
     * @return wind speed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Sets the first Address
     * @param address1 Address object
     */
    public void setAddress1(Address address1) {

        this.address1 = address1;
    }

    /**
     * Sets the second Address
     * @param address2 Address object
     */
    public void setAddress2(Address address2) {

        this.address2 = address2;
    }

    /**
     * Sets the road kinetic coefficient
     * @param kineticCoeficient kinetic coefficient
     */
    public void setKineticCoeficient(double kineticCoeficient) {

        this.kineticCoeficient = kineticCoeficient;
    }

    /**
     * Sets the wind angle
     * @param windAngle wind angle
     */
    public void setWindAngle(double windAngle) {
        this.windAngle = windAngle;
    }

    /**
     * Sets the wind speed
     * @param windSpeed wind speed
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Path's String format
     * @return Path's String
     */
    @Override
    public String toString() {
        return "Path{" +
                "address1=" + address1 +
                ", address2=" + address2 +
                ", kineticCoeficient=" + kineticCoeficient +
                ", windAngle=" + windAngle +
                ", windSpeed=" + windSpeed +
                '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.address1);
        hash = 89 * hash + Objects.hashCode(this.address2);
        return hash;
    }

    /**
     * Compares the equality of the current object with the object of same type
     * @param obj object to compare with
     * @return true if equals, false if not
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Path other = (Path) obj;

        return address1.equals(other.address1) && address2.equals(other.address2);
    }
}
