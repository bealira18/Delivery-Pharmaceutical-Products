package lapr.project.model;

import java.util.Objects;

public class Path {

    private Address address1;
    private Address address2;
    private double kineticCoeficient;
    private double windAngle;
    private double windSpeed;

    public Path(Address address1, Address address2, double kineticCoeficient, double windAngle, double windSpeed) {

        this.address1 = address1;
        this.address2 = address2;
        this.kineticCoeficient = kineticCoeficient;
        this.windAngle = windAngle;
        this.windSpeed = windSpeed;
    }

    public Address getAddress1() {

        return address1;
    }

    public Address getAddress2() {

        return address2;
    }

    public double getKineticCoeficient() {

        return kineticCoeficient;
    }

    public double getWindAngle() {
        return windAngle;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setAddress1(Address address1) {

        this.address1 = address1;
    }

    public void setAddress2(Address address2) {

        this.address2 = address2;
    }

    public void setKineticCoeficient(double kineticCoeficient) {

        this.kineticCoeficient = kineticCoeficient;
    }

    public void setWindAngle(double windAngle) {
        this.windAngle = windAngle;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

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

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.address1);
        hash = 89 * hash + Objects.hashCode(this.address2);
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
        Path other = (Path) obj;

        return address1.equals(other.address1) && address2.equals(other.address2);
    }
}
