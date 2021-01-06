package lapr.project.model;

import java.util.Objects;

public class Product {

    private int productId;
    private String name;
    private double price;
    private double weight;

    public Product(){
        this.productId=0;
        this.name=null;
        this.price=0;
        this.weight=0;
    }

    public Product(int productId, String name, double price, double weight) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, price, weight);
    }

    @Override
    public String toString() {
        return "productId=" + productId +
                ", name='" + name +
                ", price=" + price +
                ", weight=" + weight;
    }
}
