package lapr.project.model;

import java.util.Objects;

public class ProductLine {

    private int orderId;
    private int productId;
    private int productQuantity;
    private double price;

    public ProductLine() {

        this.orderId = 0;
        this.productId = 0;
        this.productQuantity = 0;
        this.price = 0;
    }

    public ProductLine(int orderId, int productId, int productQuantity, double price) {

        this.orderId = orderId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.price = price;
    }

    public int getOrderId() {

        return orderId;
    }

    public void setOrderId(int orderId) {

        this.orderId = orderId;
    }

    public int getProductId() {

        return productId;
    }

    public void setProductId(int productId) {

        this.productId = productId;
    }

    public int getProductQuantity() {

        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {

        this.productQuantity = productQuantity;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    @Override
    public String toString() {

        return "orderId=" + orderId
                + ", productId=" + productId
                + ", productQuantity=" + productQuantity
                + ", price=" + price;
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, productId, productQuantity, price);
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
