package lapr.project.data;

import lapr.project.model.Administrator;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministratorDB extends DataHandler {

    public boolean addAdministrator(Administrator administrator) {

        return addAdministrator(administrator.getEmail(), administrator.getPharmacyId(), administrator.getName(),
                administrator.getNif(), administrator.getSocialSecurity());
    }

    private boolean addAdministrator(String email, int idPharmacy, String name, int nif, long socialSecurity) {

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ call addAdministrator(?,?,?,?,?) }")) {

                callStmt.setString(1, email);
                callStmt.setInt(2, idPharmacy);
                callStmt.setString(3, name);
                callStmt.setInt(4, nif);
                callStmt.setLong(5, socialSecurity);

                callStmt.execute();
                return true;
            }

        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AdministratorDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            closeAll();
        }
    }
}
