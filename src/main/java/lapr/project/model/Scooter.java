package lapr.project.model;

public class Scooter {

    private int id;
    private int pharmacy_id;
    private int scooter_status_id;
    private double current_battery;
    private double max_battery;

    public Scooter(int id, int pharmacy_id, int scooter_status_id, double current_battery, double max_battery) {

        this.id = id;
        this.pharmacy_id = pharmacy_id;
        this.scooter_status_id = scooter_status_id;
        this.current_battery = current_battery;
        this.max_battery = max_battery;
    }

    public Scooter() {

    }

    public int getId() {

        return id;
    }

    public int getPharmacy_id() {

        return pharmacy_id;
    }

    public int getScooter_status_id() {

        return scooter_status_id;
    }

    public double getCurrent_battery() {

        return current_battery;
    }

    public double getMax_battery() {

        return max_battery;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setPharmacy_id(int pharmacy_id) {

        this.pharmacy_id = pharmacy_id;
    }

    public void setScooter_status_id(int scooter_status_id) {

        this.scooter_status_id = scooter_status_id;
    }

    public void setCurrent_battery(double current_battery) {

        this.current_battery = current_battery;
    }

    public void setMax_battery(double max_battery) {

        this.max_battery = max_battery;
    }

    @Override
    public String toString() {

        return "Scooter{" + "id=" + id + ", pharmacy_id=" + pharmacy_id + ", scooter_status_id=" + scooter_status_id + ", current_battery=" + current_battery + ", max_battery=" + max_battery + '}';
    }

    @Override
    public int hashCode() {

        int hash = 5;
        hash = 67 * hash + this.id;
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
