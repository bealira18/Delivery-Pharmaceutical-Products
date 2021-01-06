package lapr.project.model;

public class Scooter {

    private int id;
    private int pharmacy_id;
    private double max_battery;
    private double current_battery;
    private String status;

    public Scooter(int id, int pharmacy_id, double max_battery, double current_battery, String status) {

        this.id = id;
        this.pharmacy_id = pharmacy_id;
        this.max_battery = max_battery;
        this.current_battery = current_battery;
        this.status = status;
    }

    public Scooter() {

    }

    public int getId() {

        return id;
    }

    public int getPharmacy_id() {

        return pharmacy_id;
    }

    public double getMax_battery() {

        return max_battery;
    }

    public double getCurrent_battery() {

        return current_battery;
    }

    public String getStatus() {

        return status;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setPharmacy_id(int pharmacy_id) {

        this.pharmacy_id = pharmacy_id;
    }

    public void setMax_battery(double max_battery) {

        this.max_battery = max_battery;
    }

    public void setCurrent_battery(double current_battery) {

        this.current_battery = current_battery;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    @Override
    public String toString() {

        return "Scooter{" + "id=" + id + ", pharmacy_id=" + pharmacy_id + ", max_battery=" + max_battery + ", current_battery=" + current_battery + ", status=" + status + '}';
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
        final Scooter other = (Scooter) obj;

        return this.id == other.id;
    }
}
