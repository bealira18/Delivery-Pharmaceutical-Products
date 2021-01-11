package lapr.project.model;

public class ScooterStatus {

    private int scooterStatusId;
    private String scooterStatusName;

    public ScooterStatus(int id, String name) {

        this.scooterStatusId = id;
        this.scooterStatusName = name;
    }

    public int getScooterStatusId() {

        return scooterStatusId;
    }

    public String getScooterStatusName() {

        return scooterStatusName;
    }

    public void setScooterStatusId(int scooterStatusId) {

        this.scooterStatusId = scooterStatusId;
    }

    public void setScooterStatusName(String scooterStatusName) {

        this.scooterStatusName = scooterStatusName;
    }

    @Override
    public String toString() {

        return "ScooterStatus{" + "scooterStatusId=" + scooterStatusId + ", scooterStatusName=" + scooterStatusName + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 79 * hash + this.scooterStatusId;
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

        return this.scooterStatusId == other.scooterStatusId;
    }
}
