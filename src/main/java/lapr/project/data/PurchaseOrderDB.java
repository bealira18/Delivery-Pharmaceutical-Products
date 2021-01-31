package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles database interactions related to PurchaseOrder.
 *
 * @author lapr3-2020-g052
 */
public class PurchaseOrderDB extends DataHandler {

    /**
     * Adds a purchase order to the database
     * @param idOrder purchase order id
     * @param idPharmacy pharmacy id
     * @param email client email
     * @return true if added, false otherwise
     */
    public boolean newOrder(int idOrder, int idPharmacy, String email) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ call newOrder(?,?,?) }")) {

                callStmt.setInt(1, idOrder);
                callStmt.setInt(2, idPharmacy);
                callStmt.setString(3, email);

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
