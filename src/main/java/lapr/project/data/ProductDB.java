package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.model.Product;
import lapr.project.model.ProductCategory;
import oracle.jdbc.OracleTypes;

public class ProductDB extends DataHandler {

    public List<Product> getProducts() {

        List<Product> listProd = new ArrayList<>();

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getProducts() }")) {
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.execute();

                try (ResultSet rs = (ResultSet) callStmt.getObject(1)) {

                    while (rs.next()) {

                        int prodId = rs.getInt(1);
                        String prodName = rs.getString(2);
                        double prodPrice = rs.getDouble(3);
                        double prodWeight = rs.getDouble(4);
                        int prodIdCategory = rs.getInt(5);

                        listProd.add(new Product(prodId, prodName, prodPrice, prodWeight, prodIdCategory));
                    }
                    return listProd;
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();

        } finally {
            closeAll();
        }
    }

    public boolean addProduct(Product product) {

        return addProduct(product.getName(), product.getPrice(), product.getWeight(), product.getCategoryId());
    }

    public boolean addProduct(String name, double price, double weight, int category) {

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ call addProduct(?,?,?,?) }")) {

                callStmt1.setString(1, name);
                callStmt1.setDouble(2, price);
                callStmt1.setDouble(3, weight);
                callStmt1.setInt(4, category);

                callStmt1.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    public Product getProduct(int id) {

        try (Connection con2 = getConnection()) {

            try (CallableStatement callStmt2 = con2.prepareCall("{ ? = call getProduct(?) }")) {

                callStmt2.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt2.setInt(2, id);

                callStmt2.execute();

                try (ResultSet rs1 = (ResultSet) callStmt2.getObject(1)) {

                    if (rs1.next()) {
                        int idP = rs1.getInt(1);
                        String name = rs1.getString(2);
                        double price = rs1.getDouble(3);
                        double weight = rs1.getDouble(4);
                        int categoryId = rs1.getInt(5);

                        return new Product(idP, name, price, weight, categoryId);
                    }
                }
            }
        } catch (NullPointerException | SQLException e) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, e);
            throw new IllegalArgumentException("No Product with id:" + id);

        } finally {
            closeAll();
        }
        return null;
    }

    public boolean updateProduct(int id, Product p) {

        Product a = getProduct(id);
        if (a == null) {
            return false;
        }
        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ call updateProduct(?,?,?,?,?) }")) {

                callStmt3.setInt(1, p.getId());
                callStmt3.setString(2, p.getName());
                callStmt3.setInt(3, p.getCategoryId());
                callStmt3.setDouble(4, p.getPrice());
                callStmt3.setDouble(5, p.getWeight());

                callStmt3.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    public Map<ProductCategory, List<Product>> getProductsFromPharmacy(int idPharmacy) {

        HashMap<ProductCategory, List<Product>> mapProducts = new HashMap<>();

        try (Connection con4 = getConnection()) {

            try (CallableStatement callStmt4 = con4.prepareCall("{ ? = call getProductsFromPharmacy(?) }")) {

                callStmt4.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt4.setInt(2, idPharmacy);

                callStmt4.execute();

                try (ResultSet rs2 = (ResultSet) callStmt4.getObject(1)) {

                    while (rs2.next()) {
                        int idCategory = rs2.getInt(1);
                        String nameCategory = rs2.getString(2);
                        int idProduct = rs2.getInt(3);
                        String nameProduct = rs2.getString(4);
                        double priceProduct = rs2.getDouble(5);
                        double weight = rs2.getDouble(6);

                        ProductCategory pc = new ProductCategory(idCategory, nameCategory);
                        Product p = new Product(idProduct, nameProduct, priceProduct, weight, idCategory);

                        if (!mapProducts.containsKey(pc)) {
                            ArrayList<Product> listProducts = new ArrayList<>();
                            listProducts.add(p);
                            mapProducts.put(pc, listProducts);

                        } else {
                            List<Product> listProducts = mapProducts.get(pc);
                            listProducts.add(p);
                            mapProducts.put(pc, listProducts);
                        }
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        return mapProducts;
    }
}
