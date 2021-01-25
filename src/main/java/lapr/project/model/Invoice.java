package lapr.project.model;

public class Invoice {

    private int invoiceId;
    private int orderId;
    private int pharmacyId;
    private String clientEmail;
    private double deliveryFee;
    private double totalPrice;
    private double noVATprice;

    public Invoice(int id, int orderId, int pharmacyId, String clientEmail, double deliveryFee, double totalPrice, double noVATprice) {
        this.invoiceId = id;
        this.orderId = orderId;
        this.pharmacyId = pharmacyId;
        this.clientEmail = clientEmail;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.noVATprice = noVATprice;
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

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public double getTotalPrice() {

        return totalPrice;
    }

    public double getNoVATprice() {
        return noVATprice;
    }

    public static double getIVA() {
        
        double deliveryFee = Double.parseDouble(System.getProperty("invoice.iva", "0.0"));
        if (deliveryFee < 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative IVA). Please check your configuration file.");
        }
        return deliveryFee;
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

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public void setTotalPrice(double totalPrice) {

        this.totalPrice = totalPrice;
    }

    public void setNoVATprice(double noVATprice) {
        this.noVATprice = noVATprice;
    }

    public static void setIVA(double iva) {

        if (iva < 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative IVA)");
        }
        System.setProperty("invoice.iva", String.valueOf(iva));
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + invoiceId +
                ", orderId=" + orderId +
                ", pharmacyId=" + pharmacyId +
                ", clientEmail='" + clientEmail + '\'' +
                ", deliveryFee=" + deliveryFee +
                ", totalPrice=" + totalPrice +
                ", noVATprice=" + noVATprice +
                '}';
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
