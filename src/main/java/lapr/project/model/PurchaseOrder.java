package lapr.project.model;

import java.time.LocalDate;

public class PurchaseOrder {

    private int purchaseOrderId;
    private int pharmacyId;
    private String clientEmail;
    private LocalDate emissionDate;

    /**
     * Initializes the PurchaseOrder object
     * @param id purchase order id
     * @param pharmacyId pharmacy bought from id
     * @param clientEmail client email
     * @param emissionDate date of purchase
     */
    public PurchaseOrder(int id, int pharmacyId, String clientEmail, LocalDate emissionDate) {

        this.purchaseOrderId = id;
        this.pharmacyId = pharmacyId;
        this.clientEmail = clientEmail;
        setEmissionDate(emissionDate);
    }

    /**
     * Gets the order id
     * @return order id
     */
    public int getId() {

        return purchaseOrderId;
    }

    /**
     * Gets the pharmacy bought from id
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
     * Gets the date of the purchase
     * @return purchase date
     */
    public LocalDate getEmissionDate() {

        return emissionDate;
    }

    /**
     * Gets the delivery fee defined in the properties
     * @return delivery fee
     */
    public static double getDeliveryFee() {
        
        double deliveryFee = Double.parseDouble(System.getProperty("purchase.order.delivery.fee", "0.0"));
        if (deliveryFee < 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative Delivery Fee). Please check your configuration file.");
        }
        return deliveryFee;
    }

    /**
     * Sets the order id
     * @param id order id
     */
    public void setId(int id) {

        this.purchaseOrderId = id;
    }

    /**
     * Sets the pharmacy bought from id
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
     * Sets the date of the order
     * @param emissionDate order date
     */
    public final void setEmissionDate(LocalDate emissionDate) {

        this.emissionDate = emissionDate;
    }

    /**
     * Sets the delivery fee in the properties
     * @param deliveryFee delivery fee
     */
    public static void setDeliveryFee(double deliveryFee) {

        if (deliveryFee < 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative Delivery Fee)");
        }
        System.setProperty("purchase.order.delivery.fee", String.valueOf(deliveryFee));
    }

    /**
     * PurchaseOrder's String format
     * @return PurchaseOrder's String
     */
    @Override
    public String toString() {

        return "PurchaseOrder{" + "id=" + purchaseOrderId + ", pharmacyId=" + pharmacyId + ", clientEmail=" + clientEmail + ", emissionDate=" + emissionDate + '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 29 * hash + this.purchaseOrderId;
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
        final PurchaseOrder other = (PurchaseOrder) obj;

        return this.purchaseOrderId == other.purchaseOrderId;
    }
}
