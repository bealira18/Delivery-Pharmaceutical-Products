package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductLineDB extends DataHandler {

    public boolean newProductLine(int idOrder,int idProduct,int quantity,double price) throws SQLException {

        CallableStatement callStmt = null;

        try{
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
            if (callStmt != null) {
                callStmt.close();
            }
        }

        return false;
    }
}
