package lapr.project.model;

public class Product {

    private int productId;
    private String name;
    private double price;
    private double weight;
    private int categoryId;

    public Product(int id, String name, double price, double weight, int categoryId) {

        this.productId = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.categoryId = categoryId;
    }

    public int getId() {

        return productId;
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

    public int getCategoryId() {

        return categoryId;
    }

    public void setId(int id) {

        this.productId = id;
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

    public void setCategoryId(int categoryId) {

        this.categoryId = categoryId;
    }

    @Override
    public String toString() {

        return "Product{" + "id=" + productId + ", name=" + name + ", price=" + price + ", weight=" + weight + ", categoryId=" + categoryId + '}';
    }

    @Override
    public int hashCode() {

        int hash = 13;
        hash = 59 * hash + this.productId;
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

        return this.productId == other.productId;
    }
}
