package lapr.project.data;

import lapr.project.model.Delivery;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryDB extends DataHandler {

    public boolean addDelivery(Delivery d) throws SQLException {

        openConnection();

        try {
            return addDelivery(d.getOrderId(), d.getVehicleId(), d.getCourierEmail(), d.getDeliveryStatusId(), d.getDeliveryStart(),
                                d.getDeliveryEnd() ,d.getDeliveryRun());

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    private boolean addDelivery(int orderId, int vehicleId, String courierEmail, int deliveryStatusId, LocalDate deliveryStart, LocalDate deliveryEnd, int deliveryRun) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt.getConnection().prepareCall("{ call addDelivery(?,?,?,?,?,?,?) }");

            callStmt.setInt(1, orderId);
            callStmt.setInt(2, vehicleId);
            callStmt.setString(3, courierEmail);
            callStmt.setInt(4, deliveryStatusId);
            callStmt.setDate(5, Date.valueOf(deliveryStart));
            callStmt.setDate(6, Date.valueOf(deliveryEnd));
            callStmt.setInt(7, deliveryRun);

            callStmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
    }

}
