package lapr.project.model;

import java.util.Objects;

public class Product {

    private int id;
    private String name;
    private double price;
    private double weight;
    private int category_id;

    public Product(int id, String name, double price, double weight, int category_id) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.category_id = category_id;
    }

    public Product() {

    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public double getPrice() {

        return price;
    }

    public double getWeight() {

        return weight;
    }

    public int getCategory_id() {

        return category_id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public void setWeight(double weight) {

        this.weight = weight;
    }

    public void setCategory_id(int category_id) {

        this.category_id = category_id;
    }

    @Override
    public String toString() {

        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", weight=" + weight + ", category_id=" + category_id + '}';
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 59 * hash + this.id;
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
        final Product other = (Product) obj;

        return this.id == other.id;
    }
}
