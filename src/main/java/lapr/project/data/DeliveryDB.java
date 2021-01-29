package lapr.project.data;

import lapr.project.model.Delivery;
import lapr.project.model.PurchaseOrder;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryDB extends DataHandler {

    /**
     * Adds deliverys to the delivery list
     *
     * @param deliveryList delivery list
     * @return true if it adds the delivery, false otherwise
     */
    public boolean addDeliveries(List<Delivery> deliveryList) {

        boolean check = true;

        for (Delivery d : deliveryList) {

            if (!addDelivery(d)) {
                check = false;
            }
        }
        return check;
    }

    /**
     * Calls the data base to create a new delivery
     *
     * @param idOrder order id
     * @param deliveryRun the time taken by the delivery
     * @return true if it creates a delivery and false if some exception appears
     */
    public boolean createNewDelivery(int idOrder, int deliveryRun) {

        try (Connection con4 = getConnection()) {

            try (CallableStatement callStmt4 = con4.prepareCall("{ call createDelivery(?,?) }")) {

                callStmt4.setInt(1, idOrder);
                callStmt4.setInt(2, deliveryRun);

                callStmt4.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeAll();
        }
    }

    /**
     * Getts the next available scooter
     *
     * @param pharmacyId pharmacy id
     * @return a delivery that have already ended and has scooter free
     */
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

    /**
     * Getts the next available courier
     *
     * @param idPharmacy pharmacy id
     * @return a delivery that have already ended and has a courier free
     */
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

    /**
     * Getts thee client email from his purchased order
     *
     * @param idOrder order id
     * @return the the order's client email
     */
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

    /**
     * Getts the Order correspondent to the delivery run
     *
     * @param idDeliveryRun delivery run id
     * @return list of orders correspondent to the delivery run
     */
    public List<PurchaseOrder> getPurchaseOrdersByDeliveryRun(int idDeliveryRun) {

        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

        try (Connection con4 = getConnection()) {

            try (CallableStatement callStmt4 = con4.prepareCall("{ ? = call getPurchaseOrdersByDeliveryRun(?) }")) {

                callStmt4.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt4.setInt(2, idDeliveryRun);

                callStmt4.execute();

                try (ResultSet rs = (ResultSet) callStmt4.getObject(1)) {

                    while (rs.next()) {

                        int orderId = rs.getInt(1);
                        int pharmacyId = rs.getInt(2);
                        String emailClient = rs.getString(3);
                        LocalDate emissionDate = rs.getDate(4).toLocalDate();

                        PurchaseOrder purchaseOrder = new PurchaseOrder(orderId, pharmacyId, emailClient, emissionDate);
                        purchaseOrderList.add(purchaseOrder);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAll();
        }
        return purchaseOrderList;
    }

    /**
     * Updates the vehicle associated to the delivery run
     *
     * @param idVehicle vehicle id
     * @param deliveryRun delivery run
     * @return true if it updates the vehicle id in the delivery run or false if some execption appears
     */
    public boolean deliveryRunAssociateVehicle(int idVehicle, int deliveryRun) {

        try (Connection con5 = getConnection()) {

            try (CallableStatement callStmt5 = con5.prepareCall("{ call deliveryRunAssociateVehicle(?,?) }")) {

                callStmt5.setInt(1, idVehicle);
                callStmt5.setInt(2, deliveryRun);

                callStmt5.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeAll();
        }
    }

    /**
     * Updates the courier associated to the delivery run
     *
     * @param emailCourier courier email
     * @param deliveryRun delivery run
     * @return true if it updates the courier email in the delivery run or false if some execption appears
     */
    public boolean deliveryRunAssociateCourier(String emailCourier, int deliveryRun) {

        try (Connection con6 = getConnection()) {

            try (CallableStatement callStmt6 = con6.prepareCall("{ call deliveryRunAssociateCourier(?,?) }")) {

                callStmt6.setString(1, emailCourier);
                callStmt6.setInt(2, deliveryRun);

                callStmt6.execute();
                return true;
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(DeliveryDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeAll();
        }
    }

    /**
     * Adds a delivery to the system
     *
     * @param d delivery to add
     * @return true if it adds or false otherwise
     */
    private boolean addDelivery(Delivery d) {

        return addDelivery(d.getOrderId(), d.getVehicleId(), d.getCourierEmail(), d.getDeliveryStatusId(), d.getDeliveryStart(),
                d.getDeliveryEnd(), d.getDeliveryRun());
    }

    /**
     * Calls the data base to add a Delivery
     *
     * @param orderId order id
     * @param vehicleId vehicle id
     * @param courierEmail courier email
     * @param deliveryStatusId id of the delivery status
     * @param deliveryStart the date of the delivery start
     * @param deliveryEnd the date of the delivery end
     * @param deliveryRun the time it takes to end
     * @return true if it adds or false if any exeption appears
     */
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
