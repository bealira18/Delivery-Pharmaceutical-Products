package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseOrderDB extends DataHandler {

    public boolean newOrder(int idOrder, int idPharmacy, String email) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ call newOrder(?,?) }")) {

                callStmt.setInt(1, idPharmacy);
                callStmt.setString(2, email);

                callStmt.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PurchaseOrderDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }
}
