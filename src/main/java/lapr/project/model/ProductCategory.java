package lapr.project.model;

/**
 * This class serves as a container for a product category information.
 * @author lapr3-2020-g052
 */
public class ProductCategory {

    private int productCategoryId;
    private String productCategoryName;

    /**
     * Initializes the ProductCategory object
     * @param id product categoty id
     * @param name product category description
     */
    public ProductCategory(int id, String name) {

        this.productCategoryId = id;
        this.productCategoryName = name;
    }

    /**
     * Gets the product category id
     * @return product categoty id
     */
    public int getProductCategoryId() {

        return productCategoryId;
    }

    /**
     * Gets the product category name
     * @return product category name
     */
    public String getProductCategoryName() {

        return productCategoryName;
    }

    /**
     * Sets the product categoty id
     * @param productCategoryId product category id
     */
    public void setProductCategoryId(int productCategoryId) {

        this.productCategoryId = productCategoryId;
    }

    /**
     * Sets the product category name
     * @param productCategoryName product category name
     */
    public void setProductCategoryName(String productCategoryName) {

        this.productCategoryName = productCategoryName;
    }

    /**
     * ProductCategory's String format
     * @return ProductCategory's String
     */
    @Override
    public String toString() {

        return "ProductCategory{" + "productCategoryId=" + productCategoryId + ", productCategoryName=" + productCategoryName + '}';
    }

    /**
     * Returns a hash code value for the object
     * @return hash code value for the object
     */
    @Override
    public int hashCode() {

        int hash = 21;
        hash = 71 * hash + this.productCategoryId;
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
        final ProductCategory other = (ProductCategory) obj;

        return this.productCategoryId == other.productCategoryId;
    }
}
