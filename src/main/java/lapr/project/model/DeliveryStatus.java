package lapr.project.model;

public class DeliveryStatus {

    private int deliveryStatusId;
    private String deliveryStatusName;

    public DeliveryStatus(int id, String name) {

        this.deliveryStatusId = id;
        this.deliveryStatusName = name;
    }

    public int getDeliveryStatusId() {

        return deliveryStatusId;
    }

    public String getDeliveryStatusName() {

        return deliveryStatusName;
    }

    public void setDeliveryStatusId(int deliveryStatusId) {

        this.deliveryStatusId = deliveryStatusId;
    }

    public void setDeliveryStatusName(String deliveryStatusName) {

        this.deliveryStatusName = deliveryStatusName;
    }

    @Override
    public String toString() {

        return "DeliveryStatus{" + "deliveryStatusId=" + deliveryStatusId + ", deliveryStatusName=" + deliveryStatusName + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 83 * hash + this.deliveryStatusId;
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

        return this.deliveryStatusId == other.deliveryStatusId;
    }
}
