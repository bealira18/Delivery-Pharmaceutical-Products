package lapr.project.data;

import lapr.project.model.Delivery;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryDB extends DataHandler {

    public boolean addDeliveries(List<Delivery> deliveryList) throws SQLException {
        boolean check = true;
        try {
            openConnection();
            for (Delivery d : deliveryList) {
                if (!addDelivery(d)) {
                    check = false;
                }
            }

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
        return check;
    }

    private boolean addDelivery(Delivery d) throws SQLException {

        try {
            return addDelivery(d.getOrderId(), d.getVehicleId(), d.getCourierEmail(), d.getDeliveryStatusId(), d.getDeliveryStart(),
                    d.getDeliveryEnd(), d.getDeliveryRun());

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
            if(callStmt!=null) callStmt.close();
            closeAll();
        }
        return false;
    }

    public Delivery getNextAvailableScooter(int pharmacyId) throws SQLException {
        Delivery delivery = null;
        CallableStatement callStmt = null;
        ResultSet rSet = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getNextAvailableScooter(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setInt(2, pharmacyId);

            callStmt.execute();

            rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int idOrder = rSet.getInt(1);
                int idScooter = rSet.getInt(2);
                String emailCourier = rSet.getString(3);
                int idDeliveryStatus = rSet.getInt(4);
                Date deleveryStart = rSet.getDate(5);
                Date deliveryEnd = rSet.getDate(6);
                int deliveryRun = rSet.getInt(7);

                delivery = new Delivery(idOrder, idScooter, emailCourier, idDeliveryStatus, deleveryStart.toLocalDate(), deliveryEnd.toLocalDate(), deliveryRun);
            }

        } catch (SQLException e) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(callStmt!=null) callStmt.close();
            if(rSet!=null) rSet.close();
            closeAll();
        }
        return delivery;
    }

    public Delivery getNextAvailableCourier(int idPharmacy) throws SQLException {
        Delivery delivery = null;
        CallableStatement callStmt = null;
        ResultSet resultSetSet = null;

        try {
            openConnection();

            callStmt = getConnection().prepareCall("{ ? = call getNextAvailableCourier(?) }");;

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPharmacy);
            callStmt.execute();

            resultSetSet = (ResultSet) callStmt.getObject(1);

            if (resultSetSet.next()) {
                int orderId = resultSetSet.getInt(1);
                int idScooter = resultSetSet.getInt(2);
                String emailCourier = resultSetSet.getString(3);
                int idDeliveryStatus = resultSetSet.getInt(4);
                Date deleveryStart = resultSetSet.getDate(5);
                Date deliveryEnd = resultSetSet.getDate(6);
                int deliveryRun = resultSetSet.getInt(7);

                delivery = new Delivery(orderId, idScooter, emailCourier, idDeliveryStatus, deleveryStart.toLocalDate(), deliveryEnd.toLocalDate(), deliveryRun);
            }

        } catch (SQLException e) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(callStmt!=null) callStmt.close();
            if(resultSetSet!=null) resultSetSet.close();
            closeAll();
        }
        return delivery;
    }

}
