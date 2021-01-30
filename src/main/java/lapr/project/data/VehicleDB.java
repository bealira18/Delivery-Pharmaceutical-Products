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

        try (Connection con = getConnection()) {

            try (CallableStatement callStmt = con.prepareCall("{ ? = call typeOfVehicleByID(?) }")) {
                callStmt.registerOutParameter(1, OracleTypes.VARCHAR);
                callStmt.setInt(2, vehicleID);

                callStmt.execute();
                return callStmt.getString(1);
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(VehicleDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Could not find a vehicle matching this id");
    }

    public List<String> getEmailNameFromParkedVehicleResponsible(int vehicleID) {

        List<String> emailName = new ArrayList<>();

        try (Connection con1 = getConnection()) {

            try (CallableStatement callStmt1 = con1.prepareCall("{ ? = call getEmailNameFromParkedVehicleResponsible(?) }")) {
                callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
                callStmt1.setInt(2, vehicleID);

                callStmt1.execute();

                try (ResultSet rs = (ResultSet) callStmt1.getObject(1)) {

                    if (rs.next()) {
                        String email = rs.getString(1);
                        String name = rs.getString(2);
                        emailName.add(email);
                        emailName.add(name);
                    }
                }
            }
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(VehicleDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            closeAll();
        }
        return emailName;
    }

}
