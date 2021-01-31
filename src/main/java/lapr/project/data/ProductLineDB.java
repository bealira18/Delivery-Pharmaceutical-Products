package lapr.project.data;

import lapr.project.model.ProductLine;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles database interactions related to ProductLine.
 *
 * @author lapr3-2020-g052
 */
public class ProductLineDB extends DataHandler {

    /**
     * Adds a product line related to a purchase order
     * @param idOrder purchase order id
     * @param idProduct product id
     * @param quantity product quantity
     * @param price product price * quantity
     * @return true if added, false otherwise
     */
    public boolean newProductLine(int idOrder, int idProduct, int quantity, double price) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ call newProductLine(?,?,?,?) }")) {

                callStmt.setInt(1, idOrder);
                callStmt.setInt(2, idProduct);
                callStmt.setInt(3, quantity);
                callStmt.setDouble(4, price);

                callStmt.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ProductLineDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }

    /**
     * Gets all product lines from an order
     * @param idOrder purchase order id
     * @return all product lines from order
     */
    public List<ProductLine> getProductLinesFromOrder(int idOrder) {
        List<ProductLine> productLineList = new ArrayList<>();

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ ? = call getProductLinesFromOrder(?) }")) {

                callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt1.setInt(2, idOrder);

                callStmt1.execute();

                try (ResultSet rs = (ResultSet) callStmt1.getObject(1)) {

                    while (rs.next()) {
                        int orderId = rs.getInt(1);
                        int productId = rs.getInt(2);
                        int productQuantity = rs.getInt(3);
                        double price = rs.getDouble(4);

                        ProductLine productLine = new ProductLine(orderId, productId, productQuantity, price);
                        productLineList.add(productLine);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ProductLineDB.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            closeAll();
        }
        if (productLineList.isEmpty()) {
            throw new IllegalArgumentException("No product lines for order " + idOrder);
        }
        return productLineList;
    }
}
