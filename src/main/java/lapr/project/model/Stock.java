package lapr.project.model;

public class Stock {

    private int pharmacyId;
    private int productId;
    private int quantity;

    public Stock(int pharmacyId, int productId, int quantity) {

        this.pharmacyId = pharmacyId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getPharmacyId() {

        return pharmacyId;
    }

    public int getProductId() {

        return productId;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    public void setProductId(int productId) {

        this.productId = productId;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    @Override
    public String toString() {

        return "Stock{" + "pharmacyId=" + pharmacyId + ", productId=" + productId + ", quantity=" + quantity + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 83 * hash + this.pharmacyId;
        hash = 83 * hash + this.productId;
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
        Stock other = (Stock) obj;

        return pharmacyId == other.pharmacyId && productId == other.productId;
    }
}
