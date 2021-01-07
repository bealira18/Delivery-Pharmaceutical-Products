package lapr.project.model;

public class DeliveryStatus {

    private int id;
    private String name;

    public DeliveryStatus(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public DeliveryStatus() {

    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return "DeliveryStatus{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
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
        final DeliveryStatus other = (DeliveryStatus) obj;

        return this.id == other.id;
    }
}
