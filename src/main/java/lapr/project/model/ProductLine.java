package lapr.project.model;

public class ProductLine {

    private int orderId;
    private int productId;
    private int productQuantity;
    private double price;

    /**
     * Initializes the ProductLine object
     * @param orderId purchase order id
     * @param productId bought product id
     * @param productQuantity bought product quantity
     * @param price product price * quantity
     */
    public ProductLine(int orderId, int productId, int productQuantity, double price) {

        this.orderId = orderId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.price = price;
    }

    /**
     * Gets the purchase order id
     * @return purchase order id
     */
    public int getOrderId() {

        return orderId;
    }

    /**
     * Gets the product id
     * @return product id
     */
    public int getProductId() {

        return productId;
    }

    /**
     * Gets the product quanity
     * @return product quantity
     */
    public int getProductQuantity() {

        return productQuantity;
    }

    /**
     * Gets the price for the product line
     * @return product line price
     */
    public double getPrice() {

        return price;
    }

    /**
     * Sets the purchase order id
     * @param orderId purchase order id
     */
    public void setOrderId(int orderId) {

        this.orderId = orderId;
    }

    /**
     * Sets the product id
     * @param productId product id
     */
    public void setProductId(int productId) {

        this.productId = productId;
    }

    /**
     * Sets the product quantity bought
     * @param productQuantity product quantity bought
     */
    public void setProductQuantity(int productQuantity) {

        this.productQuantity = productQuantity;
    }

    /**
     * Sets the price for the product line
     * @param price product line price
     */
    public void setPrice(double price) {

        this.price = price;
    }

    /**
     * ProductLine's String format
     * @return ProductLine's String
     */
    @Override
    public String toString() {

        return "ProductLine{" + "orderId=" + orderId + ", productId=" + productId + ", productQuantity=" + productQuantity + ", price=" + price + '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 3;
        hash = 67 * hash + this.orderId;
        hash = 67 * hash + this.productId;
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
        ProductLine that = (ProductLine) obj;

        return orderId == that.orderId
                && productId == that.productId;
    }
}
