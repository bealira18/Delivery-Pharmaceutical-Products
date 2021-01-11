package lapr.project.model;

public class Pharmacy {

    private int pharmacyId;
    private String name;
    private String address;

    public Pharmacy(int id, String name, String address) {

        this.pharmacyId = id;
        this.name = name;
        this.address = address;
    }

    public Pharmacy() {

    }

    public int getId() {

        return pharmacyId;
    }

    public String getName() {

        return name;
    }

    public String getAddress() {

        return address;
    }

    public void setId(int id) {

        this.pharmacyId = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    @Override
    public String toString() {

        return "Pharmacy{" + "id=" + pharmacyId + ", name=" + name + ", address=" + address + '}';
    }

    @Override
    public int hashCode() {

        int hash = 5;
        hash = 83 * hash + this.pharmacyId;
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
        final Pharmacy other = (Pharmacy) obj;

        return this.pharmacyId == other.pharmacyId;
    }
}
