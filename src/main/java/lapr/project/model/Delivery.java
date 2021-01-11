package lapr.project.model;

import java.time.LocalDate;

public class Delivery {

    private int orderId;
    private int vehicleId;
    private String courierEmail;
    private int deliveryStatusId;
    private LocalDate deliveryStart;
    private LocalDate deliveryEnd;
    private int deliveryRun;

    public Delivery(int orderId, int vehicleId, String courierEmail, int deliveryStatusId, LocalDate deliveryStart, LocalDate deliveryEnd, int deliveryRun) {

        this.orderId = orderId;
        this.vehicleId = vehicleId;
        this.courierEmail = courierEmail;
        this.deliveryStatusId = deliveryStatusId;
        setDeliveryStart(deliveryStart);
        setDeliveryEnd(deliveryEnd);
        this.deliveryRun = deliveryRun;
    }

    public int getOrderId() {

        return orderId;
    }

    public int getVehicleId() {

        return vehicleId;
    }

    public String getCourierEmail() {

        return courierEmail;
    }

    public int getDeliveryStatusId() {

        return deliveryStatusId;
    }

    public LocalDate getDeliveryStart() {

        return deliveryStart;
    }

    public LocalDate getDeliveryEnd() {

        return deliveryEnd;
    }

    public int getDeliveryRun() {
        return deliveryRun;
    }

    public void setOrderId(int orderId) {

        this.orderId = orderId;
    }

    public void setVehicleId(int scooterId) {

        this.vehicleId = scooterId;
    }

    public void setCourierEmail(String courierEmail) {

        this.courierEmail = courierEmail;
    }

    public void setDeliveryStatusId(int deliveryStatusId) {

        this.deliveryStatusId = deliveryStatusId;
    }

    public final void setDeliveryStart(LocalDate deliveryStart) {

        this.deliveryStart = deliveryStart;
    }

    public final void setDeliveryEnd(LocalDate deliveryEnd) {

        this.deliveryEnd = deliveryEnd;
    }

    public void setDeliveryRun(int deliveryRun) {
        this.deliveryRun = deliveryRun;
    }

    @Override
    public String toString() {

        return "Delivery{" + "orderId=" + orderId + ", vehicleId=" + vehicleId + ", courierEmail=" + courierEmail + ", deliveryStatusId=" + deliveryStatusId + ", deliveryStart=" + deliveryStart + ", deliveryEnd=" + deliveryEnd + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 71 * hash + this.orderId;
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
        final Delivery other = (Delivery) obj;

        return this.orderId == other.orderId;
    }
}
