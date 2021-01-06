package lapr.project.model;

// Does this class need to exist?
public class Invoice {

    private int id;
    private int order_id;
    private int pharmacy_id;
    private String client_email;
    private double total_price;

    public Invoice(int id, int order_id, int pharmacy_id, String client_email, double total_price) {

        this.id = id;
        this.order_id = order_id;
        this.pharmacy_id = pharmacy_id;
        this.client_email = client_email;
        this.total_price = total_price;
    }

    public int getId() {

        return id;
    }

    public int getOrder_id() {

        return order_id;
    }

    public int getPharmacy_id() {

        return pharmacy_id;
    }

    public String getClient_email() {

        return client_email;
    }

    public double getTotal_price() {

        return total_price;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setOrder_id(int order_id) {

        this.order_id = order_id;
    }

    public void setPharmacy_id(int pharmacy_id) {

        this.pharmacy_id = pharmacy_id;
    }

    public void setClient_email(String client_email) {

        this.client_email = client_email;
    }

    public void setTotal_price(double total_price) {

        this.total_price = total_price;
    }

    @Override
    public String toString() {

        return "Invoice{" + "id=" + id + ", order_id=" + order_id + ", pharmacy_id=" + pharmacy_id + ", client_email=" + client_email + ", total_price=" + total_price + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 47 * hash + this.id;
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
        final Invoice other = (Invoice) obj;

        return this.id == other.id;
    }
}
