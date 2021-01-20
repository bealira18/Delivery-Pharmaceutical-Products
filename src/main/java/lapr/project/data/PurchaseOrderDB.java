package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseOrderDB extends DataHandler {

    public boolean newOrder (int idOrder,int idPharmacy,String email) throws SQLException {

        CallableStatement callStmt = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ call newOrder(?,?,?) }");

            callStmt.setInt(1, idOrder);
            callStmt.setInt(2, idPharmacy);
            callStmt.setString(3, email);

            callStmt.execute();
            return true;
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            closeAll();
        }
        return false;
    }
}
