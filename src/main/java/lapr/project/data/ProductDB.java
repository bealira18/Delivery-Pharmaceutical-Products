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

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ call addProduct(?,?,?,?) }")) {

                callStmt.setString(1, name);
                callStmt.setDouble(2, price);
                callStmt.setDouble(3, weight);
                callStmt.setInt(4, category);

                callStmt.execute();
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
        Product p = null;

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt1 = con.prepareCall("{ ? = call getProduct(?) }")) {

                // Regista o tipo de dados SQL para interpretar o resultado obtido.
                callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
                // Especifica o parâmetro de entrada da função "getSailor".
                callStmt1.setInt(2, id);
                // Executa a invocação da função "getSailor".
                callStmt1.execute();
                // Guarda o cursor retornado num objeto "ResultSet".
                try (ResultSet rSet = (ResultSet) callStmt1.getObject(1)) {

                    if (rSet.next()) {
                        //Product(int id, String name, double price, double weight, int categoryId)
                        int idP = rSet.getInt(1);
                        String name = rSet.getString(2);
                        double price = rSet.getDouble(3);
                        double weight = rSet.getDouble(4);
                        int categoryId = rSet.getInt(5);

                        p = new Product(idP, name, price, weight, categoryId);
                    }
                }
            }
        } catch (NullPointerException | SQLException e) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, e);
            throw new IllegalArgumentException("No Product with id:" + id);
        } finally {
            closeAll();
        }
        return p;
    }

    public boolean updateProduct(int id, Product p) {
        Product a;

        a = getProduct(id);
        if (a == null) {
            return false;
        }

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = getConnection().prepareCall("{ call updateProduct(?,?,?,?,?) }")) {

                callStmt.setInt(1, p.getId());
                callStmt.setString(2, p.getName());
                callStmt.setInt(3, p.getCategoryId());
                callStmt.setDouble(4, p.getPrice());
                callStmt.setDouble(5, p.getWeight());

                callStmt.execute();

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

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getProductsFromPharmacy(?) }")) {

                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(2, idPharmacy);

                callStmt.execute();

                try (ResultSet rs = (ResultSet) callStmt.getObject(1)) {

                    while (rs.next()) {
                        int idCategory = rs.getInt(1);
                        String nameCategory = rs.getString(2);
                        int idProduct = rs.getInt(3);
                        String nameProduct = rs.getString(4);
                        double priceProduct = rs.getDouble(5);
                        double weight = rs.getDouble(6);

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
