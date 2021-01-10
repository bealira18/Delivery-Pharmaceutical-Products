package lapr.project.model;

public class ProductCategory {

    private int id;
    private String name;

    public ProductCategory(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return "ProductCategory{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public int hashCode() {

        int hash = 21;
        hash = 71 * hash + this.id;
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

        return this.id == other.id;
    }
}
