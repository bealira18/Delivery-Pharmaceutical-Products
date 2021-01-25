package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

public class VehicleDB extends DataHandler {

    public String typeOfVehicleByID(int vehicleID) {

        /* Objeto "callStmt" para invocar a função "findUser" armazenada na BD.
         *
         * FUNCTION typeOfVehicleByID(email_pr VARCHAR2, password_pr VARCHAR2) RETURN MATCHING_USER.ref_cursor
         */
        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call typeOfVehicleByID(?) }")) {
                callStmt.registerOutParameter(1, OracleTypes.VARCHAR);
                callStmt.setInt(2, vehicleID);

                callStmt.execute();
                return callStmt.getString(1);
            }
        } catch (NullPointerException | SQLException e) {
            Logger.getLogger(VehicleDB.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Could not find a vehicle matching this id");
    }

    public List<String> getEmailNameFromParkedVehicleResponsible(int vehicleID) {

        List<String> emailName = new ArrayList<>();

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call getEmailNameFromParkedVehicleResponsible(?) }")) {
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt.setInt(2, vehicleID);

                callStmt.execute();

                try (ResultSet rSet = (ResultSet) callStmt.getObject(1)) {

                    if (rSet.next()) {
                        String email = rSet.getString(1);
                        String name = rSet.getString(2);
                        emailName.add(email);
                        emailName.add(name);
                    }
                }
            }
        } catch (NullPointerException | SQLException e) {
            Logger.getLogger(VehicleDB.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            closeAll();
        }
        return emailName;
    }
}
