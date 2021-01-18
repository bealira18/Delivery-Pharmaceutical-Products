package lapr.project.model;

public class Invoice {

    private int invoiceId;
    private int orderId;
    private int pharmacyId;
    private String clientEmail;
    private double totalPrice;

    public Invoice(int id, int orderId, int pharmacyId, String clientEmail, double totalPrice) {
        this.invoiceId = id;
        this.orderId = orderId;
        this.pharmacyId = pharmacyId;
        this.clientEmail = clientEmail;
        this.totalPrice = totalPrice;
    }

    public int getId() {

        return invoiceId;
    }

    public int getOrderId() {

        return orderId;
    }

    public int getPharmacyId() {

        return pharmacyId;
    }

    public String getClientEmail() {

        return clientEmail;
    }

    public double getTotalPrice() {

        return totalPrice;
    }

    public void setId(int id) {

        this.invoiceId = id;
    }

    public void setOrderId(int orderId) {

        this.orderId = orderId;
    }

    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    public void setClientEmail(String clientEmail) {

        this.clientEmail = clientEmail;
    }

    public void setTotalPrice(double totalPrice) {

        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {

        return "Invoice{" + "id=" + invoiceId + ", orderId=" + orderId + ", pharmacyId=" + pharmacyId + ", clientEmail=" + clientEmail + ", totalPrice=" + totalPrice + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 47 * hash + this.invoiceId;
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
        Invoice other = (Invoice) obj;

        return this.invoiceId == other.invoiceId;
    }
}
