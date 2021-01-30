package lapr.project.model;

import java.time.LocalDate;

/**
 * This class serves as a container for a delivery information.
 * @author lapr3-2020-g052
 */
public class Delivery {

    private int orderId;
    private int vehicleId;
    private String courierEmail;
    private int deliveryStatusId;
    private LocalDate deliveryStart;
    private LocalDate deliveryEnd;
    private int deliveryRun;

    /**
     * Initializes the Delivery object
     * @param orderId purchase order id
     * @param vehicleId vehicle id
     * @param courierEmail courier email
     * @param deliveryStatusId delivery status id
     * @param deliveryStart time of delivery start
     * @param deliveryEnd time of delivery end
     * @param deliveryRun delivery run id
     */
    public Delivery(int orderId, int vehicleId, String courierEmail, int deliveryStatusId, LocalDate deliveryStart, LocalDate deliveryEnd, int deliveryRun) {

        this.orderId = orderId;
        this.vehicleId = vehicleId;
        this.courierEmail = courierEmail;
        this.deliveryStatusId = deliveryStatusId;
        setDeliveryStart(deliveryStart);
        setDeliveryEnd(deliveryEnd);
        this.deliveryRun = deliveryRun;
    }

    /**
     * Gets the purchase order id
     * @return order id
     */
    public int getOrderId() {

        return orderId;
    }

    /**
     * Gets the vehicle transporting the delivery id
     * @return vehicle id
     */
    public int getVehicleId() {

        return vehicleId;
    }

    /**
     * Gets the courier transporting the delivery email
     * @return courier email
     */
    public String getCourierEmail() {

        return courierEmail;
    }

    /**
     * Gets the delivery status id
     * @return status id
     */
    public int getDeliveryStatusId() {

        return deliveryStatusId;
    }

    /**
     * Gets the date when delivery started
     * @return LocalDate with delivery start
     */
    public LocalDate getDeliveryStart() {

        return deliveryStart;
    }

    /**
     * Gets the date when delivery finished
     * @return LocalDate with delivery end
     */
    public LocalDate getDeliveryEnd() {

        return deliveryEnd;
    }

    /**
     * Gets the delivery run id
     * @return delivery run id
     */
    public int getDeliveryRun() {
        return deliveryRun;
    }

    /**
     * Sets the purchase order id
     * @param orderId purchase order id
     */
    public void setOrderId(int orderId) {

        this.orderId = orderId;
    }

    /**
     * Sets the vehicle id
     * @param scooterId vehicle id
     */
    public void setVehicleId(int scooterId) {

        this.vehicleId = scooterId;
    }

    /**
     * Sets the delivery courier email
     * @param courierEmail courier email
     */
    public void setCourierEmail(String courierEmail) {

        this.courierEmail = courierEmail;
    }

    /**
     * Sets the status of the delivery
     * @param deliveryStatusId delivery status id
     */
    public void setDeliveryStatusId(int deliveryStatusId) {

        this.deliveryStatusId = deliveryStatusId;
    }

    /**
     * Sets the delivery start date
     * @param deliveryStart LocalDate with delivery start
     */
    public final void setDeliveryStart(LocalDate deliveryStart) {

        this.deliveryStart = deliveryStart;
    }

    /**
     * Sets the delivery finish date
     * @param deliveryEnd LocalDate with delivery end
     */
    public final void setDeliveryEnd(LocalDate deliveryEnd) {

        this.deliveryEnd = deliveryEnd;
    }

    /**
     * Sets the delivery run id
     * @param deliveryRun delivery run id
     */
    public void setDeliveryRun(int deliveryRun) {
        this.deliveryRun = deliveryRun;
    }

    /**
     * Delivery's String format
     * @return Delivery's String
     */
    @Override
    public String toString() {

        return "Delivery{" + "orderId=" + orderId + ", vehicleId=" + vehicleId + ", courierEmail=" + courierEmail + ", deliveryStatusId=" + deliveryStatusId + ", deliveryStart=" + deliveryStart + ", deliveryEnd=" + deliveryEnd + '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 3;
        hash = 71 * hash + this.orderId;
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
        final Delivery other = (Delivery) obj;

        return this.orderId == other.orderId;
    }
}
