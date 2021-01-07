package lapr.project.model;

public class ScooterPark {

    private int id;
    private int pharmacy_id;
    private int limit;
    private int num_charging_stations;
    private String address;

    public ScooterPark(int id, int pharmacy_id, int limit, int num_charging_stations, String address) {

        this.id = id;
        this.pharmacy_id = pharmacy_id;
        this.limit = limit;
        this.num_charging_stations = num_charging_stations;
        this.address = address;
    }

    public ScooterPark() {

    }

    public int getId() {

        return id;
    }

    public int getPharmacy_id() {

        return pharmacy_id;
    }

    public int getLimit() {

        return limit;
    }

    public int getNum_charging_stations() {

        return num_charging_stations;
    }

    public String getAddress() {

        return address;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setPharmacy_id(int pharmacy_id) {

        this.pharmacy_id = pharmacy_id;
    }

    public void setLimit(int limit) {

        this.limit = limit;
    }

    public void setNum_charging_stations(int num_charging_stations) {

        this.num_charging_stations = num_charging_stations;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    @Override
    public String toString() {

        return "ScooterPark{" + "id=" + id + ", pharmacy_id=" + pharmacy_id + ", limit=" + limit + ", num_charging_stations=" + num_charging_stations + ", address=" + address + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 61 * hash + this.id;
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
        final ScooterPark other = (ScooterPark) obj;

        return this.id == other.id;
    }
}
