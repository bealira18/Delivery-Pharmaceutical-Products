package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Pharmacy;

public class PharmacyDB extends DataHandler {

    public boolean addPharmacy(Pharmacy p, int limit) throws SQLException {

        openConnection();

        try {
            return addPharmacy(p.getName(), p.getAddress(), limit);

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    private boolean addPharmacy(String name, String address, int limit) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt.getConnection().prepareCall("{ call addPharmacy(?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setString(2, address);
            callStmt.setInt(3, limit);

            callStmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(PharmacyDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
    }
}
