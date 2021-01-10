package lapr.project.model;

public class ParkingSpace {

    private int parkingSpaceId;
    private int parkId;
    private int scooterId;
    private boolean isChargingStation;

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

        int hash = 3;
        hash = 43 * hash + this.parkingSpaceId;
        hash = 43 * hash + this.parkId;
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
        ParkingSpace other = (ParkingSpace) obj;

        return parkingSpaceId == other.parkingSpaceId
                && parkId == other.parkId;
    }
}
