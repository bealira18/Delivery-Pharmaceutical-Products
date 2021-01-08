package lapr.project.model;

import java.util.Date;

public class PurchaseOrder {

    private int id;
    private int pharmacyId;
    private String clientEmail;
    private Date emissionDate;

    public PurchaseOrder(int id, int pharmacyId, String clientEmail, Date emissionDate) {

        this.id = id;
        this.pharmacyId = pharmacyId;
        this.clientEmail = clientEmail;
        this.emissionDate = emissionDate;
    }

    public PurchaseOrder() {

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

    public Date getEmissionDate() {

        return emissionDate;
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

    public void setEmissionDate(Date emissionDate) {

        this.emissionDate = emissionDate;
    }

    @Override
    public String toString() {

        return "Order{" + "id=" + id + ", pharmacyId=" + pharmacyId + ", clientEmail=" + clientEmail + ", emissionDate=" + emissionDate + '}';
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
