package lapr.project.data;

import lapr.project.model.ProductLine;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductLineDB extends DataHandler {

    public boolean newProductLine(int idOrder,int idProduct,int quantity,double price) throws SQLException {

        CallableStatement callStmt = null;

        try{
            openConnection();

            callStmt = getConnection().prepareCall("{ call newProductLine(?,?,?,?) }");

            callStmt.setInt(1,idOrder);
            callStmt.setInt(2,idProduct);
            callStmt.setInt(3,quantity);
            callStmt.setDouble(4,price);

            return true;
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            if(callStmt!=null) callStmt.close();
            closeAll();
        }

        return false;
    }

    public List<ProductLine> getProductLinesFromOrder (int idOrder) throws SQLException {
        List<ProductLine> productLineList = new ArrayList<>();

        CallableStatement callStmt = null;
        ResultSet rSet = null;
        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getProductLinesFromOrder(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idOrder);

            callStmt.execute();

            rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int orderId = rSet.getInt(1);
                int productId = rSet.getInt(2);
                int productQuantity = rSet.getInt(3);
                double price = rSet.getDouble(4);

                ProductLine productLine = new ProductLine(orderId, productId, productQuantity, price);
                productLineList.add(productLine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (callStmt != null) {
                callStmt.close();

                if (rSet != null) {
                    rSet.close();
                }
            }
            closeAll();
        }

        return productLineList;
    }
}
