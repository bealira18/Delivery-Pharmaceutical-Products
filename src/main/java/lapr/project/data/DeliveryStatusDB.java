package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryStatusDB extends DataHandler {

    public boolean updateDeliveryStatusInDelivery(int idOrder) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ call updateDeliveryStatusInDelivery(?) }")) {

                callStmt.setInt(1, idOrder);

                callStmt.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DeliveryStatusDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }
}
