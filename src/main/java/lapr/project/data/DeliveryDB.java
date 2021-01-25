package lapr.project.data;

import lapr.project.model.Delivery;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryDB extends DataHandler {

    public boolean addDeliveries(List<Delivery> deliveryList) {

        boolean check = true;

        for (Delivery d : deliveryList) {

            if (!addDelivery(d)) {
                check = false;
            }
        }
        return check;
    }

    public Delivery getNextAvailableScooter(int pharmacyId) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getNextAvailableScooter(?) }")) {

                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(2, pharmacyId);

                callStmt.execute();

                try (ResultSet rs = (ResultSet) callStmt.getObject(1)) {

                    if (rs.next()) {
                        int idOrder = rs.getInt(1);
                        int idScooter = rs.getInt(2);
                        String emailCourier = rs.getString(3);
                        int idDeliveryStatus = rs.getInt(4);
                        Date deliveryStart = rs.getDate(5);
                        Date deliveryEnd = rs.getDate(6);
                        int deliveryRun = rs.getInt(7);

                        return new Delivery(idOrder, idScooter, emailCourier,
                                idDeliveryStatus, deliveryStart.toLocalDate(), deliveryEnd.toLocalDate(), deliveryRun);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        return null;
    }

    public Delivery getNextAvailableCourier(int idPharmacy) {

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ ? = call getNextAvailableCourier(?) }")) {

                callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt1.setInt(2, idPharmacy);

                callStmt1.execute();

                try (ResultSet rs1 = (ResultSet) callStmt1.getObject(1)) {

                    if (rs1.next()) {
                        int orderId = rs1.getInt(1);
                        int idScooter = rs1.getInt(2);
                        String emailCourier = rs1.getString(3);
                        int idDeliveryStatus = rs1.getInt(4);
                        Date deliveryStart = rs1.getDate(5);
                        Date deliveryEnd = rs1.getDate(6);
                        int deliveryRun = rs1.getInt(7);

                        return new Delivery(orderId, idScooter, emailCourier,
                                idDeliveryStatus, deliveryStart.toLocalDate(), deliveryEnd.toLocalDate(), deliveryRun);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        return null;
    }

    public String getClientEmailFromOrder(int idOrder) {

        try (Connection con2 = getConnection()) {

            try (CallableStatement callStmt2 = con2.prepareCall("{ ? = call getClientEmailFromOrder(?) }")) {

                callStmt2.registerOutParameter(1, OracleTypes.VARCHAR);
                callStmt2.setInt(2, idOrder);
                callStmt2.execute();

                return callStmt2.getString(1);
            }
        } catch (NullPointerException | NumberFormatException | SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        } finally {
            closeAll();
        }
    }

    private boolean addDelivery(Delivery d) {

        return addDelivery(d.getOrderId(), d.getVehicleId(), d.getCourierEmail(), d.getDeliveryStatusId(), d.getDeliveryStart(),
                d.getDeliveryEnd(), d.getDeliveryRun());
    }

    private boolean addDelivery(int orderId, int vehicleId, String courierEmail, int deliveryStatusId,
            LocalDate deliveryStart, LocalDate deliveryEnd, int deliveryRun) {

        try (Connection con3 = getConnection()) {

            try (CallableStatement callStmt3 = con3.prepareCall("{ call addDelivery(?,?,?,?,?,?,?) }")) {

                callStmt3.setInt(1, orderId);
                callStmt3.setInt(2, vehicleId);
                callStmt3.setString(3, courierEmail);
                callStmt3.setInt(4, deliveryStatusId);
                callStmt3.setDate(5, Date.valueOf(deliveryStart));
                callStmt3.setDate(6, Date.valueOf(deliveryEnd));
                callStmt3.setInt(7, deliveryRun);

                callStmt3.execute();
                return true;
            }

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }
}
