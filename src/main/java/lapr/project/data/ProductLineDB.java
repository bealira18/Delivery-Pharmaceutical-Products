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

public class ProductLineDB extends DataHandler {

    public boolean newProductLine(int idOrder,int idProduct,int quantity,double price) {

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

    public List<ProductLine> getProductLinesFromOrder (int idOrder) {
        List<ProductLine> productLineList = new ArrayList<>();

        try (Connection con = getConnection()) {

            try(CallableStatement callStmt = con.prepareCall("{ ? = call getProductLinesFromOrder(?) }")) {

                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(2, idOrder);

                callStmt.execute();

                try (ResultSet rSet = (ResultSet) callStmt.getObject(1)) {

                    while (rSet.next()) {
                        int orderId = rSet.getInt(1);
                        int productId = rSet.getInt(2);
                        int productQuantity = rSet.getInt(3);
                        double price = rSet.getDouble(4);

                        ProductLine productLine = new ProductLine(orderId, productId, productQuantity, price);
                        productLineList.add(productLine);
                    }
                }
            }
        } catch (NullPointerException | SQLException e) {
            Logger.getLogger(ProductLineDB.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeAll();
        }
        if(productLineList.isEmpty()) throw new IllegalArgumentException("No product lines for order " + idOrder);
        return productLineList;
    }
}
