package lapr.project.data;

import lapr.project.model.Scooter;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScooterDB extends DataHandler {

    public boolean addScooter(Scooter scooter)throws SQLException {

        openConnection();

        try{
            return addScooter(scooter.getPharmacyId(),scooter.getScooterStatusId(), scooter.getCurrentBattery(), scooter.getMaxBattery());

        }catch (NullPointerException | SQLException ex){
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    public boolean addScooter(int idP, int idSS, double currentBat, double maxBat)throws SQLException {

        CallableStatement callStmt = null;

        try {
            callStmt.getConnection().prepareCall("{ call addScooter(?,?,?,?) }");

            callStmt.setInt(1, idP);
            callStmt.setInt(2, idSS);
            callStmt.setDouble(3, currentBat);
            callStmt.setDouble(4, maxBat);

            callStmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ScooterDB.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (callStmt != null) {
                callStmt.close();
            }
        }
        return false;
    }

}
