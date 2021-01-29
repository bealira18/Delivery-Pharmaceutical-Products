package lapr.project.model;

public class Product {

    private int productId;
    private String name;
    private double price;
    private double weight;
    private int categoryId;

    /**
     * Initializes the Product object
     * @param id product id
     * @param name product name
     * @param price product price
     * @param weight product weight
     * @param categoryId product category
     */
    public Product(int id, String name, double price, double weight, int categoryId) {

        this.productId = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.categoryId = categoryId;
    }

    /**
     * Gets product id
     * @return product id
     */
    public int getId() {

        return productId;
    }

    /**
     * Gets product name
     * @return product name
     */
    public String getName() {

        return name;
    }

    /**
     * Gets product price
     * @return product price
     */
    public double getPrice() {

        return price;
    }

    /**
     * Gets product weight
     * @return product weight
     */
    public double getWeight() {

        return weight;
    }

    /**
     * Gets product category
     * @return product category
     */
    public int getCategoryId() {

        return categoryId;
    }

    /**
     * Sets product id
     * @param id product id
     */
    public void setId(int id) {

        this.productId = id;
    }

    /**
     * Sets product name
     * @param name product name
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Sets product price
     * @param price product price
     */
    public void setPrice(double price) {

        this.price = price;
    }

    /**
     * Sets product weight
     * @param weight product weight
     */
    public void setWeight(double weight) {

        this.weight = weight;
    }

    /**
     * Sets product category
     * @param categoryId product category
     */
    public void setCategoryId(int categoryId) {

        this.categoryId = categoryId;
    }

    /**
     * Product's String format
     * @return Product's String
     */
    @Override
    public String toString() {

        return "{name=" + name + ", price=" + price + "€, weight=" + weight + "kg}";
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 13;
        hash = 59 * hash + this.productId;
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
        final Product other = (Product) obj;

        return this.productId == other.productId;
    }
}
