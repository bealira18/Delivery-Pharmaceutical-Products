package lapr.project.model;

import java.util.Date;

public class Order {

    private int id;
    private int pharmacy_id;
    private String client_email;
    private Date emission_date;

    public Order(int id, int pharmacy_id, String client_email, Date emission_date) {

        this.id = id;
        this.pharmacy_id = pharmacy_id;
        this.client_email = client_email;
        this.emission_date = emission_date;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public int getPharmacy_id() {

        return pharmacy_id;
    }

    public String getClient_email() {

        return client_email;
    }

    public Date getEmission_date() {

        return emission_date;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setPharmacy_id(int pharmacy_id) {

        this.pharmacy_id = pharmacy_id;
    }

    public void setClient_email(String client_email) {

        this.client_email = client_email;
    }

    public void setEmission_date(Date emission_date) {

        this.emission_date = emission_date;
    }

    @Override
    public String toString() {

        return "Order{" + "id=" + id + ", pharmacy_id=" + pharmacy_id + ", client_email=" + client_email + ", emission_date=" + emission_date + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 29 * hash + this.id;
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
        final Order other = (Order) obj;

        return this.id == other.id;
    }
}
