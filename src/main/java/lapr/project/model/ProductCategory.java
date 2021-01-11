package lapr.project.model;

public class ProductCategory {

    private int productCategoryId;
    private String productCategoryName;

    public ProductCategory(int id, String name) {

        this.productCategoryId = id;
        this.productCategoryName = name;
    }

    public int getProductCategoryId() {

        return productCategoryId;
    }

    public String getProductCategoryName() {

        return productCategoryName;
    }

    public void setProductCategoryId(int productCategoryId) {

        this.productCategoryId = productCategoryId;
    }

    public void setProductCategoryName(String productCategoryName) {

        this.productCategoryName = productCategoryName;
    }

    @Override
    public String toString() {

        return "ProductCategory{" + "productCategoryId=" + productCategoryId + ", productCategoryName=" + productCategoryName + '}';
    }

    @Override
    public int hashCode() {

        int hash = 21;
        hash = 71 * hash + this.productCategoryId;
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
        final ProductCategory other = (ProductCategory) obj;

        return this.productCategoryId == other.productCategoryId;
    }
}
