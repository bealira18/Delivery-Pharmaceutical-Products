package lapr.project.model;

import java.util.Date;

public class Delivery {

    private int orderId;
    private int scooterId;
    private String courierEmail;
    private int deliveryStatusId;
    private Date deliveryStart;
    private Date deliveryEnd;

    public Delivery(int orderId, int scooterId, String courierEmail, int deliveryStatusId, Date deliveryStart, Date deliveryEnd) {

        this.orderId = orderId;
        this.scooterId = scooterId;
        this.courierEmail = courierEmail;
        this.deliveryStatusId = deliveryStatusId;
        setDeliveryStart(deliveryStart);
        setDeliveryEnd(deliveryEnd);
    }

    public Delivery() {

    }

    public int getOrderId() {

        return orderId;
    }

    public int getScooterId() {

        return scooterId;
    }

    public String getCourierEmail() {

        return courierEmail;
    }

    public int getDeliveryStatusId() {

        return deliveryStatusId;
    }

    public Date getDeliveryStart() {

        return (Date) deliveryStart.clone();
    }

    public Date getDeliveryEnd() {

        return (Date) deliveryEnd.clone();
    }

    public void setOrderId(int orderId) {

        this.orderId = orderId;
    }

    public void setScooterId(int scooterId) {

        this.scooterId = scooterId;
    }

    public void setCourierEmail(String courierEmail) {

        this.courierEmail = courierEmail;
    }

    public void setDeliveryStatusId(int deliveryStatusId) {

        this.deliveryStatusId = deliveryStatusId;
    }

    public final void setDeliveryStart(Date deliveryStart) {

        this.deliveryStart = (Date) deliveryStart.clone();
    }

    public final void setDeliveryEnd(Date deliveryEnd) {

        this.deliveryEnd = (Date) deliveryEnd.clone();
    }

    @Override
    public String toString() {

        return "Delivery{" + "orderId=" + orderId + ", scooterId=" + scooterId + ", courierEmail=" + courierEmail + ", deliveryStatusId=" + deliveryStatusId + ", deliveryStart=" + deliveryStart + ", deliveryEnd=" + deliveryEnd + '}';
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
