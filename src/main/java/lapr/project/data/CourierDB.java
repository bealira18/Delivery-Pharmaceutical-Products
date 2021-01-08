package lapr.project.data;

import lapr.project.model.Courier;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourierDB extends DataHandler {

    public boolean addCourier(Courier c) throws SQLException {

        openConnection();

        try {
            return addCourier(c.getEmail(), c.getName(), c.getNif(), c.getSocialSecurity(), c.getPharmacyId());

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(CourierDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    private boolean addCourier(String email, String name, int nif, int socialSecurity, int pharmacyId) throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt.getConnection().prepareCall("{ call addCourier(?,?,?,?,?) }");

            callStmt.setInt(1, pharmacyId);
            callStmt.setString(2, email);
            callStmt.setString(3, name);
            callStmt.setInt(4, nif);
            callStmt.setInt(5, socialSecurity);

            callStmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CourierDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
    }
}
