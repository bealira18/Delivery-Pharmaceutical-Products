package lapr.project.model;

public class Pharmacy {

    private int id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;

    public Pharmacy(int id, String name, String address, double latitude, double longitude) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {

        return latitude;
    }

    public double getLongitude() {

        return longitude;
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

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {

        this.longitude = longitude;
    }

    @Override
    public String toString() {

        return "Pharmacy{" + "id=" + id + ", name=" + name + ", address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 11 * hash + this.id;
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
