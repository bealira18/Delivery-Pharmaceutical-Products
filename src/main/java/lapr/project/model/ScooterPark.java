package lapr.project.model;

import java.util.Objects;

public class ScooterPark {

    private int parkId;
    private int pharmacyId;
    private int limit;
    private int nrChargingStations;
    private double latitude;
    private double longitude;

    public ScooterPark(){
        this.parkId=0;
        this.pharmacyId=0;
        this.limit=0;
        this.nrChargingStations=0;
        this.latitude=0;
        this.longitude=0;
    }

    public ScooterPark(int parkId, int pharmacyId, int limit, int nrChargingStations, double latitude, double longitude) {
        this.parkId = parkId;
        this.pharmacyId = pharmacyId;
        this.limit = limit;
        this.nrChargingStations = nrChargingStations;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getNrChargingStations() {
        return nrChargingStations;
    }

    public void setNrChargingStations(int nrChargingStations) {
        this.nrChargingStations = nrChargingStations;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ScooterPark sp = (ScooterPark) obj;
        return parkId == sp.parkId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkId,pharmacyId,limit,nrChargingStations,latitude,longitude);
    }

    @Override
    public String toString() {
        return "parkId=" + parkId +
                ", pharmacyId=" + pharmacyId +
                ", limit=" + limit +
                ", nrChargingStations=" + nrChargingStations +
                ", latitude=" + latitude +
                ", longitude=" + longitude;
    }
}
