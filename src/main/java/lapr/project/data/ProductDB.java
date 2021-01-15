package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Product;
import oracle.jdbc.OracleTypes;

public class ProductDB extends DataHandler {

    public List<Product> getProducts() throws SQLException {

        List<Product> listProd = new ArrayList<>();
        CallableStatement callStmt = null;
        ResultSet rs = null;

        try {
            callStmt = getConnection().prepareCall("{ ? = call getProducts() }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();

            rs = (ResultSet) callStmt.getObject(1);

            while (rs.next()) {

                int prodId = rs.getInt(1);
                String prodName = rs.getString(2);
                double prodPrice = rs.getDouble(3);
                double prodWeight = rs.getDouble(4);
                int prodIdCategory = rs.getInt(5);

                listProd.add(new Product(prodId, prodName, prodPrice, prodWeight, prodIdCategory));
            }
            return listProd;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();

        } finally {
            if (callStmt != null) {
                callStmt.close();

                if (rs != null) {
                    rs.close();
                }
            }
            closeAll();
        }
    }

    public boolean addProduct(Product product) throws SQLException {

        openConnection();

        try {
            return addProduct(product.getName(), product.getPrice(), product.getWeight(), product.getCategoryId());

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    public boolean addProduct(String name, double price, double weight, int category) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{ call addProduct(?,?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setDouble(2, price);
            callStmt.setDouble(3, weight);
            callStmt.setInt(4, category);

            callStmt.execute();
            return true;

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
    }

    public Product getProduct(int id) throws SQLException {
        Product p = null;
        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{ ? = call getProduct(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setInt(1, id);
            // Executa a invocação da função "getSailor".
            callStmt.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                //Product(int id, String name, double price, double weight, int categoryId)
                int idP = rSet.getInt(1);
                String name = rSet.getString(2);
                double price = rSet.getDouble(3);
                double weight = rSet.getDouble(4);
                int categoryId = rSet.getInt(5);

                p = new Product(idP, name, price, weight, categoryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No Product with id:" + id);
        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
            //return p;
        }
        return p;
    }

    public boolean updateProduct(int id, Product p) throws SQLException {
        Product a;

        try {
            a = getProduct(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        CallableStatement callStmt = null;

        try{
            callStmt.getConnection().prepareCall("{ call updateCourier(?,?,?,?,?) }");

            callStmt.setInt(1,p.getId());
            callStmt.setString(2,p.getName());
            callStmt.setInt(3,p.getCategoryId());
            callStmt.setDouble(4,p.getPrice());
            callStmt.setDouble(5,p.getWeight());

        } catch (NullPointerException | SQLException ex){
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
    }
}
