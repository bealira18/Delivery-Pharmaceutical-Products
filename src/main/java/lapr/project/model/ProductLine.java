package lapr.project.model;

public class ProductLine {

    private int orderId;
    private int productId;
    private int productQuantity;
    private double price;

    public ProductLine(int orderId, int productId, int productQuantity, double price) {

        this.orderId = orderId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.price = price;
    }

    public int getOrderId() {

        return orderId;
    }

    public int getProductId() {

        return productId;
    }

    public int getProductQuantity() {

        return productQuantity;
    }

    public double getPrice() {

        return price;
    }

    public void setOrderId(int orderId) {

        this.orderId = orderId;
    }

    public void setProductId(int productId) {

        this.productId = productId;
    }

    public void setProductQuantity(int productQuantity) {

        this.productQuantity = productQuantity;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    @Override
    public String toString() {

        return "ProductLine{" + "orderId=" + orderId + ", productId=" + productId + ", productQuantity=" + productQuantity + ", price=" + price + '}';
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 67 * hash + this.orderId;
        hash = 67 * hash + this.productId;
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
        ProductLine that = (ProductLine) obj;

        return orderId == that.orderId
                && productId == that.productId;
    }
}
