package lapr.project.model;

public class Pharmacy {

    private int id;
    private String name;
    private String address;

    public Pharmacy(int id, String name, String address) {

        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Pharmacy() {

    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public String getAddress() {

        return address;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    @Override
    public String toString() {

        return "Pharmacy{" + "id=" + id + ", name=" + name + ", address=" + address + '}';
    }

    @Override
    public int hashCode() {

        int hash = 5;
        hash = 83 * hash + this.id;
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

        return this.id == other.id;
    }
}
