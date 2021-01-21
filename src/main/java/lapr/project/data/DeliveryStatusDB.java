package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryStatusDB extends DataHandler {

    public boolean updateDeliveryStatusInDelivery(int idOrder) throws SQLException {
        CallableStatement callStmt = null;

        try{

            callStmt = getConnection().prepareCall("{ call updateDeliveryStatusInDelivery(?) }");

            callStmt.setInt(1, idOrder);

            callStmt.execute();

            return true;
        } catch (NullPointerException | SQLException ex){
            Logger.getLogger(DeliveryStatusDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();

        } finally {
            if(callStmt!=null) callStmt.close();
            closeAll();
        }
        return false;
    }
}
