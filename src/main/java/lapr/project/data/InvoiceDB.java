package lapr.project.data;

import lapr.project.model.Invoice;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvoiceDB extends DataHandler {

    public boolean addInvoice(Invoice i) {

        return addInvoice(i.getOrderId(), i.getPharmacyId(), i.getClientEmail(), i.getDeliveryFee(), i.getTotalPrice());
    }

    private boolean addInvoice(int orderId, int pharmacyId, String clientEmail, double deliveryFee, double totalPrice) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ call createInvoice(?,?,?,?,?) }")) {

                callStmt.setInt(1, orderId);
                callStmt.setInt(2, pharmacyId);
                callStmt.setString(3, clientEmail);
                callStmt.setDouble(4, deliveryFee);
                callStmt.setDouble(5, totalPrice);

                callStmt.execute();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeAll();
        }
    }
}
