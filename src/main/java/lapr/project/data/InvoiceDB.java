package lapr.project.data;

import lapr.project.model.Invoice;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvoiceDB extends DataHandler {

    public boolean addInvoice(Invoice i, double deliveryFee) {

        openConnection();

        try {
            return addInvoice(i.getOrderId(), i.getPharmacyId(), i.getClientEmail(), deliveryFee, i.getTotalPrice());

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(InvoiceDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    private boolean addInvoice(int orderId, int pharmacyId, String clientEmail, double deliveryFee, double totalPrice) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{ call createInvoice(?,?,?,?,?) }");

            callStmt.setInt(1, orderId);
            callStmt.setInt(2, pharmacyId);
            callStmt.setString(3, clientEmail);
            callStmt.setDouble(4, deliveryFee);
            callStmt.setDouble(5, totalPrice);

            callStmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ParkDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if(callStmt!=null) callStmt.close();
            closeAll();
        }
        return false;
    }
}
