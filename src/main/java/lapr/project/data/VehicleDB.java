package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
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
        }
        finally{
            closeAll();
        }
        throw new IllegalArgumentException("Could not find a vehicle matching this id");
    }
}
