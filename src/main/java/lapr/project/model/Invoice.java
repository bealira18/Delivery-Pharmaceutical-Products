package lapr.project.model;

public class Invoice {

    private int invoiceId;
    private int orderId;
    private int pharmacyId;
    private String clientEmail;
    private double deliveryFee;
    private double totalPrice;
    private double noVATprice;

    /**
     * Initializes the Invoice object
     * @param id invoice id
     * @param orderId purchase order id
     * @param pharmacyId pharmacy bought from id
     * @param clientEmail client email
     * @param deliveryFee delivery fee
     * @param totalPrice total price
     * @param noVATprice price without VAT
     */
    public Invoice(int id, int orderId, int pharmacyId, String clientEmail, double deliveryFee, double totalPrice, double noVATprice) {
        this.invoiceId = id;
        this.orderId = orderId;
        this.pharmacyId = pharmacyId;
        this.clientEmail = clientEmail;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.noVATprice = noVATprice;
    }

    /**
     * Gets the invoice id
     * @return invoice id
     */
    public int getId() {

        return invoiceId;
    }

    /**
     * Gets the id of the purchase order
     * @return purchase order id
     */
    public int getOrderId() {

        return orderId;
    }

    /**
     * Gets the pharmacy id
     * @return pharmacy id
     */
    public int getPharmacyId() {

        return pharmacyId;
    }

    /**
     * Gets the client email
     * @return client email
     */
    public String getClientEmail() {

        return clientEmail;
    }

    /**
     * Gets the delivery fee paid
     * @return delivery fee paid
     */
    public double getDeliveryFee() {
        return deliveryFee;
    }

    /**
     * Gets the total price
     * @return total price
     */
    public double getTotalPrice() {

        return totalPrice;
    }

    /**
     * Gets the price without VAT
     * @return price without VAT
     */
    public double getNoVATprice() {
        return noVATprice;
    }

    /**
     * Gets the VAT defined in the properties
     * @return VAT
     */
    public static double getIVA() {
        
        double deliveryFee = Double.parseDouble(System.getProperty("invoice.iva", "0.0"));
        if (deliveryFee < 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative IVA). Please check your configuration file.");
        }
        return deliveryFee;
    }


    /**
     * Sets the invoice id
     * @param id invoice id
     */
    public void setId(int id) {

        this.invoiceId = id;
    }

    /**
     * Sets the purchase order id
     * @param orderId purchase order id
     */
    public void setOrderId(int orderId) {

        this.orderId = orderId;
    }

    /**
     * Sets the pharmacy id
     * @param pharmacyId pharmacy id
     */
    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    /**
     * Sets the client email
     * @param clientEmail client email
     */
    public void setClientEmail(String clientEmail) {

        this.clientEmail = clientEmail;
    }

    /**
     * Sets the delivery fee paid
     * @param deliveryFee delivery fee
     */
    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    /**
     * Sets the total price paid
     * @param totalPrice total price paid
     */
    public void setTotalPrice(double totalPrice) {

        this.totalPrice = totalPrice;
    }

    /**
     * Sets the price without VAT
     * @param noVATprice price without VAT
     */
    public void setNoVATprice(double noVATprice) {
        this.noVATprice = noVATprice;
    }

    /**
     * Gets the VAT defined in the properties
     * @param iva VAT
     */
    public static void setIVA(double iva) {

        if (iva < 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative IVA)");
        }
        System.setProperty("invoice.iva", String.valueOf(iva));
    }

    /**
     * Invoice's String format
     * @return Invoice's String
     */
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

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 47 * hash + this.invoiceId;
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
        Invoice other = (Invoice) obj;

        return this.invoiceId == other.invoiceId;
    }
}
