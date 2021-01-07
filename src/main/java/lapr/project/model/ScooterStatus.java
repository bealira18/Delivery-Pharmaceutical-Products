package lapr.project.model;

public class ScooterStatus {

    private int id;
    private String name;

    public ScooterStatus(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public ScooterStatus() {

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

        return "ScooterStatus{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 79 * hash + this.id;
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
        final ScooterStatus other = (ScooterStatus) obj;

        return this.id == other.id;
    }
}
