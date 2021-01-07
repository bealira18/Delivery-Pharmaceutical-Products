package lapr.project.model;

import java.util.Objects;

public class ParkingSpace {

    private int parkingSpaceId;
    private int parkId;
    private int scooterId;
    private boolean isChargingStation;

    public ParkingSpace() {

        this.parkingSpaceId = 0;
        this.parkId = 0;
        this.scooterId = 0;
        this.isChargingStation = false;
    }

    public ParkingSpace(int parkingSpaceId, int parkId, int scooterId, boolean isChargingStation) {

        this.parkingSpaceId = parkingSpaceId;
        this.parkId = parkId;
        this.scooterId = scooterId;
        this.isChargingStation = isChargingStation;
    }

    public int getParkingSpaceId() {

        return parkingSpaceId;
    }

    public void setParkingSpaceId(int parkingSpaceId) {

        this.parkingSpaceId = parkingSpaceId;
    }

    public int getParkId() {

        return parkId;
    }

    public void setParkId(int parkId) {

        this.parkId = parkId;
    }

    public int getScooterId() {

        return scooterId;
    }

    public void setScooterId(int scooterId) {

        this.scooterId = scooterId;
    }

    public boolean isChargingStation() {

        return isChargingStation;
    }

    public void setChargingStation(boolean chargingStation) {

        isChargingStation = chargingStation;
    }

    @Override
    public String toString() {

        return "parkingSpaceId=" + parkingSpaceId
                + ", parkId=" + parkId
                + ", scooterId=" + scooterId
                + ", isChargingStation=" + isChargingStation;
    }

    @Override
    public int hashCode() {

        return Objects.hash(parkingSpaceId, parkId, scooterId, isChargingStation);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ParkingSpace ps = (ParkingSpace) obj;

        return parkingSpaceId == ps.parkingSpaceId;
    }
}
