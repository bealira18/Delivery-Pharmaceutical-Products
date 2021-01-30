package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehicleStatusDB extends DataHandler {

    /**
     * Calls the data base to update the vehicle status
     *
     * @param idVehicle vehicle id
     * @param status vehicle status
     * @return true if updates the vehicle status, false otherwise
     */
    public boolean updateVehicleStatus(int idVehicle, String status) {

        try (Connection con2 = getConnection()) {

            try (CallableStatement callStmt2 = con2.prepareCall("{ call updateVehicleStatus(?,?) }")) {

                callStmt2.setInt(1, idVehicle);
                callStmt2.setString(2, status);

                callStmt2.execute();
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
