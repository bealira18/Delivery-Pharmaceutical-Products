package lapr.project.model;

/**
 * This class serves as a container for a delivery status information.
 * @author lapr3-2020-g052
 */
public class DeliveryStatus {

    private int deliveryStatusId;
    private String deliveryStatusName;

    /**
     * Initializes the delivery status object
     * @param id status id
     * @param name status description
     */
    public DeliveryStatus(int id, String name) {

        this.deliveryStatusId = id;
        this.deliveryStatusName = name;
    }

    /**
     * Gets the delivery status id
     * @return status id
     */
    public int getDeliveryStatusId() {

        return deliveryStatusId;
    }

    /**
     * Gets delivery status description
     * @return status description
     */
    public String getDeliveryStatusName() {

        return deliveryStatusName;
    }

    /**
     * Sets delivery status id
     * @param deliveryStatusId status id
     */
    public void setDeliveryStatusId(int deliveryStatusId) {

        this.deliveryStatusId = deliveryStatusId;
    }

    /**
     * Sets delivery status description
     * @param deliveryStatusName status description
     */
    public void setDeliveryStatusName(String deliveryStatusName) {

        this.deliveryStatusName = deliveryStatusName;
    }

    /**
     * DeliveryStatus' String format
     * @return DeliveryStatus' String
     */
    @Override
    public String toString() {

        return "DeliveryStatus{" + "deliveryStatusId=" + deliveryStatusId + ", deliveryStatusName=" + deliveryStatusName + '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 83 * hash + this.deliveryStatusId;
        return hash;
    }

    /**
     * Compares the equality of the current object with the object of same type
     * @param obj object to compare with
     * @return true if equals, false if not
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeliveryStatus other = (DeliveryStatus) obj;

        return this.deliveryStatusId == other.deliveryStatusId;
    }
}
