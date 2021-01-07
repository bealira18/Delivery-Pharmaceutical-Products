package lapr.project.model;

import java.util.Objects;

public class Stock {

    private int pharmacyId;
    private int productId;
    private int quantity;

    public Stock() {

        this.pharmacyId = 0;
        this.productId = 0;
        this.quantity = 0;
    }

    public Stock(int pharmacyId, int productId, int quantity) {

        this.pharmacyId = pharmacyId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getPharmacyId() {

        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    public int getProductId() {

        return productId;
    }

    public void setProductId(int productId) {

        this.productId = productId;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "pharmacyId=" + pharmacyId
                + ", productId=" + productId
                + ", quantity=" + quantity;
    }

    @Override
    public int hashCode() {

        return Objects.hash(pharmacyId, productId, quantity);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Stock stock = (Stock) obj;

        return pharmacyId == stock.pharmacyId
                && productId == stock.productId;
    }
}
