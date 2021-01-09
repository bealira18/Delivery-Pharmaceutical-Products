package lapr.project.model;

import java.util.Objects;

public class Path {

    private Address address1;
    private Address address2;
    private double kineticCoeficient;

    public Path(Address address1, Address address2, double kineticCoeficient) {
        this.address1 = address1;
        this.address2 = address2;
        this.kineticCoeficient = kineticCoeficient;
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

    public void setAddress1(Address address1) {
        this.address1 = address1;
    }

    public void setAddress2(Address address2) {
        this.address2 = address2;
    }

    public void setKineticCoeficient(double kineticCoeficient) {
        this.kineticCoeficient = kineticCoeficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;
        Path path = (Path) o;
        return Double.compare(path.getKineticCoeficient(), getKineticCoeficient()) == 0 &&
                getAddress1().equals(path.getAddress1()) &&
                getAddress2().equals(path.getAddress2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress1(), getAddress2(), getKineticCoeficient());
    }

    @Override
    public String toString() {
        return "Path{" +
                "address1=" + address1 +
                ", address2=" + address2 +
                ", kineticCoeficient=" + kineticCoeficient +
                '}';
    }
}
