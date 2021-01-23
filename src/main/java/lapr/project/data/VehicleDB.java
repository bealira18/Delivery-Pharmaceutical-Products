package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

public class VehicleDB extends DataHandler {

    public String typeOfVehicleByID(int vehicleID) {

        /* Objeto "callStmt" para invocar a função "findUser" armazenada na BD.
         *
         * FUNCTION typeOfVehicleByID(email_pr VARCHAR2, password_pr VARCHAR2) RETURN MATCHING_USER.ref_cursor
         */
        CallableStatement callStmt = null;

        try {

            callStmt = getConnection().prepareCall("{ ? = call typeOfVehicleByID(?) }");
            callStmt.registerOutParameter(1, OracleTypes.VARCHAR);
            callStmt.setInt(2, vehicleID);

            callStmt.execute();
            return callStmt.getString(1);

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Could not find a vehicle matching this id");
    }

    public List<String> getEmailNameFromParkedVehicleResponsible(int vehicleID) {

        List<String> emailName = new ArrayList<>();

        CallableStatement callStmt = null;
        ResultSet rSet = null;

        try {

            callStmt = getConnection().prepareCall("{ ? = call getEmailNameFromParkedVehicleResponsible(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, vehicleID);

            callStmt.execute();

            rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String email = rSet.getString(1);
                String name = rSet.getString(2);
                emailName.add(email);
                emailName.add(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try
            {
                if(callStmt!=null) callStmt.close();
                if(rSet!=null) rSet.close();
                closeAll();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
   
        }
        return emailName;
    }
}
