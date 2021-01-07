package lapr.project.model;

import java.util.Date;

public class Delivery {

    private int order_id;
    private int scooter_id;
    private String courier_email;
    private int delivery_status_id;
    private Date delivery_start;
    private Date delivery_end;

    public Delivery(int order_id, int scooter_id, String courier_email, int delivery_status_id, Date delivery_start, Date delivery_end) {

        this.order_id = order_id;
        this.scooter_id = scooter_id;
        this.courier_email = courier_email;
        this.delivery_status_id = delivery_status_id;
        this.delivery_start = delivery_start;
        this.delivery_end = delivery_end;
    }

    public Delivery() {

    }

    public int getOrder_id() {

        return order_id;
    }

    public int getScooter_id() {

        return scooter_id;
    }

    public String getCourier_email() {

        return courier_email;
    }

    public int getDelivery_status_id() {

        return delivery_status_id;
    }

    public Date getDelivery_start() {

        return delivery_start;
    }

    public Date getDelivery_end() {

        return delivery_end;
    }

    public void setOrder_id(int order_id) {

        this.order_id = order_id;
    }

    public void setScooter_id(int scooter_id) {

        this.scooter_id = scooter_id;
    }

    public void setCourier_email(String courier_email) {

        this.courier_email = courier_email;
    }

    public void setDelivery_status_id(int delivery_status_id) {

        this.delivery_status_id = delivery_status_id;
    }

    public void setDelivery_start(Date delivery_start) {

        this.delivery_start = delivery_start;
    }

    public void setDelivery_end(Date delivery_end) {

        this.delivery_end = delivery_end;
    }

    @Override
    public String toString() {

        return "Delivery{" + "order_id=" + order_id + ", scooter_id=" + scooter_id + ", courier_email=" + courier_email + ", delivery_status_id=" + delivery_status_id + ", delivery_start=" + delivery_start + ", delivery_end=" + delivery_end + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 71 * hash + this.order_id;
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

        return this.order_id == other.order_id;
    }
}
