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
    public String toString() {

        return "Path{" + "address1=" + address1 + ", address2=" + address2 + ", kineticCoeficient=" + kineticCoeficient + '}';
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Path other = (Path) obj;

        return address1.equals(other.address1) && address2.equals(other.address2);
    }
}
