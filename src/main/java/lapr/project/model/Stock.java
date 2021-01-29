package lapr.project.model;

public class Stock {

    private int pharmacyId;
    private int productId;
    private int quantity;

    /**
     * Initializes the Stock object
     * @param pharmacyId pharmacy id
     * @param productId product id
     * @param quantity product quantity
     */
    public Stock(int pharmacyId, int productId, int quantity) {

        this.pharmacyId = pharmacyId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Gets pharmacy id
     * @return the pharmacy's id
     */
    public int getPharmacyId() {

        return pharmacyId;
    }

    /**
     * Gets product id
     * @return the product's id
     */
    public int getProductId() {

        return productId;
    }

    /**
     * Gets the product quantity
     * @return the product quantity
     */
    public int getQuantity() {

        return quantity;
    }

    /**
     * Sets the pharmacy id
     * @param pharmacyId pharmacy id
     */
    public void setPharmacyId(int pharmacyId) {

        this.pharmacyId = pharmacyId;
    }

    /**
     * Sets the product id
     * @param productId product id
     */
    public void setProductId(int productId) {

        this.productId = productId;
    }

    /**
     * Sets the product quantity in stock
     * @param quantity product quantity
     */
    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    /**
     * Stock's String format
     * @return Stick's String
     */
    @Override
    public String toString() {

        return "Stock{" + "pharmacyId=" + pharmacyId + ", productId=" + productId + ", quantity=" + quantity + '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 7;
        hash = 83 * hash + this.pharmacyId;
        hash = 83 * hash + this.productId;
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
        Stock other = (Stock) obj;

        return pharmacyId == other.pharmacyId && productId == other.productId;
    }
}
