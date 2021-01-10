package lapr.project.data;

import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScooterParkDB extends DataHandler {

    //Para duplicação receber o call por string e juntar as duas funções em uma
    public int getLimitScooterPark(int idPharmacy) throws SQLException {
        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getLimitScooterPark(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idPharmacy);
            callStmt.execute();

            return callStmt.getInt(1);
        }catch(NullPointerException | NumberFormatException | SQLException ex){
            Logger.getLogger(ScooterParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }finally {
            if(callStmt != null){
                callStmt.close();
            }
        }
    }

    public int getNumberOfScootersInPharmacy(int idPharmacy) throws SQLException{
        CallableStatement callStmt = null;

        try{
            callStmt = getConnection().prepareCall("{ ? = call getNumberOfScootersInPharmacy(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idPharmacy);
            callStmt.execute();

            return callStmt.getInt(1);
        }catch(NullPointerException | NumberFormatException | SQLException ex){
            Logger.getLogger(ScooterParkDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }finally {
            if(callStmt != null){
                callStmt.close();
            }
        }
    }
}
