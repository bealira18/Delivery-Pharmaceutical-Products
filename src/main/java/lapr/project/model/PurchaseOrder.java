package lapr.project.model;

import java.time.LocalDate;

public class PurchaseOrder {

    private static double deliveryFee = 0;

    private int id;
    private int pharmacyId;
    private String clientEmail;
    private LocalDate emissionDate;

    public PurchaseOrder(int id, int pharmacyId, String clientEmail, LocalDate emissionDate) {

        this.id = id;
        this.pharmacyId = pharmacyId;
        this.clientEmail = clientEmail;
        setEmissionDate(emissionDate);
    }

    public int getId() {

        return id;
    }

    public int getPharmacyId() {

        return pharmacyId;
    }

    public String getClientEmail() {

        return clientEmail;
    }

    public LocalDate getEmissionDate() {

        return emissionDate;
    }

    public static double getDeliveryFee() {

        return deliveryFee;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    public void setClientEmail(String clientEmail) {

        this.clientEmail = clientEmail;
    }

    public final void setEmissionDate(LocalDate emissionDate) {

        this.emissionDate = emissionDate;
    }

    public static void setDeliveryFee(double deliveryFee) {

        if (deliveryFee < 0) {
            throw new IllegalArgumentException("Invalid Numeric Value (Negative Delivery Fee)");
        }
        PurchaseOrder.deliveryFee = deliveryFee;
    }

    @Override
    public String toString() {

        return "PurchaseOrder{" + "id=" + id + ", pharmacyId=" + pharmacyId + ", clientEmail=" + clientEmail + ", emissionDate=" + emissionDate + '}';
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
        final PurchaseOrder other = (PurchaseOrder) obj;

        return this.id == other.id;
    }
}
